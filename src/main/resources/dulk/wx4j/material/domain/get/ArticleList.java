package dulk.wx4j.material.domain.get;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 单条图文消息中文章的列表
 */
public class ArticleList {

    /**
     * 文章列表
     */
    @JSONField(name = "news_item")
    private List<Article> articleList;

    /**
     * 图文消息的创建时间
     */
    @JSONField(name = "create_time")
    private String createTime;

    /**
     * 图文消息的最后更新时间
     */
    @JSONField(name = "update_time")
    private String updateTime;

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
