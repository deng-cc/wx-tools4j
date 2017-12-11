package dulk.wx4j.message.domain;

import com.alibaba.fastjson.annotation.JSONField;
import dulk.wx4j.message.api.WxMessageAPI;

/**
 * 根据标签群发的文本消息参数封装类
 */
public class PushTextByTag extends PushMessageByTag{

    /**
     * 文本消息
     */
    @JSONField(name = "text")
    private Text text;

    public PushTextByTag() {
    }

    public PushTextByTag(String content, boolean toAll, int tagId) {
        setFilter(new Filter(toAll, tagId));
        setMessageType(WxMessageAPI.SEND_TAG_MSG_TYPE_TEXT);
        setText(new Text(content));
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }
}
