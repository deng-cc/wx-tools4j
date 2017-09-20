package dulk.wx4j.base.api;

/**
 * 微信的核心参数配置类
 * <p>
 * 该类用于封装微信开发者的各项配置
 * </p>
 */
public class WxConfig {

    /**
     * 开发者的应用ID
     */
    private static String appId;

    /**
     * 开发者的应用密钥
     */
    private static String appSecret;

    /**
     * 开发者的微信接口使用凭证，需要通过appId和appSecret获取，有效期限制2小时，每天获取次数限制为2000次
     */
    private static String accessToken;

    /**
     * 商户ID
     */
    private static String merchantId;

    /**
     * 微信支付商户的API密钥key
     */
    private static String merchantKey;


    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        WxConfig.appId = appId;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    public static void setAppSecret(String appSecret) {
        WxConfig.appSecret = appSecret;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        WxConfig.accessToken = accessToken;
    }

    public static String getMerchantId() {
        return merchantId;
    }

    public static void setMerchantId(String merchantId) {
        WxConfig.merchantId = merchantId;
    }

    public static String getMerchantKey() {
        return merchantKey;
    }

    public static void setMerchantKey(String merchantKey) {
        WxConfig.merchantKey = merchantKey;
    }
}
