package dulk.wx4j.base.domain.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信交互的响应音乐参数的封装类
 */
public class Music {

    /**
     * 标题
     */
    @XStreamAlias("Title")
    private String title;

    /**
     * 描述
     */
    @XStreamAlias("Description")
    private String description;

    /**
     * 音乐链接
     */
    @XStreamAlias("MusicUrl")
    private String musicUrl;

    /**
     * 高质量音乐链接，wifi环境下优先使用该链接
     */
    @XStreamAlias("HQMusicUrl")
    private String hqMusicUrl;

    /**
     * 缩略图的媒体id
     */
    @XStreamAlias("ThumbMediaId")
    private String thumbMediaId;

    public Music(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
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

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getHqMusicUrl() {
        return hqMusicUrl;
    }

    public void setHqMusicUrl(String hqMusicUrl) {
        this.hqMusicUrl = hqMusicUrl;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
}
