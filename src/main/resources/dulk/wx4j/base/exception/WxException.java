package dulk.wx4j.base.exception;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * WxException.
 *
 * @author Dulk
 * @version 20170811
 * @date 17-8-11
 */
public class WxException extends Exception{
    private static Logger log = Logger.getLogger(WxException.class);

    private static Map<String, String> wxErrorCode = new HashMap<String, String>();

    /** 读取xml中的微信错误码 */
    static {
        InputStream xml = WxException.class.getResourceAsStream("wxErrorCode.xml");
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(xml);
            Element root = document.getRootElement();
            List<Element> elements = root.elements();
            for (Element element : elements) {
                String code = element.elementText("code");
                String text = element.elementText("text");
                WxException.wxErrorCode.put(code, text);
            }
        } catch (DocumentException e) {
            log.error("wxErrorCode.xml读取失败");
            e.printStackTrace();
        }
    }

    /** 错误码 */
    private String code;
    /** 错误信息 */
    private String text;
    /** 错误信息（微信服务器返回） */
    private String errMsg;

    public WxException(String code, String errMsg) {
        super(wxErrorCode.get(code) + " " + code + ":" + errMsg);
        this.code = code;
        this.text = wxErrorCode.get(code);
        this.errMsg = errMsg;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public String getErrMsg() {
        return errMsg;
    }

}
