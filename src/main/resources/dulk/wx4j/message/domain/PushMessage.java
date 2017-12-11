package dulk.wx4j.message.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 群发消息的基类
 */
public class PushMessage {

    /**
     * 消息类型
     */
    @JSONField(name = "msgtype")
    private String MessageType;

    public String getMessageType() {
        return MessageType;
    }

    public void setMessageType(String messageType) {
        MessageType = messageType;
    }
}
