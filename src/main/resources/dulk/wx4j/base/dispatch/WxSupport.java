package dulk.wx4j.base.dispatch;

import dulk.wx4j.base.domain.WxRequestParams;
import dulk.wx4j.base.domain.WxResponseParams;
import dulk.wx4j.base.domain.message.Article;
import dulk.wx4j.base.domain.message.Image;
import dulk.wx4j.base.domain.message.Music;
import dulk.wx4j.base.domain.message.Video;
import dulk.wx4j.base.domain.message.Voice;
import dulk.wx4j.base.service.WxBaseService;
import dulk.wx4j.base.util.XmlUtil;
import dulk.wx4j.message.api.WxMsgAPI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;


/**
 * WxSupport.
 *
 * @author Dulk
 * @version 20170901
 * @date 17-9-1
 */
public abstract class WxSupport {

    protected WxRequestParams wxRequestParams = new WxRequestParams();
    protected WxResponseParams wxResponseParams = new WxResponseParams();

    /**
     * 微信信息和事件处理的执行入口，执行数据的接受和事件分发
     * @param request
     * @param response
     */
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        //微信服务器校验
        if ("GET".equals(request.getMethod())) {
            WxBaseService.wxServerConfirm(request, response);
        }
        //微信信息交互
        if ("POST".equals(request.getMethod())) {
            //解析数据
            parseData(request);
            //消息分发处理
            dispatchMsg();
            //消息响应
            response(response);
        }
    }

    /**
     * 将微信请求中的xml流解析封装为该Action中的WxRequestParams类
     * @param request
     */
    private void parseData(HttpServletRequest request) {
        try {
            //设置编码格式避免乱码
            request.setCharacterEncoding("UTF-8");
            InputStream is = request.getInputStream();
            this.wxRequestParams = XmlUtil.toBean(is, WxRequestParams.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 响应信息
     * @param response
     */
    private void response(HttpServletResponse response) {
        try {
            //设置编码避免中文乱码
            response.setCharacterEncoding("UTF-8");
            String responseData = XmlUtil.toXml(wxResponseParams);
            //String responseData = XmlUtil.toXmlCDATA(wxResponseParams);
            PrintWriter out = response.getWriter();
            out.print(responseData);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 响应数据的基础构建
     */
    private void responseBase() {
        wxResponseParams.setToUserName(wxRequestParams.getFromUserName());
        wxResponseParams.setFromUserName(wxRequestParams.getToUserName());
        wxResponseParams.setCreateTime(wxRequestParams.getCreateTime());
    }

    /**
     * 设置文本响应数据
     * @param content
     */
    protected void responseText(String content) {
        responseBase();
        wxResponseParams.setMsgType(WxMsgAPI.MSG_TYPE_TEXT);
        wxResponseParams.setContent(content);
    }

    /**
     * 设置图片响应数据
     * <p>其中mediaId可以通过MaterialUtil工具类上传图片进行获取</p>
     * @param mediaId 图片素材上传到微信服务器后返回得到的mediaId
     */
    protected void responseImage(String mediaId) {
        responseBase();
        wxResponseParams.setMsgType(WxMsgAPI.MSG_TYPE_IMAGE);
        Image image = new Image(mediaId);
        wxResponseParams.setImage(image);
    }

    /**
     * 设置语音响应数据
     * @param mediaId
     */
    protected void responseVoice(String mediaId) {
        responseBase();
        wxResponseParams.setMsgType(WxMsgAPI.MSG_TYPE_VOICE);
        Voice voice = new Voice(mediaId);
        wxResponseParams.setVoice(voice);
    }

    /**
     * 设置视频响应数据
     * @param video
     */
    protected void responseVideo(Video video) {
        responseBase();
        wxResponseParams.setMsgType(WxMsgAPI.MSG_TYPE_VIDEO);
        wxResponseParams.setVideo(video);
    }

    /**
     * 设置音乐响应数据
     * @param music
     */
    protected void responseMusic(Music music) {
        responseBase();
        wxResponseParams.setMsgType(WxMsgAPI.MSG_TYPE_MUSIC);
        wxResponseParams.setMusic(music);
    }

    /**
     * 设置图文消息响应数据
     * @param articles
     */
    protected void responseNews(List<Article> articles) {
        responseBase();
        wxResponseParams.setMsgType(WxMsgAPI.MSG_TYPE_NEWS);
        wxResponseParams.setArticleCount(String.valueOf(articles.size()));
        wxResponseParams.setArticles(articles);
    }

    /**
     * 消息分发和处理
     */
    private void dispatchMsg() {
        String msgType = wxRequestParams.getMsgType();
        //事件
        if (WxMsgAPI.MSG_TYPE_EVENT.equals(msgType)) {
            String eventType = wxRequestParams.getEvent();
            dispatchEvent(eventType);
        }
        //文本
        if (WxMsgAPI.MSG_TYPE_TEXT.equals(msgType)) {
            handleText();
        }
        //图片
        if (WxMsgAPI.MSG_TYPE_IMAGE.equals(msgType)) {
            handleImage();
        }
        //语音
        if (WxMsgAPI.MSG_TYPE_VOICE.equals(msgType)) {
            handleVoice();
        }
        //视频
        if (WxMsgAPI.MSG_TYPE_VIDEO.equals(msgType)) {
            handleVideo();
        }
        //小视频
        if (WxMsgAPI.MSG_TYPE_SHORT_VIDEO.equals(msgType)) {
            handleShortVideo();
        }
        //地理位置
        if (WxMsgAPI.MSG_TYPE_LOCATION.equals(msgType)) {
            handleLocation();
        }
        //链接
        if (WxMsgAPI.MSG_TYPE_LINK.equals(msgType)) {
            handleLink();
        }
    }

    private void dispatchEvent(String eventType) {
        //关注
        if (WxMsgAPI.EVENT_TYPE_SUBSCRIBE.equals(eventType)) {

        }
    }

    protected abstract void handleText();
    protected abstract void handleImage();
    protected abstract void handleVoice();
    protected abstract void handleVideo();
    protected abstract void handleShortVideo();
    protected abstract void handleLocation();
    protected abstract void handleLink();

    protected abstract void doSubscribe();
    protected abstract void doUnsubscribe();
    protected abstract void doQR_Subscribe();
    protected abstract void doQR_Scan();
    protected abstract void doLocation();
    protected abstract void doMenu_Click();
    protected abstract void doMenu_View();

    

}
