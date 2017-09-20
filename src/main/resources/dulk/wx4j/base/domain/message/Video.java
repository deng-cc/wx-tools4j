package dulk.wx4j.base.domain.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信交互的响应视频参数的封装类
 */
public class Video {

    /**
     * 视频媒体id
     */
    @XStreamAlias("MediaId")
    private String mediaId;

    /**
     * 视频标题
     */
    @XStreamAlias("Title")
    private String title;

    /**
     * 视频描述
     */
    @XStreamAlias("Description")
    private String description;

    public Video(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
