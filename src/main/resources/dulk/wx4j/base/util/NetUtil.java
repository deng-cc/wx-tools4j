package dulk.wx4j.base.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dulk.wx4j.base.exception.WxException;
import org.apache.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 * 发送https请求的工具类
 */
public class NetUtil {
    private static Logger log = Logger.getLogger(NetUtil.class);

    /**
     * 解析request请求中的inputStream为字符串
     *
     * @param request http请求
     * @return 字符串
     */
    public static String parseRequest(HttpServletRequest request) {
        InputStream inputStream = null;
        StringBuffer buffer = new StringBuffer();
        try {
            inputStream = request.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }

    /**
     * 以GET请求方式调用微信url接口，以获取对应结果的字符串
     *
     * @param url 微信接口
     * @return 结果字符串
     */
    public static String throwRequestGET(String url) {
        return NetUtil.sendRequest(url, "GET", null);
    }

    /**
     * 以GET请求方式调用微信url接口，以获取对应结果的字符串JSON对象
     *
     * @param url 微信接口
     * @return 返回对应结果字符串的JSONObject对象
     * @throws WxException
     */
    public static JSONObject sendRequestGET(String url) throws WxException {
        String result = sendRequest(url, "GET", null);
        JSONObject resultJSON = JSON.parseObject(result);
        String errorCode = resultJSON.getString("errcode");
        String errorMsg = resultJSON.getString("errmsg");
        if (errorCode != null && !"0".equals(errorCode)) {
            throw new WxException(errorCode, errorMsg);
        }
        return resultJSON;
    }

    /**
     * 以GET请求方式调用微信url接口，以获取对应结果的File
     *
     * @param url  微信接口
     * @param file 将写入内容的file
     * @throws WxException
     */
    public static void sendRequestGET(String url, File file) throws WxException {
        sendRequest(url, "GET", null, file);
    }


    /**
     * 以POST请求方式调用微信url接口，以获取对应结果的字符串
     *
     * @param url     微信接口
     * @param content 需要附带提交的数据，json或者xml格式
     * @return 返回对应结果字符串
     */
    public static String throwRequestPOST(String url, String content) {
        return sendRequest(url, "POST", content);
    }

    /**
     * 以POST请求方式调用微信url接口，以获取对应结果的JSONO对象
     *
     * @param url     微信接口
     * @param content 需要附带提交的数据，常为json字符串
     * @return 返回对应结果字符串的JSONObject对象
     * @throws WxException
     */
    public static JSONObject sendRequestPOST(String url, String content) throws WxException {
        String result = sendRequest(url, "POST", content);
        JSONObject resultJSON = JSON.parseObject(result);
        String errorCode = resultJSON.getString("errcode");
        String errorMsg = resultJSON.getString("errmsg");
        if (errorCode != null && !"0".equals(errorCode)) {
            throw new WxException(errorCode, errorMsg);
        }
        return resultJSON;
    }

    /**
     * 以POST请求方式调用微信url接口，以获取对应结果的File
     *
     * @param url     微信接口
     * @param content 需要提交的数据，常为json字符串
     * @param file    将写入内容的file
     * @throws WxException
     */
    public static void sendRequestPOST(String url, String content, File file) throws WxException {
        sendRequest(url, "POST", content, file);
    }


    /**
     * 发送一个https请求并获取结果
     * <p>
     * 该方法针对于微信中对于大部分官方提供接口的访问，以得到期望数据
     * </p>
     *
     * @param url           请求地址url
     * @param requestMethod 请求方法GET/POST
     * @param outputStr     需要额外提交的数据，没有的话填null
     * @return response返回结果的字符串
     */
    private static String sendRequest(String url, String requestMethod, String outputStr) {
        outputStr = outputStr == null || outputStr.equals("") ? null : outputStr;
        StringBuffer buffer = new StringBuffer();
        //创建SSLContext对象，并使用我们指定的信任管理器初始化
        TrustManager[] trustManager = {new MyX509TrustManager()};
        SSLContext sslContext = null;
        String result = null;

        try {
            sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, trustManager, new SecureRandom());
            //从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            URL requestUrl = new URL(url);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) requestUrl.openConnection();
            httpUrlConn.setSSLSocketFactory(sslSocketFactory);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            //设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod.toUpperCase());

            if (requestMethod.equals("GET")) {
                httpUrlConn.connect();
            }

            //当有数据需要提交时
            if (outputStr != null) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                //注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            //将返回的输入流转换为字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            result = buffer.toString();

            //关闭资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.debug(result);
        return result;
    }

    /**
     * 发送一个https请求并获取结果
     * <p>
     * 该方法主要针对于微信中对于媒体文件下载接口的访问，以得到期望数据
     * </p>
     *
     * @param url           请求地址url
     * @param requestMethod 请求方法GET/POST
     * @param outputStr     需要额外提交的数据，没有的话填null
     * @param file          期望获取文件的File类
     * @throws WxException
     */
    private static void sendRequest(String url, String requestMethod, String outputStr, File file) throws WxException {
        outputStr = outputStr == null || outputStr.equals("") ? null : outputStr;
        StringBuffer buffer = new StringBuffer();
        //创建SSLContext对象，并使用我们指定的信任管理器初始化
        TrustManager[] trustManager = {new MyX509TrustManager()};
        SSLContext sslContext = null;
        String result = null;

        try {
            sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, trustManager, new SecureRandom());
            //从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            URL requestUrl = new URL(url);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) requestUrl.openConnection();
            httpUrlConn.setSSLSocketFactory(sslSocketFactory);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            //设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod.toUpperCase());

            if ("GET".equals(requestMethod)) {
                httpUrlConn.connect();
            }

            //当有数据需要提交时
            if (outputStr != null) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                //注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            //通过http请求头内容判断流内容，是业务错误代码还是文件
            String contentType = httpUrlConn.getContentType();
            InputStream is = httpUrlConn.getInputStream();
            //如果输入流是微信返回的业务错误代码
            if (contentType != null && contentType.contains("application/json")) {
                InputStreamReader inputStreamReader = new InputStreamReader(is, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String str = null;
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
                result = buffer.toString();
                JSONObject resultJSON = JSON.parseObject(result);
                String errorCode = resultJSON.getString("errcode");
                String errorMsg = resultJSON.getString("errmsg");
                if (errorCode != null && !"0".equals(errorCode)) {
                    throw new WxException(errorCode, errorMsg);
                }

            } else {
                //如果输入流返回的是文件流
                String fileName = file.getName();
                String fileType = contentType == null ? null : contentType.substring(contentType.lastIndexOf("/") + 1);
                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                if (fileType != null && !fileType.equals(suffix)) {
                    log.warn("mediaId对应的媒体类型（" + fileType + "）和file封装的文件类型（" + suffix + "）不符，" +
                            "写入的file可能无法打开");
                }

                //获取输入流，转化为文件
                OutputStream os = new FileOutputStream(file);
                int size = -1;
                byte[] cache = new byte[1024];
                while ((size = is.read(cache)) != -1) {
                    os.write(cache, 0, size);
                }
                os.close();
                os = null;
            }

            //关闭资源
            is.close();
            is = null;
            httpUrlConn.disconnect();
            log.debug(file.getCanonicalFile());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 模拟表单形式，上传form-data中的媒体文件
     *
     * @param url      请求地址url
     * @param file     需要上传的文件
     * @param fileDesc 额外需要提交的文件描述信息(JSON格式)，如微信提交视频文件要求增加json格式的文件描述
     * @return 返回结果的JSONObject
     * @throws WxException
     */
    public static JSONObject uploadMediaPOST(String url, File file, String... fileDesc) throws WxException {
        BufferedReader reader = null;
        String result = null;
        String line = null;
        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);  //post方式不能使用缓存
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Charset", "UTF-8");
            String BOUNDARY = "---------------------------" + System.currentTimeMillis();
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("--");  // 必须多两道线
            stringBuilder.append(BOUNDARY);
            stringBuilder.append("\r\n");
            stringBuilder.append("Content-Disposition: form-data;name=\"media\";filename=\"" + file.getName() + "\"\r\n");
            stringBuilder.append("Content-Type:application/octet-stream\r\n\r\n");
            byte[] head = stringBuilder.toString().getBytes("UTF-8");
            // 获得输出流
            OutputStream out = connection.getOutputStream();
            // 输出表头
            out.write(head);
            // 文件正文部分
            // 把文件已流文件的方式 推入到url中
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            int b = 0;
            byte[] bufferOut = new byte[1024];
            while ((b = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, b);
            }
            byte[] foot = ("\r\n--" + BOUNDARY + "\r\n").getBytes("UTF-8");
            out.write(foot);

            //参考 http://www.bijishequ.com/detail/457835?p=
            String content = null;
            for (String desc : fileDesc) {
                content = desc;
            }
            if (content != null) {
                StringBuffer sb = new StringBuffer();
                sb.append("--");
                sb.append(BOUNDARY);
                sb.append("\r\n");
                sb.append("Content-Disposition: form-data;name=\"description\";");
                sb.append("Content-Type:application/octet-stream\r\n\r\n");
                out.write(sb.toString().getBytes());
                out.write(content.getBytes());
                out.write(("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8"));
            }

            out.flush();
            out.close();

            StringBuffer buffer = new StringBuffer();
            // 定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            result = buffer.toString();
            if (reader != null) {
                reader.close();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.debug(result);
        JSONObject resultJSON = JSON.parseObject(result);
        String errorCode = resultJSON.getString("errcode");
        String errorMsg = resultJSON.getString("errmsg");
        if (errorCode != null && !"0".equals(errorCode)) {
            throw new WxException(errorCode, errorMsg);
        }
        return resultJSON;
    }

}
