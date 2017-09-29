package dulk.wx4j.pay.util;

import dulk.wx4j.base.api.WxConfig;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/**
 * 涉及微信支付相关的签名算法工具
 */
public class SignUtil {
    private static Logger log = Logger.getLogger(SignUtil.class);

    /**
     * 随机字符串的生成长度
     */
    private static final int DEFAULT_SIZE = 32;
    /**
     * 随机字符串算法的对应字符
     */
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";


    /**
     * 生成签名，用于在微信支付前，获取预支付时候需要使用的参数sign
     * <p>
     * ASCII字典序排列的参数进行字符串拼接，然后拼接API密钥后进行MD加密。
     * 算法参考：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3
     * </p>
     *
     * @param params 需要发送的所有数据设置为的Map
     * @return 签名sign
     */
    public static String createSign(TreeMap<String, String> params) {
        String signValue = "";
        String stringSignTemp = "";
        String stringA = "";

        //获得stringA
        Set<String> keys = params.keySet();
        for (String key : keys) {
            stringA += (key + "=" + params.get(key) + "&");
        }
        stringA = stringA.substring(0, stringA.length() - 1);
        //获得stringSignTemp
        stringSignTemp = stringA + "&key=" + WxConfig.getMerchantKey();
        //获得signValue
        signValue = encryptByMD5(stringSignTemp).toUpperCase();

        log.debug("签名: " + signValue);
        return signValue;
    }

    /**
     * 生成随机数
     * <p>
     * 调用随机数函数生成，将得到的值转换为字符串，不得长于32位。
     * 算法参考：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3
     * </p>
     *
     * @return 随机数字符串
     */
    public static String createNonceStr() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            int number = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(number));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * MD5加密
     *
     * @param sourceStr 需要加密的字符串
     * @return 加密后的字符串
     */
    private static String encryptByMD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes("UTF-8"));
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }


}
