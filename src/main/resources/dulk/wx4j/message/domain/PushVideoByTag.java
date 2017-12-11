package dulk.wx4j.message.domain;

import com.alibaba.fastjson.annotation.JSONField;
import dulk.wx4j.message.api.WxMessageAPI;

/**
 * 根据标签群发的视频消息参数封装类
 */
public class PushVideoByTag extends PushMessageByTag{

    /**
     * 视频消息
     */
    @JSONField(name = "mpvideo")
    private Video video;

    public PushVideoByTag() {
    }

    public PushVideoByTag(String mediaId, boolean toAll, int tagId) {
        setFilter(new Filter(toAll, tagId));
        setMessageType(WxMessageAPI.SEND_TAG_MSG_TYPE_VIDEO);
        setVideo(new Video(mediaId));
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
