package dulk.wx4j.material.domain.upload;


import com.alibaba.fastjson.annotation.JSONField;

/**
 * 上传的永久图文消息中文章的实体类
 */
public class Article {

    //必需参数

    /**
     * 标题
     */
    @JSONField(name = "title")
    private String title;

    /**
     * 封面素材id
     * <p>必须为永久素材id</p>
     */
    @JSONField(name = "thumb_media_id")
    private String thumb_mediaId;

    /**
     * 是否显示封面图片
     * <p>这里是指是否将封面图片在文章内容中也显示出来</p>
     * <p>1为显示，0为不显示</p>
     */
    @JSONField(name = "show_cover_pic")
    private String showCover;

    /**
     * 图文消息内容
     * <p>支持HTML，必须少于2w字符，小于1m，会过滤js和外部图片url，图片只能使用"上传图文消息内图片接口"获取的url</p>
     */
    @JSONField(name = "content")
    private String content;

    /**
     * 原文地址
     * <p>点"阅读原文"后的url</p>
     */
    @JSONField(name = "content_source_url")
    private String contentSrcUrl;



    //非必需参数

    /**
     * 作者
     */
    @JSONField(name = "author")
    private String author;

    /**
     * 摘要
     * <p>不填则默认抓取正文前64个字</p>
     */
    @JSONField(name = "digest")
    private String digest;

    public Article(String title, String thumb_mediaId, boolean isShowCover, String content, String contentSrcUrl) {
        this.title = title;
        this.thumb_mediaId = thumb_mediaId;
        if (isShowCover) {
            this.showCover = "1";
        } else {
            this.showCover = "0";
        }
        this.content = content;
        this.contentSrcUrl = contentSrcUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb_mediaId() {
        return thumb_mediaId;
    }

    public void setThumb_mediaId(String thumb_mediaId) {
        this.thumb_mediaId = thumb_mediaId;
    }

    public String getShowCover() {
        return showCover;
    }

    public void setShowCover(String showCover) {
        this.showCover = showCover;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentSrcUrl() {
        return contentSrcUrl;
    }

    public void setContentSrcUrl(String contentSrcUrl) {
        this.contentSrcUrl = contentSrcUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }
}
