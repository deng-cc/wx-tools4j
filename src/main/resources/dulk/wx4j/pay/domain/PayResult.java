package dulk.wx4j.pay.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信支付结果的响应参数封装类
 * <p>
 * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7
 * </p>
 */
@XStreamAlias("xml")
public class PayResult extends BaseParam{

    /**
     * 通信标志返回状态码
     */
    @XStreamAlias("return_code")
    private String returnCode;

    /**
     * 返回信息
     */
    @XStreamAlias("return_msg")
    private String returnMsg;

    /**
     * 公众账号id
     */
    @XStreamAlias("appid")
    private String appId;

    /**
     * 商户号
     */
    @XStreamAlias("mch_id")
    private String merchantId;

    /**
     * 设备号
     */
    @XStreamAlias("device_info")
    private String deviceInfo;

    /**
     * 随机字符串
     */
    @XStreamAlias("nonce_str")
    private String nonceStr;

    /**
     * 签名
     */
    @XStreamAlias("sign")
    private String sign;

    /**
     * 签名类型
     */
    @XStreamAlias("sign_type")
    private String signType;

    /**
     * 业务结果
     */
    @XStreamAlias("result_code")
    private String resultCode;

    /**
     * 错误代码
     */
    @XStreamAlias("err_code")
    private String errCode;

    /**
     * 错误代码描述
     */
    @XStreamAlias("err_code_des")
    private String errCodeDesc;

    /**
     * 用户标识
     */
    @XStreamAlias("openid")
    private String openId;

    /**
     * 用户是否关注公众账号
     */
    @XStreamAlias("is_subscribe")
    private String subscribed;

    /**
     * 交易类型
     */
    @XStreamAlias("trade_type")
    private String tradeType;

    /**
     * 付款银行
     */
    @XStreamAlias("bank_type")
    private String bankType;

    /**
     * 订单金额
     */
    @XStreamAlias("total_fee")
    private int totalFee;

    /**
     * 应结订单金额
     */
    @XStreamAlias("settlement_total_fee")
    private int settlementTotalFee;

    /**
     * 货币种类
     */
    @XStreamAlias("fee_type")
    private String feeType;

    /**
     * 现金支付金额
     */
    @XStreamAlias("cash_fee")
    private int cashFee;

    /**
     * 现金支付货币类型
     */
    @XStreamAlias("cash_fee_type")
    private String cashFeeType;

    /**
     * 总代金券金额
     */
    @XStreamAlias("coupon_fee")
    private int couponFee;

    /**
     * 代金券使用数量
     */
    @XStreamAlias("coupon_count")
    private int couponCount;

    /**
     * 代金券类型
     */
    @XStreamAlias("coupon_type_0")
    private String couponType0;

    /**
     * 代金券类型
     */
    @XStreamAlias("coupon_type_1")
    private String couponType1;

    /**
     * 代金券类型
     */
    @XStreamAlias("coupon_type_2")
    private String couponType2;

    /**
     * 代金券id
     */
    @XStreamAlias("coupon_id_0")
    private String couponId0;

    /**
     * 代金券id
     */
    @XStreamAlias("coupon_id_1")
    private String couponId1;

    /**
     * 代金券id
     */
    @XStreamAlias("coupon_id_2")
    private String couponId2;

    /**
     * 单个代金券支付金额
     */
    @XStreamAlias("coupon_fee_0")
    private int couponFee0;

    /**
     * 单个代金券支付金额
     */
    @XStreamAlias("coupon_fee_1")
    private int couponFee1;

    /**
     * 单个代金券支付金额
     */
    @XStreamAlias("coupon_fee_2")
    private int couponFee2;

    /**
     * 微信支付订单号
     */
    @XStreamAlias("transaction_id")
    private String wxTradeNo;

    /**
     * 商户订单号
     */
    @XStreamAlias("out_trade_no")
    private String merchantTradeNo;

    /**
     * 商家附属数据包
     */
    @XStreamAlias("attach")
    private String attach;

    /**
     * 支付完成时间
     */
    @XStreamAlias("time_end")
    private String timeEnd;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
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

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDesc() {
        return errCodeDesc;
    }

    public void setErrCodeDesc(String errCodeDesc) {
        this.errCodeDesc = errCodeDesc;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(String subscribed) {
        this.subscribed = subscribed;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public int getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public void setSettlementTotalFee(int settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public int getCashFee() {
        return cashFee;
    }

    public void setCashFee(int cashFee) {
        this.cashFee = cashFee;
    }

    public String getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    public int getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(int couponFee) {
        this.couponFee = couponFee;
    }

    public int getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(int couponCount) {
        this.couponCount = couponCount;
    }

    public String getCouponType0() {
        return couponType0;
    }

    public void setCouponType0(String couponType0) {
        this.couponType0 = couponType0;
    }

    public String getCouponType1() {
        return couponType1;
    }

    public void setCouponType1(String couponType1) {
        this.couponType1 = couponType1;
    }

    public String getCouponType2() {
        return couponType2;
    }

    public void setCouponType2(String couponType2) {
        this.couponType2 = couponType2;
    }

    public String getCouponId0() {
        return couponId0;
    }

    public void setCouponId0(String couponId0) {
        this.couponId0 = couponId0;
    }

    public String getCouponId1() {
        return couponId1;
    }

    public void setCouponId1(String couponId1) {
        this.couponId1 = couponId1;
    }

    public String getCouponId2() {
        return couponId2;
    }

    public void setCouponId2(String couponId2) {
        this.couponId2 = couponId2;
    }

    public int getCouponFee0() {
        return couponFee0;
    }

    public void setCouponFee0(int couponFee0) {
        this.couponFee0 = couponFee0;
    }

    public int getCouponFee1() {
        return couponFee1;
    }

    public void setCouponFee1(int couponFee1) {
        this.couponFee1 = couponFee1;
    }

    public int getCouponFee2() {
        return couponFee2;
    }

    public void setCouponFee2(int couponFee2) {
        this.couponFee2 = couponFee2;
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

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
}
