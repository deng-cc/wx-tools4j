package dulk.wx4j.message.domain;

import com.alibaba.fastjson.annotation.JSONField;
import dulk.wx4j.message.api.WxMessageAPI;

/**
 * 根据标签群发的语音/音频消息参数封装类
 */
public class PushVoiceByTag extends PushMessageByTag{

    /**
     * 语音消息
     */
    @JSONField(name = "voice")
    private Voice voice;

    public PushVoiceByTag() {
    }

    public PushVoiceByTag(String mediaId, boolean toAll, int tagId) {
        setFilter(new Filter(toAll, tagId));
        setMessageType(WxMessageAPI.SEND_TAG_MSG_TYPE_VOICE);
        setVoice(new Voice(mediaId));
    }

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }
}
