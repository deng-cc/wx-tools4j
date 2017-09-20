package dulk.wx4j.material.domain.upload;


import com.alibaba.fastjson.annotation.JSONField;
import dulk.wx4j.material.domain.upload.Article;

import java.util.List;

/**
 * 上传的永久图文消息的参数的封装类
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
