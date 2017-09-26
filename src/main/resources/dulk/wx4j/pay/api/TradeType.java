package dulk.wx4j.pay.api;

/**
 * 微信支付的交易类型
 */
public enum TradeType {

    /**
     * 公众号内支付
     */
    JSAPI("JSAPI"),
    /**
     * 原生扫码支付
     */
    NATIVE("NATIVE"),
    /**
     * app支付
     */
    APP("APP");

    private String value;

    TradeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
