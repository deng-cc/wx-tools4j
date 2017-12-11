package dulk.wx4j.message.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 图片消息封装类
 */
public class Image {

    /**
     * 媒体id
     */
    @JSONField(name = "media_id")
    private String mediaId;

    public Image() {
    }

    public Image(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
