package dulk.wx4j.material.domain.get;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 其他永久媒体素材的封装类
 * <p>
 *     该类是获取永久素材列表的信息封装类，并最终封装为List作为同包中MediaList类的属性
 * </p>
 */
public class Media {

    /**
     * 媒体id
     */
    @JSONField(name = "media_id")
    private String mediaId;

    /**
     * 文件名称
     */
    @JSONField(name = "name")
    private String name;

    /**
     * 素材的更新时间
     */
    @JSONField(name = "update_time")
    private String updateTime;

    /**
     * 图片的url
     */
    @JSONField(name = "url")
    private String url;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
