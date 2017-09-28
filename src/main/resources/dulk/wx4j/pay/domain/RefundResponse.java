package dulk.wx4j.pay.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信退款的响应参数封装类
 */
@XStreamAlias("xml")
public class RefundResponse {

    /**
     * 返回状态码
     * <p>
     * SUCCESS/FAIL
     * </p>
     */
    @XStreamAlias("return_code")
    private String returnCode;

    /**
     * 返回信息
     * <p>
     * 如果非空，则为错误原因
     * </p>
     */
    @XStreamAlias("return_msg")
    private String returnMsg;

    //如下参数在returnCode为SUCCESS时有返回

    /**
     * 申请退款业务结果
     * <p>
     * SUCCESS/FAIL
     * </p>
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
     * 微信订单号
     */
    @XStreamAlias("transaction_id")
    private String wxTradeNo;

    /**
     * 商户订单号
     */
    @XStreamAlias("out_trade_no")
    private String merchantTradeNo;

    /**
     * 微信退款订单号
     */
    @XStreamAlias("refund_id")
    private String wxRefundNo;

    /**
     * 退款金额
     * <p>
     * 单位：分
     * </p>
     */
    @XStreamAlias("refund_fee")
    private int refundFee;

    /**
     * 应结退款金额
     * <p>
     * 单位：分；去掉非充值代金券退款金额后的退款金额
     * </p>
     */
    @XStreamAlias("settlement_refund_fee")
    private int settlementRefundFee;

    /**
     * 订单总金额
     * 单位：分
     */
    @XStreamAlias("total_fee")
    private int totalFee;

    /**
     * 应结订单金额
     * <p>
     * 单位：分；去掉非充值代金券后的订单总金额
     * </p>
     */
    @XStreamAlias("settlement_total_fee")
    private int settlementTotalFee;

    /**
     * 订单金额货币类型
     */
    @XStreamAlias("fee_type")
    private String feeType;

    /**
     * 现金支付金额
     * <p>
     * 单位：分
     * </p>
     */
    @XStreamAlias("cash_fee")
    private int cashFee;

    /**
     * 现金支付币种
     */
    @XStreamAlias("cash_fee_type")
    private String cashFeeType;

    /**
     * 现金退款金额
     */
    @XStreamAlias("cash_refund_fee")
    private int cashRefundFee;

    /**
     * 代金券类型
     * <p>
     * CASH充值代金券；NO_CASH非充值代金券；订单在使用代金券时有返回，$n为下标，从0开始编号，如coupon_type_0
     * </p>
     * <p>
     * 该类默认封装了三张代金券，若有更多封装需求，需要自行添加
     * </p>
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
     * 代金券退款总金额
     * <p>
     * 单位：分
     * </p>
     */
    @XStreamAlias("coupon_refund_fee")
    private int couponRefundFee;

    /**
     * 单个代金券退款金额
     * <p>
     * 单位：分
     * </p>
     */
    @XStreamAlias("coupon_refund_fee_0")
    private int couponRefundFee0;

    /**
     * 单个代金券退款金额
     * <p>
     * 单位：分
     * </p>
     */
    @XStreamAlias("coupon_refund_fee_1")
    private int couponRefundFee1;

    /**
     * 单个代金券退款金额
     * <p>
     * 单位：分
     * </p>
     */
    @XStreamAlias("coupon_refund_fee_2")
    private int couponRefundFee2;

    /**
     * 退款代金券使用数量
     */
    @XStreamAlias("coupon_refund_count")
    private int couponRefundCount;

    /**
     * 退款代金券id
     */
    @XStreamAlias("coupon_refund_id_0")
    private String couponRefundId0;

    /**
     * 退款代金券id
     */
    @XStreamAlias("coupon_refund_id_1")
    private String couponRefundId1;

    /**
     * 退款代金券id
     */
    @XStreamAlias("coupon_refund_id_2")
    private String couponRefundId2;

    @XStreamAlias("out_refund_no")
    private String outRefundNo;

    @XStreamAlias("refund_channel")
    private String refundChannel;

    public String getRefundChannel() {
        return refundChannel;
    }

    public void setRefundChannel(String refundChannel) {
        this.refundChannel = refundChannel;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

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

    public String getWxRefundNo() {
        return wxRefundNo;
    }

    public void setWxRefundNo(String wxRefundNo) {
        this.wxRefundNo = wxRefundNo;
    }

    public int getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(int refundFee) {
        this.refundFee = refundFee;
    }

    public int getSettlementRefundFee() {
        return settlementRefundFee;
    }

    public void setSettlementRefundFee(int settlementRefundFee) {
        this.settlementRefundFee = settlementRefundFee;
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

    public int getCashRefundFee() {
        return cashRefundFee;
    }

    public void setCashRefundFee(int cashRefundFee) {
        this.cashRefundFee = cashRefundFee;
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

    public int getCouponRefundFee() {
        return couponRefundFee;
    }

    public void setCouponRefundFee(int couponRefundFee) {
        this.couponRefundFee = couponRefundFee;
    }

    public int getCouponRefundFee0() {
        return couponRefundFee0;
    }

    public void setCouponRefundFee0(int couponRefundFee0) {
        this.couponRefundFee0 = couponRefundFee0;
    }

    public int getCouponRefundFee1() {
        return couponRefundFee1;
    }

    public void setCouponRefundFee1(int couponRefundFee1) {
        this.couponRefundFee1 = couponRefundFee1;
    }

    public int getCouponRefundFee2() {
        return couponRefundFee2;
    }

    public void setCouponRefundFee2(int couponRefundFee2) {
        this.couponRefundFee2 = couponRefundFee2;
    }

    public int getCouponRefundCount() {
        return couponRefundCount;
    }

    public void setCouponRefundCount(int couponRefundCount) {
        this.couponRefundCount = couponRefundCount;
    }

    public String getCouponRefundId0() {
        return couponRefundId0;
    }

    public void setCouponRefundId0(String couponRefundId0) {
        this.couponRefundId0 = couponRefundId0;
    }

    public String getCouponRefundId1() {
        return couponRefundId1;
    }

    public void setCouponRefundId1(String couponRefundId1) {
        this.couponRefundId1 = couponRefundId1;
    }

    public String getCouponRefundId2() {
        return couponRefundId2;
    }

    public void setCouponRefundId2(String couponRefundId2) {
        this.couponRefundId2 = couponRefundId2;
    }
}
