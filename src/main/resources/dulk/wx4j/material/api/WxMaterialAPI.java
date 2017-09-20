package dulk.wx4j.material.api;

import dulk.wx4j.base.api.WxConfig;

/**
 * 微信素材管理相关的API
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

    //各永久素材的类型限制
    public static final String[] ALLOWED_PERM_NEWSIMAGE_TYPE = {"png", "jpg"};
    public static final String[] ALLOWED_PERM_IMAGE_TYPE = {"bmp", "png", "jpeg", "jpg", "gif"};
    public static final String[] ALLOWED_PERM_VOICE_TYPE = {"mp3", "wma", "wav", "amr", "mka"};
    public static final String[] ALLOWED_PERM_VIDEO_TYPE = {"mp4"};
    public static final String[] ALLOWED_PERM_THUMB_TYPE = {"jpg"};

    //各永久素材的大小限制，单位：字节
    public static final long ALLOWED_PERM_NEWSIMAGE_SIZE = 1024 * 1024 * 1;
    public static final long ALLOWED_PERM_IMAGE_SIZE = 1024 * 1024 * 2;
    public static final long ALLOWED_PERM_VOICE_SIZE = 1024 * 1024 * 2;
    public static final long ALLOWED_PERM_VIDEO_SIZE = 1024 * 1024 * 10;
    public static final long ALLOWED_PERM_THUMB_SIZE = 1024 * 64;

    //其他永久素材的条件限制
    public static final long ALLOWED_PERM_VOICE_TIME = 60; //永久语音的播放长度限制，单位：秒


    /**
     * "新增临时素材"的接口，返回的临时素材mediaId有效期3天
     * <p>
     * 需要参数accessToken、type；https协议，POST/FORM请求
     * </p>
     */
    private static String url_uploadTemp = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=%s";

    /**
     * "获取临时素材"的接口
     * <p>
     * 需要参数accessToken、mediaId；https协议，GET请求
     * </p>
     */
    private static String url_downloadTemp = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";

    /**
     * "新增永久图文素材"的接口
     * <p>
     * 需要参数accessToken；https协议，POST请求
     * </p>
     */
    private static String url_uploadPermNews = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=%s";

    /**
     * "上传图文消息内图片"的接口
     * <p>
     * 需要参数accessToken；https协议，POST请求
     * </p>
     */
    private static String url_uploadPermNewsImage = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=%s";

    /**
     * "上传其他类型永久素材"的接口
     * <p>
     * 其他媒体文件类型，包括图片、语音、视频和缩略图
     * </p>
     * <p>
     * 需要参数accessToken、type；https协议，POST请求
     * </p>
     */
    private static String url_uploadPermMedia = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=%s&type=%s";

    /**
     * "下载永久素材"的接口
     * <p>
     * 需要参数accessToken；https协议，POST请求
     * </p>
     */
    private static String url_downloadPermMedia = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=%s";

    /**
     * "获取永久素材列表"的接口
     * <p>
     * 需要参数accessToken；https协议，POST请求
     * </p>
     */
    private static String url_getPermMediaList = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=%s";

    /**
     * "获取素材总数"的接口
     * <p>
     * 需要参数accessToken；https协议，GET请求
     * </p>
     */
    private static String url_getPermMediaCount = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=%s";

    /**
     * "删除永久素材"的接口
     * <p>
     * 需要参数accessToken；https协议，POST请求
     * </p>
     */
    private static String url_deletePermMedia = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=%s";

    /**
     * "修改永久图文素材"的接口
     * <p>
     * 需要参数accessToken；https协议，POST请求
     * </p>
     */
    private static String url_updatePermNews = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=%s";


    /**
     * 获取"新增临时素材"的接口
     *
     * @param type 希望获取的素材类型
     * @return 接口url
     */
    public static String getUrl_getTempMediaId(MaterialType type) {
        return String.format(url_uploadTemp, WxConfig.getAccessToken(), type.getValue());
    }

    /**
     * 获取"获取临时素材"的接口
     *
     * @param mediaId 素材媒体id
     * @return 接口url
     */
    public static String getUrl_downloadTempMedia(String mediaId) {
        return String.format(url_downloadTemp, WxConfig.getAccessToken(), mediaId);
    }

    /**
     * 获取"新增永久图文素材"的接口
     *
     * @return 接口url
     */
    public static String getUrl_uploadPermNews() {
        return String.format(url_uploadPermNews, WxConfig.getAccessToken());
    }

    /**
     * 获取"上传图文消息内图片"的接口
     *
     * @return 接口url
     */
    public static String getUrl_uploadPermNewsImage() {
        return String.format(url_uploadPermNewsImage, WxConfig.getAccessToken());
    }

    /**
     * 获取"上传其他类型永久素材"的接口
     *
     * @param type 素材类型
     * @return 接口url
     */
    public static String getUrl_uploadPermMedia(MaterialType type) {
        return String.format(url_uploadPermMedia, WxConfig.getAccessToken(), type.getValue());
    }

    /**
     * 获取"下载永久素材"的接口
     *
     * @return 接口url
     */
    public static String getUrl_downloadPermMedia() {
        return String.format(url_downloadPermMedia, WxConfig.getAccessToken());
    }

    /**
     * 获取"获取永久素材列表"的接口
     *
     * @return 接口url
     */
    public static String getUrl_getPermMediaList() {
        return String.format(url_getPermMediaList, WxConfig.getAccessToken());
    }

    /**
     * 获取"获取素材总数"的接口
     *
     * @return 接口url
     */
    public static String getUrl_getPermMediaCount() {
        return String.format(url_getPermMediaCount, WxConfig.getAccessToken());
    }

    /**
     * 获取"删除永久素材"的接口
     *
     * @return 接口url
     */
    public static String getUrl_deletePermMedia() {
        return String.format(url_deletePermMedia, WxConfig.getAccessToken());
    }

    /**
     * 获取"修改永久图文素材"的接口
     *
     * @return 接口url
     */
    public static String getUrl_updatePermNews() {
        return String.format(url_updatePermNews, WxConfig.getAccessToken());
    }
}
