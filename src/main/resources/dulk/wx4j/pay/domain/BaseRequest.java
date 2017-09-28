package dulk.wx4j.pay.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import dulk.wx4j.base.util.XmlUtil;
import dulk.wx4j.pay.util.SignUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.TreeMap;

/**
 * 接口请求的参数封装类基类
 */
public abstract class BaseRequest {
    /**
     * 将对象中不为空的属性和值转换为TreeMap以进行签名运算
     * <p>
     * 该方法以key：value键值对方式存储对象的属性和值，其中key并非属性名，而是其XStreamAlias注解值。
     * 注意：sign属性不参与转换，因为在后续的签名算法中不需要sign
     * </p>
     * <p>
     * 该类作为提供方法的父类以继承，如果是BaseRequest类的子类的子类，需要注意若有添加新的属性，会被丢失无法解析
     * </p>
     *
     * @return 以属性：值存储的TreeMap
     */
    private TreeMap<String, String> toTreeMap() {
        TreeMap<String, String> map = new TreeMap<String, String>();
        Field[] fields = this.getClass().getDeclaredFields();
        if (fields.length == 0) {
            fields = this.getClass().getSuperclass().getDeclaredFields();
        }
        for (Field field : fields) {
            String fieldName = field.getName();
            String getter = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            try {
                Method method = this.getClass().getMethod(getter);
                Object value = method.invoke(this);
                if (value != null && !"sign".equals(fieldName) && field.isAnnotationPresent(XStreamAlias.class)) {
                    String name = field.getAnnotation(XStreamAlias.class).value();
                    map.put(name, value.toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 将统一下单参数封装类，转换为带签名的xml
     * <p>
     * 调用统一下单接口时，需要先将其他参数进行签名，再将结果同时作为参数之一最终转换为xml
     * </p>
     *
     * @return 统一下单请求参数的xml格式字符串（含签名）
     */
    public String toSignedXml() {
        setSign(SignUtil.createSign(this.toTreeMap()));
        return XmlUtil.toXmlCDATA(this);
    }

    /**
     * 设置sign的抽象方法
     * <p>
     * 所有子类实现时，只需要 this.sign = sign 即可
     * </p>
     *
     * @param sign 签名
     */
    public abstract void setSign(String sign);
}
