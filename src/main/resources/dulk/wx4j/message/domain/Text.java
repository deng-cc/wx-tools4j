package dulk.wx4j.message.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 文本消息封装类
 */
public class Text {

    /**
     * 文本内容
     */
    @JSONField(name = "content")
    private String content;

    public Text() {
    }

    public Text(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
