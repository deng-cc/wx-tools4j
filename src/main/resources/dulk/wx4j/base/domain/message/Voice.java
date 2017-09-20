package dulk.wx4j.base.domain.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信交互的响应语音参数的封装类
 */
public class Voice {

    /**
     * 语音媒体id
     */
    @XStreamAlias("MediaId")
    private String mediaId;

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
