package dulk.wx4j.message.api;

import dulk.wx4j.base.api.WxConfig;

/**
 * 微信消息管理相关的API
 */
public class WxMessageAPI {

    //微信消息类型
    public static final String MSG_TYPE_EVENT = "event";
    public static final String MSG_TYPE_TEXT = "text";
    public static final String MSG_TYPE_IMAGE = "image";
    public static final String MSG_TYPE_VOICE = "voice";
    public static final String MSG_TYPE_VIDEO = "video";
    public static final String MSG_TYPE_SHORT_VIDEO = "shortvideo";
    public static final String MSG_TYPE_LOCATION = "location";
    public static final String MSG_TYPE_LINK = "link";
    public static final String MSG_TYPE_MUSIC = "music";
    public static final String MSG_TYPE_NEWS = "news";

    //微信事件类型
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    public static final String EVENT_TYPE_SCAN = "SCAN";
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
    public static final String EVENT_TYPE_MENU_CLICK = "CLICK";
    public static final String EVENT_TYPE_MENU_VIEW = "VIEW";

    //根据标签群发的消息类型
    public static final String SEND_TAG_MSG_TYPE_NEWS = "mpnews";
    public static final String SEND_TAG_MSG_TYPE_TEXT = "text";
    public static final String SEND_TAG_MSG_TYPE_VOICE = "voice";
    public static final String SEND_TAG_MSG_TYPE_MUSIC = "music";
    public static final String SEND_TAG_MSG_TYPE_IMAGE = "image";
    public static final String SEND_TAG_MSG_TYPE_VIDEO = "mpvideo";
    public static final String SEND_TAG_MSG_TYPE_CARD = "wxcard";


    /**
     * "根据标签群发"的接口
     * <p>
     * 需要参数accessToken；https协议，POST请求
     * </p>
     */
    private static String url_sendAllByTag = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=%s";

    /**
     * "获取视频的mediaId"接口
     * <p>
     * 该接口是在发送视频消息之前，需要先POSTmediaId调用该接口，返回一个新的mediaId，使用推送接口POST新的mediaId
     * </p>
     */
    private static String url_getUploadVideo = "https://api.weixin.qq.com/cgi-bin/media/uploadvideo?access_token=%s";

    /**
     * 获取"根据标签群发"的接口
     *
     * @return 接口url
     */
    public static String getUrl_sendAllByTag() {
        return String.format(url_sendAllByTag, WxConfig.getAccessToken());
    }

    /**
     * 获取"获得视频的mediaId"的接口
     *
     * @return 接口url
     */
    public static String getUrl_getUploadVideo() {
        return String.format(url_getUploadVideo, WxConfig.getAccessToken());
    }
}
