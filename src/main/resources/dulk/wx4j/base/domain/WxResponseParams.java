package dulk.wx4j.base.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import dulk.wx4j.base.domain.message.Article;
import dulk.wx4j.base.domain.message.Image;
import dulk.wx4j.base.domain.message.Music;
import dulk.wx4j.base.domain.message.Video;
import dulk.wx4j.base.domain.message.Voice;

import java.util.List;

/**
 * 我方服务器需要响应给微信服务器的响应参数封装类
 * <p>
 * 该类封装了与用于交互时的所有类别消息的微信服务器响应参数，通过调用WxSupport类中responseXxx方法进行属性的注入，
 * 其中根据响应消息的不同，该类的参数也会发生变化，有的参数会是空值
 * </p>
 */
@XStreamAlias("xml")
public class WxResponseParams {

    /**
     * 接收方
     */
    @XStreamAlias("ToUserName")
    private String toUserName;

    /**
     * 发送方
     */
    @XStreamAlias("FromUserName")
    private String fromUserName;

    /**
     * 消息生成时间
     */
    @XStreamAlias("CreateTime")
    private String createTime;

    /**
     * 消息类型
     */
    @XStreamAlias("MsgType")
    private String msgType;

    /**
     * 文本消息内容
     */
    @XStreamAlias("Content")
    private String content;

    /**
     * 图片消息响应参数封装类
     */
    @XStreamAlias("Image")
    private Image image;

    /**
     * 语音消息响应参数封装类
     */
    @XStreamAlias("Voice")
    private Voice voice;

    /**
     * 视频消息响应参数封装类
     */
    @XStreamAlias("Video")
    private Video video;

    /**
     * 音乐消息响应参数封装类
     */
    @XStreamAlias("Music")
    private Music music;

    /**
     * 文章数量
     */
    @XStreamAlias("ArticleCount")
    private String articleCount;

    /**
     * 文章集合的参数封装类
     */
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
