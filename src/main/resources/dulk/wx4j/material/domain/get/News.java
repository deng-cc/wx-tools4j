package dulk.wx4j.material.domain.get;


import com.alibaba.fastjson.annotation.JSONField;


/**
 * 获取永久图文消息素材的图文消息实体类
 */
public class News {

    /**
     * 媒体素材Id
     */
    @JSONField(name = "media_id")
    private String mediaId;

    /**
     * 文章列表
     */
    @JSONField(name = "content")
    private ArticleList articleList;

    /**
     * 该图文消息的最后更新时间
     */
    @JSONField(name = "update_time")
    private String updateTime;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public ArticleList getArticleList() {
        return articleList;
    }

    public void setArticleList(ArticleList articleList) {
        this.articleList = articleList;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
