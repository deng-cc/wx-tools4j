package dulk.wx4j.message.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 根据标签群发消息的封装类
 */
public class PushMessageByTag extends PushMessage {

    /**
     * 设定消息的接收者
     */
    @JSONField(name = "filter")
    private Filter filter;

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
