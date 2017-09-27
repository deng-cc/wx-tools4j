package dulk.wx4j.pay.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import dulk.wx4j.pay.api.TradeType;

/**
 * 原生扫码（NATIVE）微信支付的统一下单参数封装类
 */
@XStreamAlias("xml")
public class PayRequestNATIVE extends UnifiedorderRequest {

    /**
     * NATIVE方式请求统一下单接口的参数类构造函数
     *
     * @param body            商品描述
     * @param totalFee        总价，单位分，币种默认人民币CNY
     * @param merchantTradeNo 商户内部系统的订单号
     * @param notifyUrl       支付回调地址url
     */
    public PayRequestNATIVE(String body, int totalFee, String merchantTradeNo, String notifyUrl) {
        super(body, totalFee, merchantTradeNo, notifyUrl);
        setDeviceInfo("QR");
        setTradeType(TradeType.NATIVE.getValue());
    }

}
