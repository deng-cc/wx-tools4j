package dulk.wx4j.base.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import dulk.wx4j.base.domain.message.Article;
import dulk.wx4j.base.domain.message.Image;
import dulk.wx4j.base.domain.message.Music;
import dulk.wx4j.base.domain.message.Video;
import dulk.wx4j.base.domain.message.Voice;

import java.util.List;

/**
 * WxRequestParams.
 *
 * @author Dulk
 * @version 20170904
 * @date 17-9-4
 */
@XStreamAlias("xml")
public class WxResponseParams {
    @XStreamAlias("ToUserName")
    private String toUserName;

    @XStreamAlias("FromUserName")
    private String fromUserName;

    @XStreamAlias("CreateTime")
    private String createTime;

    @XStreamAlias("MsgType")
    private String msgType;

    @XStreamAlias("Content")
    private String content;

    @XStreamAlias("Image")
    private Image image;

    @XStreamAlias("Voice")
    private Voice voice;

    @XStreamAlias("Video")
    private Video video;

    @XStreamAlias("Music")
    private Music music;

    @XStreamAlias("ArticleCount")
    private String articleCount;

    @XStreamAlias("Articles")
    private List<Article> articles;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
