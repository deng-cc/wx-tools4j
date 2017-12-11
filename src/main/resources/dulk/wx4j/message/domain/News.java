package dulk.wx4j.message.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 图文消息封装类
 */
public class News {

    /**
     * 媒体id
     */
    @JSONField(name = "media_id")
    private String mediaId;

    public News() {
    }

    public News(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
