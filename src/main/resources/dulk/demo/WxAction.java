package dulk.demo;

import dulk.wx4j.base.dispatch.WxSupport;
import dulk.wx4j.base.domain.message.Video;
import dulk.wx4j.base.exception.WxException;
import dulk.wx4j.material.exception.MaterialException;
import dulk.wx4j.material.service.WxMaterialService;
import dulk.wx4j.pay.domain.PayResult;
import dulk.wx4j.pay.exception.PayException;
import dulk.wx4j.pay.service.WxPayHandler;
import dulk.wx4j.pay.service.WxPayService;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * WxAction.
 *
 * @author Dulk
 * @version 20170904
 * @date 17-9-4
 */
@Controller
@RequestMapping(value="/wx")
public class WxAction extends WxSupport{
    private static Logger log = Logger.getLogger(WxAction.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    /**
     * 上传临时图片
     *
     * @param file
     */
    @RequestMapping(value = "/uploadTempImage")
    public void uploadTempImage(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
        try {
            String path = request.getSession().getServletContext().
                    getRealPath("/WEB-INF/temp/image") + "/" + file.getFileItem().getName();
            File temp = new File(path);
            FileUtils.copyInputStreamToFile(file.getInputStream(), temp);
            WxMaterialService.uploadTempImage(temp);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (WxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MaterialException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传临时语音
     *
     * @param file
     */
    @RequestMapping(value = "/uploadTempVoice")
    public void uploadTempVoice(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
        try {
            String path = request.getSession().getServletContext().
                    getRealPath("/WEB-INF/temp/voice") + "/" + file.getFileItem().getName();
            File temp = new File(path);
            FileUtils.copyInputStreamToFile(file.getInputStream(), temp);
            WxMaterialService.uploadTempVoice(temp);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (WxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MaterialException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传临时视频
     *
     * @param file
     */
    @RequestMapping(value = "/uploadTempVideo")
    public void uploadTempVideo(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
        try {
            String path = request.getSession().getServletContext().
                    getRealPath("/WEB-INF/temp/video") + "/" + file.getFileItem().getName();
            File temp = new File(path);
            FileUtils.copyInputStreamToFile(file.getInputStream(), temp);
            WxMaterialService.uploadTempVideo(temp);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (WxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MaterialException e) {
            e.printStackTrace();
        }
    }


    /**
     * 上传临时缩略图
     *
     * @param file
     */
    @RequestMapping(value = "/uploadTempThumb")
    public void uploadTempThumb(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) {
        try {
            String path = request.getSession().getServletContext().
                    getRealPath("/WEB-INF/temp/thumb") + "/" + file.getFileItem().getName();
            File temp = new File(path);
            FileUtils.copyInputStreamToFile(file.getInputStream(), temp);
            WxMaterialService.uploadTempThumb(temp);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (WxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MaterialException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/afterWxPay")
    public void afterWxPay(HttpServletRequest request, HttpServletResponse response) {
        try {
            WxPayService.afterWxPay(request, response, new WxPayHandler() {
                public void doWithSuccess(PayResult payResult) {
                    System.out.println( payResult.getWxTradeNo() + "订单支付成功");
                }

                public void doWithFail(PayResult payResult) {
                    System.out.println("支付失败");
                }
            });
        } catch (PayException e) {
            e.printStackTrace();
        }
    }


    /**
     * 消息交互的主动执行入口
     * @param request
     * @param response
     * @return
     */
    @Override
    @RequestMapping(value = "/execute")
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        super.execute(request, response);
    }

    //消息处理
    @Override
    protected void handleText() {
        String userContent = wxRequestParams.getContent();
        responseText("您输入的信息是：" + userContent);
    }

    @Override
    protected void handleImage() {
        String mediaId = "sdS18-X3fbHeHOfRYaa172Ekufol0AKVBPyUTy-9EIKXp152VnWeTh6Ldu4AGhmg";
        responseImage(mediaId);
    }

    @Override
    protected void handleVoice() {
        String mediaId = "wcri6jBQQld-OjcMSUmNiOTNlXUeznVnZYMNDnXwOr1KmGTzbgyx-4MKv8xSTniw";
        responseVoice(mediaId);
    }

    @Override
    protected void handleVideo() {
        Video video = new Video("0pASrB2qJyEEReNXZYEHVzsFlpHOKmnvnD4z585nCyz8rdm4er9sB2LrHE3syG_6");
        video.setTitle("轻松粘贴");
        video.setDescription("图片附着在3D模型上，爽!");
        responseVideo(video);
    }

    @Override
    protected void handleShortVideo() {
        //todo 待测试
        responseText("你好像发送了一个小视频");
    }

    @Override
    protected void handleLocation() {
        responseText("嗯，经度是" + wxRequestParams.getLocationY() + ", 纬度是" + wxRequestParams.getLocationX());
    }

    @Override
    protected void handleLink() {
        responseText("你发送了一个链接：" + wxRequestParams.getUrl());
    }

    //事件处理
    @Override
    protected void doSubscribe() {
        responseText("Hi, 欢迎来到Dulk的测试公众号~");
    }

    @Override
    protected void doUnsubscribe() {
        System.out.println("又有一位用户离开了我们的测试公众号");
    }

    @Override
    protected void doQR_Subscribe() {
        //todo 待测试
        responseText("Hi, 欢迎通过带参的二维码来到Dulk的测试公众号~");
    }

    @Override
    protected void doQR_Scan() {
        //todo 待测试
    }

    @Override
    protected void doLocation() {
        //todo 待测试
        System.out.println("用户上报了地理位置");
    }

    @Override
    protected void doMenu_Click() {
        responseText("你点击了菜单哦");
    }

    @Override
    protected void doMenu_View() {
        //todo 待测试
        System.out.println("用户点击了按钮网页浏览");
    }
}
