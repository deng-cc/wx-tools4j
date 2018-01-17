package dulk.wx4j.base.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信服务器发送到我方服务器的请求参数封装类
 * <p>
 * 该类封装了与用于交互时的所有类别消息的微信服务器请求参数，在WxSupport类中会自动处理注入值，
 * 其中根据用户消息的不同，该类的参数也会发生变化，没有的请求参数会是空值
 * </p>
 */
@XStreamAlias("xml")
public class WxRequestParams {

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
     * 消息id
     */
    @XStreamAlias("MsgId")
    private String msgId;

    /**
     * 图片链接
     */
    @XStreamAlias("PicUrl")
    private String picUrl;

    /**
     * 媒体id
     */
    @XStreamAlias("MediaId")
    private String mediaId;

    /**
     * 语音格式
     */
    @XStreamAlias("Format")
    private String format;

    /**
     * 语音消息id
     * <p>
     * 注意，该消息id为适配参数，和之前的msgId在字母大小写上有不同，注意识别
     * </p>
     */
    @XStreamAlias("MsgID")
    private String msgID;

    /**
     * 语音识别结果
     */
    @XStreamAlias("Recognition")
    private String recognition;

    /**
     * 缩略图媒体id
     */
    @XStreamAlias("ThumbMediaId")
    private String thumbMediaId;

    /**
     * 地理位置纬度
     */
    @XStreamAlias("Location_X")
    private String locationX;

    /**
     * 地理位置经度
     */
    @XStreamAlias("Location_Y")
    private String locationY;

    /**
     * 地图缩放大小
     */
    @XStreamAlias("Scale")
    private String scale;

    /**
     * 地理位置信息
     */
    @XStreamAlias("Label")
    private String label;

    /**
     * 链接消息标题
     */
    @XStreamAlias("Title")
    private String title;

    /**
     * 链接消息描述
     */
    @XStreamAlias("Description")
    private String description;

    /**
     * 链接消息的链接
     */
    @XStreamAlias("Url")
    private String url;

    /**
     * 事件类型
     */
    @XStreamAlias("Event")
    private String event;

    /**
     * 事件key值
     */
    @XStreamAlias("EventKey")
    private String eventKey;

    /**
     * 二维码ticket，可用来换取二维码图片
     */
    @XStreamAlias("Ticket")
    private String ticket;

    /**
     * 地理位置纬度
     */
    @XStreamAlias("Latitude")
    private String latitude;

    /**
     * 地理位置经度
     */
    @XStreamAlias("Longitude")
    private String longitude;

    /**
     * 地理位置精度
     */
    @XStreamAlias("Precision")
    private String precision;

    /**
     * 菜单id
     */
    @XStreamAlias("MenuId")
    private String menuId;

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

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getMsgID() {
        return msgID;
    }

    public void setMsgID(String msgID) {
        this.msgID = msgID;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
