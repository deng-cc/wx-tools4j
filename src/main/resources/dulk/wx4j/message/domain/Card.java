package dulk.wx4j.message.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 卡券封装类
 */
public class Card {

    /**
     * 卡券id
     */
    @JSONField(name = "card_id")
    private String cardId;

    public Card() {
    }

    public Card(String cardId) {
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
