package dulk.wx4j.base.domain.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Image.
 *
 * @author Dulk
 * @version 20170906
 * @date 17-9-6
 */
public class Image {
    
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
