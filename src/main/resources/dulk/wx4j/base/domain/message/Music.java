package dulk.wx4j.base.domain.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Music.
 *
 * @author Dulk
 * @version 20170906
 * @date 17-9-6
 */
public class Music {
    @XStreamAlias("Title")
    private String title;

    @XStreamAlias("Description")
    private String description;

    @XStreamAlias("MusicUrl")
    private String musicUrl;

    @XStreamAlias("HQMusicUrl")
    private String hqMusicUrl;

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
