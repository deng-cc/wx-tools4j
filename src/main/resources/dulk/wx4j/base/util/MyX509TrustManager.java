package dulk.wx4j.base.util;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 证书信任管理器（用于https请求）。
 * <p>该类可以让它信任我们指定的证书，此处定义为信任所有证书，不论是否权威机构颁发</p>
 */
public class MyX509TrustManager implements X509TrustManager {

    //检查客户端证书
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    //检查服务器端证书
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    //返回受信任的X509证书数组
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
