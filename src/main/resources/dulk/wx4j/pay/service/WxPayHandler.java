package dulk.wx4j.pay.service;

import dulk.wx4j.pay.domain.PayResult;

/**
 * 微信支付的处理接口
 * <p>
 * 该接口定义了微信支付之后所需要做出的处理方法
 * </p>
 */
public interface WxPayHandler {

    /**
     * 支付成功后的业务处理
     *
     * @param payResult 微信反馈的支付结果
     */
    public abstract void doWithSuccess(PayResult payResult);

    /**
     * 支付失败后的业务处理
     *
     * @param payResult 微信反馈的支付结果
     */
    public abstract void doWithFail(PayResult payResult);
}
