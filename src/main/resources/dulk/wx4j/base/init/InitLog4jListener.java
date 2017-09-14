package dulk.wx4j.base.init;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

/**
 * InitLog4jListener.
 *
 * @author Dulk
 * @version 20170606
 * @date 17-6-6
 */
public class InitLog4jListener implements ServletContextListener{

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
    }
}
