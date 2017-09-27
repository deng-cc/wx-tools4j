package dulk.wx4j.pay.service;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import dulk.wx4j.base.util.NetUtil;
import dulk.wx4j.base.util.XmlUtil;
import dulk.wx4j.pay.api.WxPayAPI;
import dulk.wx4j.pay.domain.PayRequestJSAPI;
import dulk.wx4j.pay.domain.PayRequestNATIVE;
import dulk.wx4j.pay.domain.UnifiedorderRequest;
import dulk.wx4j.pay.domain.UnifiedorderResponse;
import dulk.wx4j.pay.exception.PayException;
import dulk.wx4j.pay.util.SignUtil;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
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
     * 生成微信支付的二维码，以流形式输出
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
     * 生成微信支付的二维码图片，并返回其File封装类
     *
     * @param request   微信二维码扫码支付的请求类
     * @param imgWidth  二维码图片的宽度
     * @param imgHeight 二维码图片的高度
     * @param filepath  二维码图片的图片路径
     * @return 二维码的File封装类
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

}
