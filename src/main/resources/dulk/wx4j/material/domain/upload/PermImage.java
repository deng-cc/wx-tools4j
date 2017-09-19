package dulk.wx4j.material.domain.upload;

/**
 * 永久图片素材的实体类
 */
public class PermImage {

    /**
     * 永久图片素材的媒体Id
     */
    private String mediaId;

    /**
     * 永久图片素材的url
     */
    private String url;

    public PermImage(String mediaId, String url) {
        this.mediaId = mediaId;
        this.url = url;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}