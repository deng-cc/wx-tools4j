package dulk.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * WxActionWithParent.
 *
 * @author Dulk
 * @version 20171207
 * @date 17-12-7
 */
public class WxActionWithParent extends Parent {

    private static WxAction wxAction = new WxAction();


    public void execute(HttpServletRequest request, HttpServletResponse response) {
        wxAction.execute(request, response);
    }


}
