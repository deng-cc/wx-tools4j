package dulk.wx4j.material.domain.get;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 其他素材列表的实体类
 */
public class MediaList {

    /**
     * 该类型的媒体总数
     */
    @JSONField(name = "total_count")
    private String totalCount;

    /**
     * 本次调用获取的美的数量
     */
    @JSONField(name = "item_count")
    private String itemCount;

    /**
     * 媒体实体类的集合
     */
    @JSONField(name = "item")
    private List<Media> mediaList;


    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }
}
