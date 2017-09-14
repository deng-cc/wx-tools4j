package dulk.wx4j.base.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.InputStream;
import java.io.Writer;

/**
 * XmlUtil.
 *
 * @author Dulk
 * @version 20170904
 * @date 17-9-4
 */
public class XmlUtil {

    /**
     * 扩展xStream，使其支持CDATA块
     */
    private static XStream xStream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {

                boolean cdata = true;

                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });




    /**
     * 将xml的流形式转换为对应的对象
     * @param is
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toBean(InputStream is, Class<T> clazz) {
        XStream xStream = new XStream();
        xStream.processAnnotations(clazz);
        T obj = (T) xStream.fromXML(is);
        return obj;
    }

    /**
     * 将对象转换为xml格式的字符串
     * @param obj
     * @return
     */
    public static String toXml(Object obj) {
        XStream xStream = new XStream();
        xStream.processAnnotations(obj.getClass());
        String xml = xStream.toXML(obj);
        return xml;
    }

    /**
     * 将对象转换为xml格式的字符串，拓展CDATA块
     * @param obj
     * @return
     */
    public static String toXmlCDATA(Object obj) {
        XStream xStream = XmlUtil.xStream;
        xStream.processAnnotations(obj.getClass());
        String xml = xStream.toXML(obj);
        return xml;
    }




}
