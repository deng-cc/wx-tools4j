package dulk.wx4j.base.api;

/**
 * WxBaseAPI.
 *
 * @author Dulk
 * @version 20170812
 * @date 17-8-12
 */
public class WxBaseAPI {

    /**
     * "获取access_token"的接口
     * https协议，GET请求
     * 参数
     *   appID
     *   appSecret
     */
    private static String url_getAccessToken = "https://api.weixin.qq.com/cgi-bin/token?" +
            "grant_type=client_credential&appid=%s&secret=%s";


    public static String getUrl_getAccessToken() {
        return String.format(url_getAccessToken, WxConfig.getAppId(), WxConfig.getAppSecret());
    }


}
