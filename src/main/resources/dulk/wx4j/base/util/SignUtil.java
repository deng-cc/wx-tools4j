package dulk.wx4j.base.util;


import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 接入微信服务器时的signature签名校验工具类
 */
public class SignUtil {
    private static Logger log = Logger.getLogger(SignUtil.class);

    /**
     * token值
     * <p>
     * 该token值必须与公众号平台中开发者配置信息的token设置相同
     * </p>
     */
    private static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        SignUtil.token = token;
    }

    /**
     * 微信的接入服务器验证，通过微信发送的Get中的三种参数匹配验证.
     *
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @return 布尔值确认是否验证通过
     */
    public static boolean checkSign(String signature, String timestamp, String nonce) {
        //1.将token、timestamp、nonce三个参数进行字典序排列
        String[] arr = {getToken(), timestamp, nonce};
        Arrays.sort(arr);

        //2.将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuffer content = new StringBuffer();
        for (String str : arr) {
            content.append(str);
        }
        MessageDigest md = null;
        String temp = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            //将拼接字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            temp = byteToStr(digest);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        content = null;

        //3.将加密后的字符串与signature对比，标志该请求来源于微信
        return temp != null ? temp.equals(signature.toUpperCase()) : false;
    }


    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray byte数组
     * @return 字符串
     */
    private static String byteToStr(byte[] byteArray) {
        StringBuffer strDigest = new StringBuffer();
        for (byte b : byteArray) {
            strDigest.append(byteToHexStr(b));
        }

        return strDigest.toString();
    }


    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte byte参数
     * @return 字符串
     */
    private static String byteToHexStr(byte mByte) {
        char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] temp = new char[2];
        temp[0] = digit[(mByte >>> 4) & 0X0F];
        temp[1] = digit[mByte & 0X0F];
        String str = new String(temp);

        return str;
    }

}
