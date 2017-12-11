package dulk.wx4j.message.service;

import com.alibaba.fastjson.JSON;
import dulk.wx4j.base.exception.WxException;
import dulk.wx4j.base.util.NetUtil;
import dulk.wx4j.message.api.WxMessageAPI;
import dulk.wx4j.message.domain.PushMessageByTag;
import org.apache.log4j.Logger;

/**
 * 微信消息相关的服务类
 */
public class WxMessageService {

    private static Logger log = Logger.getLogger(WxMessageService.class);

    /**
     * 根据标签群发消息
     * <p>
     * todo 群发音乐的post格式未在文档找到
     * </p>
     *
     * @param message 消息封装类（请传入PushMessageByTag的具体子类）
     * @throws WxException
     */
    public static void sendAllByTag(PushMessageByTag message) throws WxException {
        String content = JSON.toJSONString(message);
        NetUtil.sendRequestPOST(WxMessageAPI.getUrl_sendAllByTag(), content);
    }


}
