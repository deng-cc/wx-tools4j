package dulk.wx4j.material.domain.upload;

/**
 * 上传永久图片素材成功后，返回的图片信息的封装类
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
