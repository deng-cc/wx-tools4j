package dulk.wx4j.material.domain;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 图文消息的实体类
 */
public class News {

    @JSONField(name = "articles")
    private List<Article> articles;

    public News(List<Article> articles) {
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
