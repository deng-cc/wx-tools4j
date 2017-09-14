package dulk.wx4j.material.api;

import dulk.wx4j.base.api.WxConfig;

/**
 * 微信素材上传相关的API
 */
public class WxMaterialAPI {

    //各临时素材的类型限制
    public static final String[] ALLOWED_TEMP_IMAGE_TYPE = {"png", "jpeg", "jpg", "gif"};
    public static final String[] ALLOWED_TEMP_VOICE_TYPE = {"amr", "mp3"};
    public static final String[] ALLOWED_TEMP_VIDEO_TYPE = {"mp4"};
    public static final String[] ALLOWED_TEMP_THUMB_TYPE = {"jpg"};

    //各临时素材的大小限制，单位：字节
    public static final long ALLOWED_TEMP_IMAGE_SIZE = 1024 * 1024 * 2;
    public static final long ALLOWED_TEMP_VOICE_SIZE = 1024 * 1024 * 2;
    public static final long ALLOWED_TEMP_VIDEO_SIZE = 1024 * 1024 * 10;
    public static final long ALLOWED_TEMP_THUMB_SIZE = 1024 * 64;

    //其他临时素材的条件限制
    public static final long ALLOWED_TEMP_VOICE_TIME = 60; //临时语音的播放长度限制，单位：秒


    /**
     * "新增临时素材"的接口，返回的临时素材mediaId有效期3天
     * http协议，POST/FORM请求
     * 参数
     *   accessToken 接口调用凭证
     *   type 素材类型
     */
    private static String url_getTempMediaId = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=%s";

    public static String getUrl_getTempMediaId(MaterialType type) {
        return String.format(url_getTempMediaId, WxConfig.getAccessToken(), type.getValue());
    }





}
