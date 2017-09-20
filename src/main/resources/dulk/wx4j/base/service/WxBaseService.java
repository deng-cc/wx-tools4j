package dulk.wx4j.base.service;

import dulk.wx4j.base.api.WxBaseAPI;
import dulk.wx4j.base.api.WxConfig;
import dulk.wx4j.base.exception.WxException;
import dulk.wx4j.base.util.NetUtil;
import dulk.wx4j.base.util.SignUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微信的基本服务类
 * <p>
 * 该服务类主要用于微信最基本的接口调用凭证获取，和微信服务器的请求验证
 * </p>
 */
public class WxBaseService {
    private static Logger log = Logger.getLogger(WxBaseService.class);

    /**
     * 立即刷新获取一个新的accessToken
     *
     * @return accessToken值
     */
    public static String getNewAccessToken() throws WxException {
        String token = NetUtil.sendRequestGET(WxBaseAPI.getUrl_getAccessToken()).getString("access_token");
        WxConfig.setAccessToken(token);

        return token;
    }

    /**
     * 微信服务器校验
     * <p>
     * 开发者对请求进行校验，确认该请求来自微信服务器
     * </p>
     *
     * @param request  http请求
     * @param response http响应
     * @return
     */
    public static boolean wxServerConfirm(HttpServletRequest request, HttpServletResponse response) {
        Boolean result = false;
        if ("GET".equals(request.getMethod())) {
            //微信加密签名
            String signature = request.getParameter("signature");
            //时间戳
            String timestamp = request.getParameter("timestamp");
            //随机数
            String nonce = request.getParameter("nonce");
            //随机字符串
            String echostr = request.getParameter("echostr");
            if (signature == null || timestamp == null || nonce == null || echostr == null) {
                log.error("请求来自非微信服务器");
                return result;
            }

            result = SignUtil.checkSign(signature, timestamp, nonce);
            if (result) {
                try {
                    response.getWriter().print(echostr);
                    log.info("微信服务器校验成功");
                } catch (IOException e) {
                    log.error("微信服务器校验成功，但响应过程失败");
                    result = false;
                    e.printStackTrace();
                }
            } else {
                log.error("微信服务器校验失败");
            }
        }
        return result;
    }

}