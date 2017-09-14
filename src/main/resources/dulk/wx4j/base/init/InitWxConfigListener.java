package dulk.wx4j.base.init;


import dulk.wx4j.base.api.WxConfig;
import dulk.wx4j.base.service.WxBaseService;
import dulk.wx4j.base.util.SignUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 微信开发者配置初始化的监听器
 */
public class InitWxConfigListener implements ServletContextListener{
    private static Logger log = Logger.getLogger(InitWxConfigListener.class);

    /**
     * 定时器
     */
    private static Timer timer = new Timer();

    /**
     * 定时器任务，间歇刷新accessToken
     */
    private static TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            String accessToken = WxBaseService.getNewAccessToken();
            WxConfig.setAccessToken(accessToken);
            log.info("accessToken刷新：" + accessToken);
        }
    };

    /**
     * 刷新的间隔时间（单位：毫秒），默认为1.5h
     */
    private long period = 5400*1000;

    /**
     * 容器初始化任务，以注入微信开发者配置
     * @param event
     */
    public void contextInitialized(ServletContextEvent event) {
        File config = new File(event.getServletContext().getRealPath("/WEB-INF/classes/wxConfig.properties"));
        if (!config.exists()) {
            log.error("WEB-INF/classes/wxConfig.properties未找到！");
        }

        //加载配置文件
        Properties wxConfig = new Properties();
        try {
            wxConfig.load(new FileInputStream(config));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //微信配置的注入赋值
        WxConfig.setAppId(wxConfig.getProperty("appId"));
        WxConfig.setAppSecret(wxConfig.getProperty("appSecret"));
        WxConfig.setMerchantId(wxConfig.getProperty("merchantId"));
        WxConfig.setMerchantKey(wxConfig.getProperty("merchantKey"));
        SignUtil.setToken(wxConfig.getProperty("token"));
        log.info("微信开发者配置注入");

        //微信access_token定时刷新
        timer.schedule(timerTask, new Date(), period);
    }


    /**
     * 容器销毁任务，用以取消accessToken的自动刷新
     * @param event
     */
    public void contextDestroyed(ServletContextEvent event) {
        timerTask.cancel();
        log.info("access_token自动刷新的定时任务取消");
    }
}
