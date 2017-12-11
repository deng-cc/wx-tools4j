package dulk.wx4j.message.domain;

import com.alibaba.fastjson.annotation.JSONField;
import dulk.wx4j.message.api.WxMessageAPI;

/**
 * 根据标签群发的图片消息参数封装类
 */
public class PushImageByTag extends PushMessageByTag{

    /**
     * 图片消息
     */
    @JSONField(name = "image")
    private Image image;

    public PushImageByTag() {
    }

    public PushImageByTag(String mediaId, boolean toAll, int tagId) {
        setFilter(new Filter(toAll, tagId));
        setMessageType(WxMessageAPI.SEND_TAG_MSG_TYPE_IMAGE);
        setImage(new Image(mediaId));
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
