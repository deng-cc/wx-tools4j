package dulk.wx4j.pay.api;

/**
 * 微信支付相关的API
 * <p>
 * 微信支付功能要求必须开通微信商户平台，其接口的开发文档也是单独的文档，详见https://pay.weixin.qq.com/wiki/doc/api/index.html
 * </p>
 */
public class WxPayAPI {

    /**
     * "统一下单"的接口
     * <p>
     * https协议，POST请求
     * </p>
     */
    private static String url_unifiedorder = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * "申请退款"的接口
     * <p>
     * https协议，POST请求
     * </p>
     */
    private static String url_refund = "https://api.mch.weixin.qq.com/secapi/pay/refund";


    /**
     * 获取"统一下单"的接口
     * 
     * @return 接口url
     */
    public static String getUrl_unifiedorder() {
        return url_unifiedorder;
    }

    /**
     * 获取"申请退款"的接口
     *
     * @return 接口url
     */
    public static String getUrl_refund() {
        return url_refund;
    }
}
