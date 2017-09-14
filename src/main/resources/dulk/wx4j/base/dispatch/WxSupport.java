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

    protected WxRequestParams wxRequestParams;
    protected WxResponseParams wxResponseParams;

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
        this.wxRequestParams = new WxRequestParams();
        this.wxResponseParams = new WxResponseParams();
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

    /**
     * 事件分发和处理
     * @param eventType 事件类型
     */
    private void dispatchEvent(String eventType) {
        //关注
        if (WxMsgAPI.EVENT_TYPE_SUBSCRIBE.equals(eventType)) {
            doSubscribe();
        }
        //取消关注
        if (WxMsgAPI.EVENT_TYPE_UNSUBSCRIBE.equals(eventType)) {
            doUnsubscribe();
        }
        //二维码带参关注
        if (WxMsgAPI.EVENT_TYPE_SUBSCRIBE.equals(eventType) && wxRequestParams.getEventKey().startsWith("qrscene_")) {
            doQR_Subscribe();
        }
        //二维码扫描
        if (WxMsgAPI.EVENT_TYPE_SCAN.equals(eventType)) {
            doQR_Scan();
        }
        //上报地理位置
        if (WxMsgAPI.EVENT_TYPE_LOCATION.equals(eventType)) {
            doLocation();
        }
        //菜单点击拉取消息
        if (WxMsgAPI.EVENT_TYPE_MENU_CLICK.equals(eventType)) {
            doMenu_Click();
        }
        //菜单点击浏览网页
        if (WxMsgAPI.EVENT_TYPE_MENU_VIEW.equals(eventType)) {
            doMenu_View();
        }
    }

    /**
     * 处理用户发送来的文本信息
     */
    protected abstract void handleText();

    /**
     * 处理用户发来的图片信息
     */
    protected abstract void handleImage();

    /**
     * 处理用户发来的语音信息
     */
    protected abstract void handleVoice();

    /**
     * 处理用户发来的视频信息
     */
    protected abstract void handleVideo();

    /**
     * 处理用户发来的小视频信息
     * <p>todo:给公众号发送拍摄视频默认为video而非shortVideo，暂未找到触发小视频消息的方式</p>
     */
    protected abstract void handleShortVideo();

    /**
     * 处理用户发来的地理位置
     */
    protected abstract void handleLocation();

    /**
     * 处理用户发来的链接
     * <p>该链接非普通的形如发送"www.google.com"，该形态会被视为文本</p>
     * <p>该链接指类似微信分享而产生的链接信息，类似卡片形态，带有标题/摘要/缩略图</p>
     */
    protected abstract void handleLink();

    /**
     * 处理用户的关注事件
     */
    protected abstract void doSubscribe();

    /**
     * 处理用户的取消关注事件
     */
    protected abstract void doUnsubscribe();

    /**
     * 处理用户的二维码扫描关注事件
     * <p>该二维码参数中必须带有事件值，否则二维码扫码关注视为普通的关注事件</p>
     * <p>事件值为qrscene_为前缀，后面为二维码的参数值</p>
     */
    protected abstract void doQR_Subscribe();

    /**
     * 处理用户的二维码扫码事件
     */
    protected abstract void doQR_Scan();

    /**
     * 处理用户的地理位置上报事件
     * <p>该处的地理位置上报并非用户发送地理位置</p>
     * <p>而是用户同意上报地理位置后，每次进入公众号会上报地理位置，或进入会话后每5秒上报一次地理位置</p>
     */
    protected abstract void doLocation();

    /**
     * 处理用户的菜单点击拉取消息的事件
     */
    protected abstract void doMenu_Click();

    /**
     * 处理用户的菜单点击浏览网页的事件
     */
    protected abstract void doMenu_View();

    

}
