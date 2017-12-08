package dulk.wx4j.pay.service;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import dulk.wx4j.base.api.WxConfig;
import dulk.wx4j.base.util.NetUtil;
import dulk.wx4j.base.util.XmlUtil;
import dulk.wx4j.pay.api.WxPayAPI;
import dulk.wx4j.pay.domain.PayRequestJSAPI;
import dulk.wx4j.pay.domain.PayRequestNATIVE;
import dulk.wx4j.pay.domain.PayResult;
import dulk.wx4j.pay.domain.RefundRequest;
import dulk.wx4j.pay.domain.RefundResponse;
import dulk.wx4j.pay.domain.UnifiedorderRequest;
import dulk.wx4j.pay.domain.UnifiedorderResponse;
import dulk.wx4j.pay.exception.PayException;
import dulk.wx4j.pay.util.SignUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 微信支付相关的服务类
 */
public class WxPayService {
    private static Logger log = Logger.getLogger(WxPayService.class);

    /**
     * 统一下单
     * <p>
     * 调用微信支付的统一下单接口，返回其响应参数的封装对象
     * </p>
     *
     * @param request 统一下单请求的参数封装类
     * @return 统一下单响应类
     * @throws PayException
     */
    public static UnifiedorderResponse unifiedorder(UnifiedorderRequest request) throws PayException {
        String result = NetUtil.throwRequestPOST(WxPayAPI.getUrl_unifiedorder(), request.toSignedXml());
        UnifiedorderResponse response = XmlUtil.toBean(result, UnifiedorderResponse.class);
        if ("FAIL".equals(response.getReturnCode())) {
            throw new PayException("统一下单接口调用失败: " + response.getReturnMsg());
        }
        if ("FAIL".equals(response.getResultCode())) {
            throw new PayException("交易失败: " + response.getErrCode() + ":" + response.getErrCodeDesc());
        }
        return response;
    }

    /**
     * 微信公众号支付（JSAPI方式）
     * <p>
     * 参考官方文档：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=7_7&index=6
     * </p>
     *
     * @param request JSAPI方式的微信支付请求类
     * @return 参数的json格式字符串
     * @throws PayException
     */
    public static String wxPayByJSAPI(PayRequestJSAPI request) throws PayException {
        UnifiedorderResponse response = null;
        String result = null;
        try {
            response = unifiedorder(request);
            TreeMap<String, String> params = new TreeMap<String, String>();
            params.put("appId", response.getAppId());
            params.put("timeStamp", String.valueOf(Calendar.getInstance(Locale.CHINA).getTime().getTime() / 1000));
            params.put("nonceStr", SignUtil.createNonceStr());
            params.put("package", "prepay_id=" + response.getPrepayId());
            params.put("signType", "MD5");

            JSONObject resultJSON = new JSONObject();
            Set<String> keys = params.keySet();
            for (String key : keys) {
                resultJSON.put(key, params.get(key));
            }
            resultJSON.put("paySign", SignUtil.createSign(params));
            result = resultJSON.toJSONString();

        } catch (PayException e) {
            log.error(e.getMessage());
            throw new PayException(e.getMessage());
        }

        return result;
    }

    /**
     * 生成微信支付的二维码（jpg格式），以流形式输出
     *
     * @param request   微信二维码扫码支付的请求类
     * @param imgWidth  二维码图片的宽度
     * @param imgHeight 二维码图片的高度
     * @return 二维码图片（jpg格式）的输出流
     * @throws PayException
     */
    public static OutputStream wxPayByNATIVE(PayRequestNATIVE request, int imgWidth, int imgHeight) throws PayException {
        String imageType = "jpg";
        BitMatrix bitMatrix = basicWxPayByNATIVE(request, imgWidth, imgHeight);
        OutputStream out = null;
        try {
            MatrixToImageWriter.writeToStream(bitMatrix, imageType, out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return out;
    }

    /**
     * 生成微信支付的二维码图片（jpg格式），并返回其File封装类
     *
     * @param request   微信二维码扫码支付的请求类
     * @param imgWidth  二维码图片的宽度
     * @param imgHeight 二维码图片的高度
     * @param filepath  二维码图片的图片路径
     * @return 二维码图片（jpg格式）的File封装类
     * @throws PayException
     */
    public static File wxPayByNATIVE(PayRequestNATIVE request, int imgWidth, int imgHeight, String filepath) throws PayException {
        String imageType = "jpg";
        BitMatrix bitMatrix = basicWxPayByNATIVE(request, imgWidth, imgHeight);
        Path path = FileSystems.getDefault().getPath(filepath);
        try {
            MatrixToImageWriter.writeToPath(bitMatrix, imageType, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File(filepath);
    }


    /**
     * 生成微信支付二维码的基础方法
     * <p>
     * 该方法返回一个BitMatrix类，用以进一步调用，或将二维码转成流，或图片
     * </p>
     *
     * @param request   微信二维码扫码支付的请求类
     * @param imgWidth  二维码图片的宽度
     * @param imgHeight 二维码图片的高度
     * @return BitMatrix类
     * @throws PayException
     */
    private static BitMatrix basicWxPayByNATIVE(PayRequestNATIVE request, int imgWidth, int imgHeight) throws PayException {
        BitMatrix bitMatrix = null;
        try {
            UnifiedorderResponse response = unifiedorder(request);
            String codeUrl = response.getCodeUrl();
            if (codeUrl == null) {
                throw new PayException("二维码地址链接为空");
            }
            Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE, imgWidth, imgHeight, hints);

        } catch (PayException e) {
            log.error(e.getMessage());
            throw new PayException(e.getMessage());
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return bitMatrix;
    }

    /**
     * 微信退款
     *
     * @param request         退款请求参数封装类
     * @param certificatePath 证书物理路径
     * @return 退款响应参数封装类
     * @throws PayException
     */
    public static RefundResponse wxRefund(RefundRequest request, String certificatePath) throws PayException {
        if (request.getRefundFee() > request.getTotalFee()) {
            throw new PayException("申请退款金额大于订单总金额");
        }
        String url = WxPayAPI.getUrl_refund();
        String postContent = request.toSignedXml();
        FileInputStream inputStream = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient httpclient = null;
        String result = null;
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            inputStream = new FileInputStream(new File(certificatePath));
            keyStore.load(inputStream, WxConfig.getMerchantId().toCharArray());

            SSLContext sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, WxConfig.getMerchantId().toCharArray())
                    .build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

            HttpPost httpost = new HttpPost(url); // 设置响应头信息
            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpost.setEntity(new StringEntity(postContent, "UTF-8"));
            response = httpclient.execute(httpost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
            EntityUtils.consume(entity);
            inputStream.close();
            response.close();
            httpclient.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        RefundResponse refundResponse = null;
        refundResponse = result != null ? XmlUtil.toBean(result, RefundResponse.class) : null;
        if ("FAIL".equals(refundResponse.getReturnCode())) {
            throw new PayException("申请退款接口调用失败: " + refundResponse.getReturnMsg());
        }
        if ("FAIL".equals(refundResponse.getResultCode())) {
            throw new PayException("退款失败: " + refundResponse.getErrCode() + ":" + refundResponse.getErrCodeDesc());
        }
        return refundResponse;
    }

    /**
     * 微信支付后的业务处理和应答
     * <p>
     * 支付后微信会向支付时填写的notifyUrl服务器地址发送请求，包含支付情况以供服务器在业务上进行处理。
     * 同时要求服务器针对支付情况，对微信进行相关应答，否则微信会持续发送请求到该地址。
     * </p>
     * <p>
     * 开发者在调用该方法时，可以选择在WxPayHandler中实现业务处理，该方法会自动调用业务处理方法；
     * 或者传入null空值，用该方法返回的支付结果封装类，另行处理业务。
     * </p>
     * <p>
     * 文档参考 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7
     * </p>
     *
     * @param request  微信服务器发送来的请求
     * @param response 给微信服务器的响应
     * @param handler  业务处理接口的实现类
     * @return 微信支付结果的封装类
     * @throws PayException
     */
    public static PayResult afterWxPay(HttpServletRequest request, HttpServletResponse response, WxPayHandler handler) throws PayException {
        PayResult payResult = null;
        try {
            payResult = XmlUtil.toBean(request.getInputStream(), PayResult.class);
            //签名和订单金额校验
            if (payResult.getSign() != null && !payResult.getSign().equals(SignUtil.createSign(payResult.toTreeMap()))) {
                String errMsg = "签名验证失败，可能出现了\"伪造的微信支付结果通知\"";
                log.error(errMsg);
                throw new PayException(errMsg);
            }
            //业务处理和应答
            String returnCode = payResult.getReturnCode();
            String resultCode = payResult.getResultCode();
            if ("SUCCESS".equals(returnCode) && "SUCCESS".equals(resultCode)) {
                if (handler != null) {
                    handler.doWithSuccess(payResult);
                }
                String responseContent = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
                response.getWriter().write(responseContent);
            } else {
                if (handler != null) {
                    handler.doWithFail(payResult);
                }
                String responseContent = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[NG]]></return_msg></xml>";
                response.getWriter().write(responseContent);
                String errMsg = "支付结果通知失败: " +
                        (payResult.getErrCodeDesc() != null? (payResult.getErrCode() + ":" + payResult.getErrCodeDesc()) : payResult.getReturnMsg());
                log.warn(errMsg);
                throw new PayException(errMsg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return payResult;
    }

}
