package dulk.wx4j.message.domain;

import com.alibaba.fastjson.annotation.JSONField;
import dulk.wx4j.message.api.WxMessageAPI;

/**
 * 根据标签群发的图文消息参数封装类
 */
public class PushNewsByTag extends PushMessageByTag{

    /**
     * 图文消息
     */
    @JSONField(name = "mpnews")
    private News news;

    /**
     * 图文消息判定为转载时，是否继续群发
     * <p>
     * 为保护原创，图文消息在发送前，微信会对其在原创库中进行判定，如若判定文章为非原创，会以转载的形式发送。
     * 当图文消息判定为转载时，该属性用以确定时继续群发，还是停止群发，1为继续群发，0为停止群发，默认参数为0。
     * </p>
     */
    @JSONField(name = "send_ignore_reprint")
    private int sendReprint = 0;

    public PushNewsByTag() {
    }

    public PushNewsByTag(String mediaId, boolean toAll, int tagId) {
        setFilter(new Filter(toAll, tagId));
        setMessageType(WxMessageAPI.SEND_TAG_MSG_TYPE_NEWS);
        setNews(new News(mediaId));
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public int getSendReprint() {
        return sendReprint;
    }

    public void setSendReprint(int sendReprint) {
        this.sendReprint = sendReprint;
    }
}
