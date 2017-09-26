package dulk.wx4j.pay.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 统一下单的响应参数封装类
 */
@XStreamAlias("xml")
public class UnifiedorderResponse {

    /**
     * 通信标识
     * <p>
     * 通信标识，非交易标识：SUCCESS/FAIL
     * </p>
     */
    @XStreamAlias("return_code")
    private String returnCode;

    /**
     * 返回信息
     * <p>
     * 如非空，为错误原因签名失败
     * </p>
     */
    @XStreamAlias("return_msg")
    private String returnMsg;

    /**
     * 调用接口提交的公众账号id
     */
    @XStreamAlias("appid")
    private String appId;

    /**
     * 调用接口提交的商户号
     */
    @XStreamAlias("mch_id")
    private String merchantId;

    /**
     * 请求支付的终端设备号
     */
    @XStreamAlias("device_info")
    private String deviceInfo;

    /**
     * 微信返回的随机字符串
     */
    @XStreamAlias("nonce_str")
    private String nonceStr;

    /**
     * 微信返回的签名值
     */
    @XStreamAlias("sign")
    private String sign;

    /**
     * 业务结果，交易标识
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
     * 错误信息描述
     */
    @XStreamAlias("err_code_des")
    private String errCodeDesc;

    //以下字段在returnCode和resultCode都为SUCCESS时有返回

    /**
     * 交易类型
     */
    @XStreamAlias("trade_type")
    private String tradeType;

    /**
     * 预支付会话标识
     * <p>
     * 该标识用于后续接口调用中使用，有效期为2h
     * </p>
     */
    @XStreamAlias("prepay_id")
    private String prepayId;

    /**
     * 二维码链接
     * <p>
     * 在交易类型为NATIVE，即原生扫码支付时有返回，用于生成二维码
     * </p>
     */
    @XStreamAlias("code_url")
    private String codeUrl;

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

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }
}
