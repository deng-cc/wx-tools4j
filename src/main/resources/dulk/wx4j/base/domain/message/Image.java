package dulk.wx4j.base.domain.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信交互的响应图片参数的封装类
 */
public class Image {

    /**
     * 图片媒体id
     */
    @XStreamAlias("MediaId")
    private String mediaId;

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
