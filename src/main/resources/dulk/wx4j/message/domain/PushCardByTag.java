package dulk.wx4j.message.domain;

import com.alibaba.fastjson.annotation.JSONField;
import dulk.wx4j.message.api.WxMessageAPI;

/**
 * 根据标签群发的卡券参数封装类
 */
public class PushCardByTag extends PushMessageByTag{

    /**
     * 卡券
     */
    @JSONField(name = "wxcard")
    private Card card;

    public PushCardByTag() {
    }

    public PushCardByTag(String cardId, boolean toAll, int tagId) {
        setFilter(new Filter(toAll, tagId));
        setMessageType(WxMessageAPI.SEND_TAG_MSG_TYPE_CARD);
        setCard(new Card(cardId));
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
