package dulk.wx4j.material.domain.get;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 图文消息素材列表的实体类
 */
public class NewsList {

    /**
     * 该类型素材的总数
     */
    @JSONField(name = "total_count")
    private int totalCount;

    /**
     * 本次调用获取的素材的数量
     */
    @JSONField(name = "item_count")
    private int itemCount;

    /**
     * 图文消息素材的列表
     */
    @JSONField(name = "item")
    List<News> newsList;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}
