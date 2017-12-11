package dulk.wx4j.message.domain;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import dulk.wx4j.base.exception.WxException;
import dulk.wx4j.base.util.NetUtil;
import dulk.wx4j.message.api.WxMessageAPI;

/**
 * 视频消息封装类
 */
public class Video {

    /**
     * 媒体id
     * <p>
     * 此处视频的mdiaId需要基础支持中的上传下载多媒体文件接口来得到，
     * 在构造函数中会进行处理，所以传入的mediaId参数仍然传入上传素材的mediaId即可
     * </p>
     */
    @JSONField(name = "media_id")
    private String mediaId;

    public Video() {
    }

    public Video(String mediaId) {
        JSONObject post = new JSONObject();
        post.put("media_id", mediaId);
        post.put("title", "title");
        post.put("description", "description");
        String refineMediaId = null;
        try {
            refineMediaId = NetUtil.sendRequestPOST(WxMessageAPI.getUrl_getUploadVideo(), post.toJSONString()).getString("media_id");
        } catch (WxException e) {
            e.printStackTrace();
        }
        this.mediaId = refineMediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
