package dulk.wx4j.base.init;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

/**
 * 简单的log4j的初始化监听器
 * <p>
 * 如果需要初始化log4j的配置，则需要在web.xml中配置该监听器，并将配置文件（log4j.properties）放置到classpath
 * </p>
 */
public class InitLog4jListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        String path = sce.getServletContext().getRealPath("/WEB-INF/classes/log4j.properties");
        File file = new File(path);

        if (file.exists()) {
            PropertyConfigurator.configure(path);
            System.out.println("log4j环境配置完成");
        } else {
            BasicConfigurator.configure();
            System.out.println("log4j.properties未找到，使用缺省log4j环境初始化");
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        //do nothing
    }
}
