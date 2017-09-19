package dulk.wx4j.material.domain.download;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 获取的永久图文消息素材的集合
 */
public class ArticleList {

    /**
     * 文章列表
     */
    @JSONField(name = "news_item")
    private List<Article> articleList;

    /**
     * 创建时间
     */
    @JSONField(name = "create_time")
    private String createTime;

    /**
     * 最后更新时间
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
