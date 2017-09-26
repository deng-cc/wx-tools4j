package dulk.wx4j.base.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.InputStream;
import java.io.Writer;

/**
 * Java和Xml相互转换的工具类
 */
public class XmlUtil {

    /**
     * 扩展xStream，使其支持CDATA块
     */
    private static XStream xStream = new XStream(new XppDriver() {

        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {

                boolean cdata = true;

                @Override
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                @Override
                public String encodeNode(String name) {
                    return name; //去下划线
                }

                @Override
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
     * 
     * @param is 输入流
     * @param clazz Java对象
     * @param <T> 泛型
     * @return 指定的Java对象
     */
    public static <T> T toBean(InputStream is, Class<T> clazz) {
        XStream xStream = new XStream();
        xStream.processAnnotations(clazz);
        return (T) xStream.fromXML(is);
    }

    /**
     * 将xml格式的字符串转换为对应的对象
     *
     * @param xml xml格式的字符串
     * @param clazz Java对象
     * @param <T> 泛型
     * @return 指定的Java对象
     */
    public static <T> T toBean(String xml, Class<T> clazz) {
        XStream xStream = new XStream();
        xStream.processAnnotations(clazz);
        return (T) xStream.fromXML(xml);
    }

    /**
     * 将对象转换为xml格式的字符串
     *
     * @param obj Java对象
     * @return xml格式的字符串
     */
    public static String toXml(Object obj) {
        XStream xStream = new XStream(new DomDriver(null, new XmlFriendlyNameCoder("_-", "_"))); //去双下划线
        xStream.processAnnotations(obj.getClass());
        return xStream.toXML(obj);
    }

    /**
     * 将对象转换为xml格式的字符串，拓展CDATA块
     *
     * @param obj Java对象
     * @return xml格式（含CDATA块）的字符串
     */
    public static String toXmlCDATA(Object obj) {
        XStream xStream = XmlUtil.xStream;
        xStream.processAnnotations(obj.getClass());
        return xStream.toXML(obj);
    }

}
