package dulk.wx4j.pay.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import dulk.wx4j.base.api.WxConfig;
import dulk.wx4j.base.util.XmlUtil;
import dulk.wx4j.pay.util.SignUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.TreeMap;

/**
 * 请求统一下单接口时的参数封装类
 */
@XStreamAlias("xml")
public abstract class UnifiedorderRequest {

    //必需参数

    /**
     * 公众号应用id
     */
    @XStreamAlias("appid")
    private String appId = WxConfig.getAppId();

    /**
     * 商户id
     */
    @XStreamAlias("mch_id")
    private String merchantId = WxConfig.getMerchantId();

    /**
     * 终端设备号
     * <p>
     * 门店号或收银设备id，pc网页或公众号内支付传"WEB"
     * </p>
     */
    @XStreamAlias("device_info")
    private String deviceInfo;

    /**
     * 随机字符串
     * <p>
     * 随机字符串，不长于32位；可以调用SignUtil工具类生成
     * </p>
     */
    @XStreamAlias("nonce_str")
    private String nonceStr = SignUtil.createNonceStr();

    /**
     * 签名
     * <p>
     * 通过签名生成算法得到；可以调用SignUtil工具类生成
     * </p>
     */
    @XStreamAlias("sign")
    private String sign;

    /**
     * 商品的简单描述
     * <p>
     * 该字段必须严格按照规范传递，具体参考https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=4_2
     * </p>
     */
    @XStreamAlias("body")
    private String body;

    /**
     * 商户系统内部订单号
     * <p>
     * 商户订单号，要求必须保持系统内部的唯一性，32个字符，可包含字母。
     * 官方建议根据当前系统时间加随机序列来生成订单号。
     * </p>
     */
    @XStreamAlias("out_trade_no")
    private String merchantTradeNo;

    /**
     * 总金额
     * <p>
     * 订单总金额，单位为分
     * </p>
     */
    @XStreamAlias("total_fee")
    private int totalFee;

    /**
     * 通知地址
     * <p>
     * 接收微信支付异步通知的回调地址（即支付完成后，微信会带着支付结果请求该地址），必须为直接可访问的url，不能携带参数
     * </p>
     */
    @XStreamAlias("notify_url")
    private String notifyUrl;

    /**
     * 交易类型
     * <p>
     * 二维码支付为NATIVE，公众号内支付为JSAPI，APP支付为APP
     * </p>
     */
    @XStreamAlias("trade_type")
    private String tradeType;


    //非必需参数

    /**
     * 签名类型
     * <p>
     * 目前支持HMAC-SHA256和MD5，默认为MD5
     * </p>
     */
    @XStreamAlias("sign_type")
    private String signType = "MD5";

    /**
     * 商品详情
     */
    @XStreamAlias("detail")
    private String detail;

    /**
     * 附加数据
     * <p>
     *     附加数据，该字段在查询API和支付通知中会原样返回，该字段主要用于商户携带订单的自定义数据。有长度限制，String(127)
     * </p>
     */
    @XStreamAlias("attach")
    private String attach;

    /**
     * 货币类型
     * <p>符合ISO4217标准的三位字母代码，默认人民币CNY</p>
     */
    @XStreamAlias("fee_type")
    private String feeType = "CNY";

    /**
     * 终端IP
     * <p>
     * H5支付要求商户在统一下单接口中上传用户真实ip地址
     * </p>
     */
    @XStreamAlias("spbill_create_ip")
    private String clientIp;

    /**
     * 交易起始时间
     * <p>
     * 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒，则为20091225091010
     * </p>
     */
    @XStreamAlias("time_start")
    private String timeStart;

    /**
     * 交易结束时间
     * <p>
     * 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒，则为20091225091010。最短失效时间间隔必须大于5分钟。
     * </p>
     */
    @XStreamAlias("time_expire")
    private String timeExpire;

    /**
     * 商品标记
     * <p>
     * 代金券或立减优惠功能的参数，详见https://pay.weixin.qq.com/wiki/doc/api/tools/sp_coupon.php?chapter=12_1
     * </p>
     */
    @XStreamAlias("goods_tag")
    private String goodsTag;

    /**
     * 商品id
     * <p>
     * 二维码扫码支付时，即交易类型为二维码时，该参数必传
     * </p>
     */
    @XStreamAlias("product_id")
    private String productId;

    /**
     * 指定支付方式
     */
    @XStreamAlias("limit_pay")
    private String limitPay;

    /**
     * 用户标识
     * <p>
     * 当交易类型为公众号内支付时，此参数必传
     * </p>
     */
    @XStreamAlias("openid")
    private String openId;

    /**
     * 场景信息
     * <p>
     * 该字段用于上报支付的场景信息（IOS移动应用、安卓移动应用、WAP网站应用）
     * </p>
     */
    @XStreamAlias("scene_info")
    private String sceneInfo;

    public UnifiedorderRequest(String body, int totalFee, String merchantTradeNo, String notifyUrl) {
        this.body = body;
        this.totalFee = totalFee;
        this.merchantTradeNo = merchantTradeNo;
        this.notifyUrl = notifyUrl;
    }

    /**
     * 将对象中不为空的属性和值转换为TreeMap以进行签名运算
     * <p>
     * 该方法以key：value键值对方式存储对象的属性和值，其中key并非属性名，而是其XStreamAlias注解值。
     * 注意：sign属性不参与转换，因为在后续的签名算法中不需要sign
     * </p>
     *
     * @return 以属性：值存储的TreeMap
     */
    private TreeMap<String, String> toTreeMap() {
        TreeMap<String, String> map = new TreeMap<String, String>();
        Field[] fields = this.getClass().getSuperclass().getDeclaredFields();
        for (Field field : fields) {
            try {
                Object value = field.get(this);
                if (value != null && !"sign".equals(field.getName()) && field.isAnnotationPresent(XStreamAlias.class)) {
                    String name = field.getAnnotation(XStreamAlias.class).value();
                    map.put(name, value.toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 将统一下单参数封装类，转换为带签名的xml
     * <p>
     * 调用统一下单接口时，需要先将其他参数进行签名，再将结果同时作为参数之一最终转换为xml
     * </p>
     *
     * @return 统一下单请求参数的xml格式字符串（含签名）
     */
    public String toSignedXml() {
        setSign(SignUtil.createSign(this.toTreeMap()));
        return XmlUtil.toXmlCDATA(this);
    }



    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMerchantTradeNo() {
        return merchantTradeNo;
    }

    public void setMerchantTradeNo(String merchantTradeNo) {
        this.merchantTradeNo = merchantTradeNo;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(String sceneInfo) {
        this.sceneInfo = sceneInfo;
    }

}
