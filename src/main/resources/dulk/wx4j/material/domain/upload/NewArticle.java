package dulk.wx4j.material.domain.upload;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 修改永久图文素材的参数的封装类
 */
public class NewArticle {

    /**
     * 图文素材的媒体id
     */
    @JSONField(name = "media_id")
    private String mediaId;

    /**
     * 文章在图文消息中的位置，第一篇为0
     */
    @JSONField(name = "index")
    private int index;

    /**
     * 新的文章
     */
    @JSONField(name = "articles")
    private Article article;

    public NewArticle(String mediaId, int index, Article article) {
        this.mediaId = mediaId;
        this.index = index;
        this.article = article;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
