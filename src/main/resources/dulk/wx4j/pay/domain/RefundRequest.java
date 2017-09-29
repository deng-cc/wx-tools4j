package dulk.wx4j.pay.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import dulk.wx4j.base.api.WxConfig;
import dulk.wx4j.pay.util.SignUtil;

/**
 * 申请退款的请求参数封装类
 */
@XStreamAlias("xml")
public class RefundRequest extends BaseParam {

    /**
     * 公众账号id
     */
    @XStreamAlias("appid")
    private String appId = WxConfig.getAppId();

    /**
     * 商户号
     */
    @XStreamAlias("mch_id")
    private String merchantId = WxConfig.getMerchantId();

    /**
     * 随机字符串
     */
    @XStreamAlias("nonce_str")
    private String nonceStr = SignUtil.createNonceStr();

    /**
     * 签名
     */
    @XStreamAlias("sign")
    private String sign;

    /**
     * 签名类型（非必需参数）
     * <p>
     * 支持HMAC-SHA256和MD5，默认为MD5
     * </p>
     */
    @XStreamAlias("sign_type")
    private String signType = "MD5";

    /**
     * 微信订单号
     * <p>
     * 微信订单号和商户订单号择一即可
     * </p>
     */
    @XStreamAlias("transaction_id")
    private String wxTradeNo;

    /**
     * 商户订单号
     * <p>
     * 微信订单号和商户订单号择一即可
     * </p>
     */
    @XStreamAlias("out_trade_no")
    private String merchantTradeNo;

    /**
     * 商户退款单号
     * <p>
     * 商户系统内部的退款订单号，要求32个字符内，只能是数字、大小写字母或_-|*@，且同个商户号下唯一
     * </p>
     */
    @XStreamAlias("out_refund_no")
    private String merchantRefundNo;

    /**
     * 订单总金额
     * <p>
     * 单位：分
     * </p>
     */
    @XStreamAlias("total_fee")
    private int totalFee;

    /**
     * 退款金额
     * <p>
     * 单位：分
     * </p>
     */
    @XStreamAlias("refund_fee")
    private int refundFee;

    /**
     * 退款币种（非必需参数）
     * <p>
     * 符合ISO 4217标准的三位字母代码，默认人民币：CNY
     * </p>
     */
    @XStreamAlias("refund_fee_type")
    private String refundFeeType = "CNY";

    /**
     * 退款原因描述（非必需参数）
     */
    @XStreamAlias("refund_desc")
    private String refundDesc;

    /**
     * 退款资金来源（非必需参数）
     * <p>
     * REFUND_SOURCE_UNSETTLED_FUNDS（未结算资金退款）和REFUND_SOURCE_RECHARGE_FUNDS（可用余额退款），默认前者
     * </p>
     */
    @XStreamAlias("refund_account")
    private String refundAccount;

    /**
     * 以微信订单号为退款的请求参数类构造函数
     *
     * @param wxTradeNo        微信订单号
     * @param merchantRefundNo 商户退款订单号
     * @param totalFee         订单的总金额
     * @param refundFee        申请退款的金额
     */
    public RefundRequest(String wxTradeNo, String merchantRefundNo, int totalFee, int refundFee) {
        this.wxTradeNo = wxTradeNo;
        this.merchantRefundNo = merchantRefundNo;
        this.totalFee = totalFee;
        this.refundFee = refundFee;
    }

    /**
     * 以商户订单号为退款的请求参数类构造函数
     *
     * @param totalFee         订单的总金额
     * @param refundFee        申请退款的金额
     * @param merchantTradeNo  商户订单号
     * @param merchantRefundNo 商户退款订单号
     */
    public RefundRequest(int totalFee, int refundFee, String merchantTradeNo, String merchantRefundNo) {
        this.merchantTradeNo = merchantTradeNo;
        this.merchantRefundNo = merchantRefundNo;
        this.totalFee = totalFee;
        this.refundFee = refundFee;
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

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getWxTradeNo() {
        return wxTradeNo;
    }

    public void setWxTradeNo(String wxTradeNo) {
        this.wxTradeNo = wxTradeNo;
    }

    public String getMerchantTradeNo() {
        return merchantTradeNo;
    }

    public void setMerchantTradeNo(String merchantTradeNo) {
        this.merchantTradeNo = merchantTradeNo;
    }

    public String getMerchantRefundNo() {
        return merchantRefundNo;
    }

    public void setMerchantRefundNo(String merchantRefundNo) {
        this.merchantRefundNo = merchantRefundNo;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public int getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(int refundFee) {
        this.refundFee = refundFee;
    }

    public String getRefundFeeType() {
        return refundFeeType;
    }

    public void setRefundFeeType(String refundFeeType) {
        this.refundFeeType = refundFeeType;
    }

    public String getRefundDesc() {
        return refundDesc;
    }

    public void setRefundDesc(String refundDesc) {
        this.refundDesc = refundDesc;
    }

    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }
}
