package dulk.wx4j.pay.service;

import com.alibaba.fastjson.JSONObject;
import dulk.wx4j.base.util.NetUtil;
import dulk.wx4j.base.util.XmlUtil;
import dulk.wx4j.pay.api.WxPayAPI;
import dulk.wx4j.pay.domain.PayRequestJSAPI;
import dulk.wx4j.pay.domain.UnifiedorderRequest;
import dulk.wx4j.pay.domain.UnifiedorderResponse;
import dulk.wx4j.pay.exception.PayException;
import dulk.wx4j.pay.util.SignUtil;
import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.Locale;
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


}
