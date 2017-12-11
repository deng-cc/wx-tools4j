package dulk.wx4j.message.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 消息封装类中的filter参数
 */
public class Filter {
    /**
     * 是否群发所有
     */
    @JSONField(name = "is_to_all")
    private boolean toAll;

    /**
     * 标签的id
     * <p>
     * 若isAll为true，则该项可不填写
     * </p>
     */
    @JSONField(name = "tag_id")
    private int tagId;

    public Filter() {
    }

    public Filter(boolean toAll, int tagId) {
        this.toAll = toAll;
        this.tagId = tagId;
    }

    public boolean isToAll() {
        return toAll;
    }

    public void setToAll(boolean toAll) {
        this.toAll = toAll;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
