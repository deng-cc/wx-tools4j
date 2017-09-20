package dulk.wx4j.base.api;

/**
 * 微信服务的最基本服务API类
 * <p>
 * 该API不涉及具体的操作类别，而主要用于获取调用接口凭证，做其他接口调用的参数支撑
 * </p>
 */
public class WxBaseAPI {

    /**
     * 获取access_token的接口
     * <p>
     * 需要参数appId、appSecret。https协议，GET请求
     * </p>
     */
    private static String url_getAccessToken = "https://api.weixin.qq.com/cgi-bin/token?" +
            "grant_type=client_credential&appid=%s&secret=%s";

    /**
     * 获取"获取access_token"的接口
     *
     * @return 接口url
     */
    public static String getUrl_getAccessToken() {
        return String.format(url_getAccessToken, WxConfig.getAppId(), WxConfig.getAppSecret());
    }


}
