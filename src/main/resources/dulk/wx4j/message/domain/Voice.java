package dulk.wx4j.message.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 语音消息封装类
 */
public class Voice {

    /**
     * 媒体id
     */
    @JSONField(name = "media_id")
    private String mediaId;

    public Voice() {
    }

    public Voice(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
