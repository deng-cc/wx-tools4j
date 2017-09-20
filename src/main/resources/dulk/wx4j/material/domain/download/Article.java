package dulk.wx4j.material.domain.download;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 获取的永久图文素材中文章的封装类
 * <p>
 * 该类是用于获取到的永久图文素材中，每条文章的信息封装类，该类作为同包中ArticleList类的属性
 * </p>
 */
public class Article {

    /**
     * 标题
     */
    @JSONField(name = "title")
    private String title;

    /**
     * 缩略图媒体Id
     */
    @JSONField(name = "thumb_media_id")
    private String thumbMediaId;

    /**
     * 缩略图的url
     */
    @JSONField(name = "thumb_url")
    private String thumbUrl;

    /**
     * 是否在文章中显示封面图片
     * <p>
     * 是为1，否为0
     * </p>
     */
    @JSONField(name = "show_cover_pic")
    private String isShowCoverPic;

    /**
     * 作者
     */
    @JSONField(name = "author")
    private String author;

    /**
     * 摘要
     */
    @JSONField(name = "digest")
    private String digest;

    /**
     * 内容，支持html
     */
    @JSONField(name = "content")
    private String content;

    /**
     * 图文页文章的url
     */
    @JSONField(name = "url")
    private String url;

    /**
     * 阅读原文的url
     */
    @JSONField(name = "content_source_url")
    private String contentSrcUrl;

    /**
     * 是否公开评论
     * <p>
     * 1为是，0为否
     * </p>
     */
    @JSONField(name = "need_open_comment")
    private String isNeedOpenComment;

    /**
     * 是否只有关注者可以评论
     * <p>
     * 1为是，0为否
     * </p>
     */
    @JSONField(name = "only_fans_can_comment")
    private String isOnlyFansCanComment;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getShowCoverPic() {
        return isShowCoverPic;
    }

    public void setShowCoverPic(String showCoverPic) {
        isShowCoverPic = showCoverPic;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentSrcUrl() {
        return contentSrcUrl;
    }

    public void setContentSrcUrl(String contentSrcUrl) {
        this.contentSrcUrl = contentSrcUrl;
    }

    public String getNeedOpenComment() {
        return isNeedOpenComment;
    }

    public void setNeedOpenComment(String needOpenComment) {
        isNeedOpenComment = needOpenComment;
    }

    public String getOnlyFansCanComment() {
        return isOnlyFansCanComment;
    }

    public void setOnlyFansCanComment(String onlyFansCanComment) {
        isOnlyFansCanComment = onlyFansCanComment;
    }
}
