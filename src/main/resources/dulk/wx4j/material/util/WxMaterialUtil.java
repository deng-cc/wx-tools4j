package dulk.wx4j.material.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dulk.wx4j.base.exception.WxException;
import dulk.wx4j.base.util.NetUtil;
import dulk.wx4j.material.domain.download.ArticleList;
import dulk.wx4j.material.domain.get.MediaList;
import dulk.wx4j.material.domain.get.NewsList;
import dulk.wx4j.material.domain.upload.PermImage;
import dulk.wx4j.material.domain.upload.News;
import dulk.wx4j.material.exception.MaterialException;
import dulk.wx4j.material.api.MaterialType;
import static dulk.wx4j.material.api.WxMaterialAPI.*;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * 临时素材的相关工具类
 * <p>封装了临时素材相关的功能，如上传，下载等</p>
 */
public class WxMaterialUtil {
    private static Logger log = Logger.getLogger(WxMaterialUtil.class);

    /**
     * 上传临时图片素材
     *
     * @param file
     * @return 临时图片素材的mediaId
     * @throws MaterialException
     * @throws WxException
     */
    public static String uploadTempImage(File file) throws MaterialException, WxException {
        if (!isAllowedType(ALLOWED_TEMP_IMAGE_TYPE, file)) {
            throw new MaterialException("上传临时图片格式不符要求，支持"
                    + Arrays.toString(ALLOWED_TEMP_IMAGE_TYPE));
        }
        if (file.length() > ALLOWED_TEMP_IMAGE_SIZE) {
            throw new MaterialException("上传临时图片大小超过限制，最大支持"
                    + ALLOWED_TEMP_IMAGE_SIZE / 1024 / 1024 + "M");
        }
        return uploadTemp(file, MaterialType.IMAGE);
    }

    /**
     * 上传临时语音素材
     *
     * @param file
     * @return 临时语音素材的mediaId
     * @throws MaterialException
     * @throws WxException
     */
    public static String uploadTempVoice(File file) throws MaterialException, WxException {
        if (!isAllowedType(ALLOWED_TEMP_VOICE_TYPE, file)) {
            throw new MaterialException("上传临时语音格式不符要求，支持"
                    + Arrays.toString(ALLOWED_TEMP_VOICE_TYPE));
        }
        if (file.length() > ALLOWED_TEMP_VOICE_SIZE) {
            throw new MaterialException("上传临时语音大小超过限制，最大支持"
                    + ALLOWED_TEMP_VOICE_SIZE / 1024 / 1024 + "M");
        }
        if (getVoicePeriod(file) > ALLOWED_TEMP_VOICE_TIME) {
            throw new MaterialException("上传临时语音超过时长，播放长度不超过" + ALLOWED_TEMP_VOICE_TIME + "s");
        }
        return uploadTemp(file, MaterialType.VOICE);
    }

    /**
     * 上传临时视频素材
     *
     * @param file
     * @return 临时视频素材的mediaId
     * @throws MaterialException
     * @throws WxException
     */
    public static String uploadTempVideo(File file) throws MaterialException, WxException {
        if (!isAllowedType(ALLOWED_TEMP_VIDEO_TYPE, file)) {
            throw new MaterialException("上传临时视频格式不符要求，支持"
                    + Arrays.toString(ALLOWED_TEMP_VIDEO_TYPE));
        }
        if (file.length() > ALLOWED_TEMP_VIDEO_SIZE) {
            throw new MaterialException("上传临时视频大小超过限制，最大支持"
                    + ALLOWED_TEMP_VIDEO_SIZE / 1024 / 1024 + "M");
        }
        return uploadTemp(file, MaterialType.VIDEO);
    }

    /**
     * 上传临时缩略图素材
     *
     * @param file
     * @return 临时缩略图素材的mediaId
     * @throws MaterialException
     * @throws WxException
     */
    public static String uploadTempThumb(File file) throws MaterialException, WxException {
        if (!isAllowedType(ALLOWED_TEMP_THUMB_TYPE, file)) {
            throw new MaterialException("上传临时缩略图格式不符要求，支持"
                    + Arrays.toString(ALLOWED_TEMP_THUMB_TYPE));
        }
        if (file.length() > ALLOWED_TEMP_THUMB_SIZE) {
            throw new MaterialException("上传临时缩略图超过限制，最大支持"
                    + ALLOWED_TEMP_THUMB_SIZE / 1024 + "KB");
        }
        return uploadTemp(file, MaterialType.THUMB);
    }




    /**
     * 上传永久图文消息素材
     *
     * @param news 图文消息实体类
     * @return 永久图文消息素材的mediaId
     * @throws WxException
     */
    public static String uploadPermNews(News news) throws WxException {
        String url = getUrl_uploadPermNews();
        String content = JSON.toJSONString(news);
        return NetUtil.sendRequestPOST(url, content).getString("media_id");
    }

    /**
     * 上传图文消息内的图片获取url
     * <p>该方法上传图片不占用公众号永久素材库的上限</p>
     *
     * @param file
     * @return 图文消息内图片的url
     */
    public static String uploadPermNewsImage(File file) throws MaterialException, WxException {
        if (!isAllowedType(ALLOWED_PERM_NEWSIMAGE_TYPE, file)) {
            throw new MaterialException("上传图文消息内图片格式不符要求，支持"
                    + Arrays.toString(ALLOWED_PERM_NEWSIMAGE_TYPE));
        }
        if (file.length() > ALLOWED_PERM_NEWSIMAGE_SIZE) {
            throw new MaterialException("上传图文消息内图片大小超过限制，最大支持"
                    + ALLOWED_PERM_NEWSIMAGE_SIZE / 1024 / 1024 + "M");
        }
        String url = getUrl_uploadPermNewsImage();
        String imageUrl = NetUtil.uploadMediaPOST(url, file).getString("url");
        log.debug("perm newsImage url :" + imageUrl);
        return imageUrl;
    }

    /**
     * 上传永久图片素材
     *
     * @param file
     * @return 永久图片素材的封装类PermImage，包括图片的mediaId和url
     * @throws MaterialException
     * @throws WxException
     */
    public static PermImage uploadPermImage(File file) throws MaterialException, WxException {
        if (!isAllowedType(ALLOWED_PERM_IMAGE_TYPE, file)) {
            throw new MaterialException("上传永久图片格式不符要求，支持"
                    + Arrays.toString(ALLOWED_PERM_IMAGE_TYPE));
        }
        if (file.length() > ALLOWED_PERM_IMAGE_SIZE) {
            throw new MaterialException("上传永久图片大小超过限制，最大支持"
                    + ALLOWED_PERM_IMAGE_SIZE / 1024 / 1024 + "M");
        }
        String url = getUrl_uploadPermMedia(MaterialType.IMAGE);
        JSONObject result = NetUtil.uploadMediaPOST(url, file);
        String mediaId = result.getString("media_id");
        String imageUrl = result.getString("url");
        log.debug("perm image mediaId :" + mediaId + "; url :" + imageUrl);
        return new PermImage(mediaId, imageUrl);
    }

    /**
     * 上传永久语音素材
     * <p>todo:对于wma、wav两种格式的时长无法判断，目前交由微信服务器判断，超时长则抛出WxException</p>
     *
     * @param file
     * @return 永久语音素材的mediaId
     * @throws MaterialException
     * @throws WxException
     */
    public static String uploadPermVoice(File file) throws MaterialException, WxException {
        if (!isAllowedType(ALLOWED_PERM_VOICE_TYPE, file)) {
            throw new MaterialException("上传永久语音格式不符要求，支持"
                    + Arrays.toString(ALLOWED_PERM_VOICE_TYPE));
        }
        if (file.length() > ALLOWED_PERM_VOICE_SIZE) {
            throw new MaterialException("上传永久语音大小超过限制，最大支持"
                    + ALLOWED_PERM_VOICE_SIZE / 1024 / 1024 + "M");
        }
        if (getVoicePeriod(file) > ALLOWED_PERM_VOICE_TIME) {
            throw new MaterialException("上传永久语音超过时长，播放长度不超过" + ALLOWED_PERM_VOICE_TIME + "s");
        }
        String url = getUrl_uploadPermMedia(MaterialType.VOICE);
        String mediaId = NetUtil.uploadMediaPOST(url, file).getString("media_id");
        log.debug("perm voice mediaId :" + mediaId);
        return mediaId;
    }

    /**
     * 上传永久视频素材
     *
     * @param file
     * @param title        视频标题
     * @param introduction 视频描述（简介）
     * @return 永久视频素材的mediaId
     * @throws MaterialException
     * @throws WxException
     */
    public static String uploadPermVideo(File file, String title, String introduction) throws MaterialException, WxException {
        if (!isAllowedType(ALLOWED_PERM_VIDEO_TYPE, file)) {
            throw new MaterialException("上传永久视频格式不符要求，支持"
                    + Arrays.toString(ALLOWED_PERM_VIDEO_TYPE));
        }
        if (file.length() > ALLOWED_PERM_VIDEO_SIZE) {
            throw new MaterialException("上传永久视频大小超过限制，最大支持"
                    + ALLOWED_PERM_VIDEO_SIZE / 1024 / 1024 + "M");
        }
        String url = getUrl_uploadPermMedia(MaterialType.VIDEO);
        String content = String.format("{\"title\":\"%s\",\"introduction\":\"%s\"}", title, introduction);
        String mediaId = NetUtil.uploadMediaPOST(url, file, content).getString("media_id");
        log.debug("perm video mediaId :" + mediaId);
        return mediaId;
    }

    /**
     * 上传永久缩略图素材
     *
     * @param file
     * @return 永久缩略图素材的封装类PermImage，包括图片的mediaId和url
     * @throws MaterialException
     * @throws WxException
     */
    public static PermImage uploadPermThumb(File file) throws MaterialException, WxException {
        if (!isAllowedType(ALLOWED_PERM_THUMB_TYPE, file)) {
            throw new MaterialException("上传永久缩略图格式不符要求，支持"
                    + Arrays.toString(ALLOWED_PERM_THUMB_TYPE));
        }
        if (file.length() > ALLOWED_PERM_THUMB_SIZE) {
            throw new MaterialException("上传永久缩略图超过限制，最大支持"
                    + ALLOWED_PERM_THUMB_SIZE / 1024 + "KB");
        }
        String url = getUrl_uploadPermMedia(MaterialType.THUMB);
        JSONObject result = NetUtil.uploadMediaPOST(url, file);
        String mediaId = result.getString("media_id");
        String imageUrl = result.getString("url");
        log.debug("perm thumb mediaId :" + mediaId + "; url :" + imageUrl);
        return new PermImage(mediaId, imageUrl);
    }

    /**
     * 下载临时素材
     * <p>封装的File文件类的文件类型，必须和mediaId对应的素材类型相同，否则很容易抛出异常</p>
     * <p>如new File("C:\\temp.mp4")，则mediaId必须对应的是视频素材，否则容易抛出WxException，或因为json解析而抛出JSONException</p>
     *
     * @param mediaId 临时素材的mediaId
     * @param file    将要写入内容的File类
     * @return 下载文件的File类
     * @throws WxException
     */
    public static File downloadTemp(String mediaId, File file) throws WxException {
        String url = getUrl_downloadTempMedia(mediaId);
        //视频不支持https下载，NetUtil中均为https协议请求，故此处选择http协议
        if (isAllowedType(ALLOWED_TEMP_VIDEO_TYPE, file)) {
            try {
                url = NetUtil.sendRequestGET(url).getString("video_url");
                URL urlObj = new URL(url);
                HttpURLConnection coon = (HttpURLConnection) urlObj.openConnection();
                InputStream is = coon.getInputStream();
                OutputStream os = new FileOutputStream(file);
                int size = -1;
                byte[] cache = new byte[1024];
                while ((size = is.read(cache)) != -1) {
                    os.write(cache, 0, size);
                }
                os.close();
                is.close();
                coon.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return file;

        } else {
            return NetUtil.sendRequestGET(url, file);
        }
    }


    /**
     * 下载永久图文消息素材
     *
     * @param mediaId 图文消息素材的mediaId
     * @return 永久图文消息素材的封装类
     * @throws WxException
     */
    public static ArticleList downloadPermNews(String mediaId) throws WxException {
        String url = getUrl_downloadPermMedia();
        String content = String.format("{\"media_id\":\"%s\"}", mediaId);
        JSONObject resultJSON = NetUtil.sendRequestPOST(url, content);
        return resultJSON.toJavaObject(ArticleList.class);
    }

    //todo 待测试
    /**
     * 下载其他永久素材
     *
     * @param mediaId 永久素材的mediaId
     * @param file    需要写入内容的File类
     * @return 不同的素材返回的字符串代表含义不同
     *         视频素材：返回视频素材的JSON字符串，同时视频内容写入到参数file中
     *         其他素材：如图片、语音、缩略图等，返回其文件的绝对路径，同时内容写入到参数file中
     */
    public static String downloadPermMedia(String mediaId, File file) throws WxException {
        String url = getUrl_downloadPermMedia();
        String content = String.format("{\"media_id\":\"%s\"}", mediaId);
        //视频素材
        if (isAllowedType(ALLOWED_PERM_VIDEO_TYPE, file)) {
            try {
                JSONObject resultJSON = NetUtil.sendRequestPOST(url, content);
                url = resultJSON.getString("down_url");
                URL urlObj = new URL(url);
                HttpURLConnection coon = (HttpURLConnection) urlObj.openConnection();
                InputStream is = coon.getInputStream();
                OutputStream os = new FileOutputStream(file);
                int size = -1;
                byte[] cache = new byte[1024];
                while ((size = is.read(cache)) != -1) {
                    os.write(cache, 0, size);
                }
                os.close();
                is.close();
                coon.disconnect();
                return resultJSON.toJSONString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //其他素材
        if (isAllowedType(ALLOWED_PERM_NEWSIMAGE_TYPE, file) || isAllowedType(ALLOWED_PERM_IMAGE_TYPE, file)
                || isAllowedType(ALLOWED_PERM_THUMB_TYPE, file) || isAllowedType(ALLOWED_PERM_VOICE_TYPE, file)) {
            NetUtil.sendRequestPOST(url, content, file);
            try {
                return file.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //todo 待测试
    /**
     * 获取永久图文素材列表
     *
     * @param startIndex 从全部素材的startIndex位置开始，0表示从第一个素材开始返回
     * @param count      返回素材的数量，取值在1-20之间
     * @return
     */
    public static NewsList getPermNewsList(int startIndex, int count) throws MaterialException, WxException {
        if (count < 0 || count > 20) {
            throw new MaterialException("返回素材的数量指定超过限制，取值只能在1-20之间");
        }
        String url = getUrl_getPermMediaList();
        String postContent = String.format("{\"type\":\"news\",\"offset\":\"%d\",\"count\":\"%d\"}", startIndex, count);
        return NetUtil.sendRequestPOST(url, postContent).toJavaObject(NewsList.class);
    }

    //todo 待测试，注意返回参数可能比文档提供的参数更多
    /**
     * 获取永久其他素材列表
     *
     * @param type       其他媒体素材类型
     * @param startIndex 从全部素材的startIndex位置开始，0表示从第一个素材开始返回
     * @param count      返回素材的数量，取值在1-20之间
     * @return
     * @throws MaterialException
     * @throws WxException
     */
    public static MediaList getPermMediaList(MaterialType type, int startIndex, int count) throws MaterialException, WxException {
        if (count < 0 || count > 20) {
            throw new MaterialException("返回素材的数量指定超过限制，取值只能在1-20之间");
        }
        String url = getUrl_getPermMediaList();
        String postContent = String.format("{\"type\":\"%s\",\"offset\":\"%d\",\"count\":\"%d\"}", type.getValue(), startIndex, count);
        return NetUtil.sendRequestPOST(url, postContent).toJavaObject(MediaList.class);
    }

    /**
     * 获取永久图文消息素材的总数
     * @return 永久图文消息素材的总数
     * @throws WxException
     */
    public static int getPermNewsCount() throws WxException {
        String type = "news";
        return Integer.valueOf(getPermCount(type));
    }

    /**
     * 获取其他永久类型素材的总数
     *
     * @param type voice/video/image，没有thumb
     * @return 其他永久素材总数
     * @throws WxException
     */
    public static int getPermMediaCount(MaterialType type) throws WxException, MaterialException {
        if (MaterialType.THUMB == type) {
            throw new MaterialException("获取其他类型素材总数，不包括缩略图类型");
        }
        String typeStr = type.getValue();
        return Integer.valueOf(getPermCount(typeStr));
    }










    /**
     * 获取指定素材类型的总数
     *
     * @param type voice/video/image/news
     * @return
     * @throws WxException
     */
    private static String getPermCount(String type) throws WxException {
        String url = getUrl_getPermMediaCount();
        return NetUtil.sendRequestGET(url).getString(type + "_count");
    }

    /**
     * 上传临时素材
     * <p>临时素材的mediaId是可复用的，临时素材有效期为3天</p>
     *
     * @param file 文件
     * @param type 文件类型
     * @return
     */
    private static String uploadTemp(File file, MaterialType type) throws WxException {
        String mediaId = null;
        String url = getUrl_getTempMediaId(type);
        try {
            if (MaterialType.THUMB == type) {
                mediaId = NetUtil.uploadMediaPOST(url, file).getString("thumb_media_id");
            } else {
                mediaId = NetUtil.uploadMediaPOST(url, file).getString("media_id");
            }
            log.debug("temp " + type.getValue() + " mediaId :" + mediaId);
        } catch (WxException e) {
            log.warn(e.getMessage());
            throw new WxException(e.getCode(), e.getErrMsg());
        }
        return mediaId;
    }

    /**
     * 获取文件后缀
     *
     * @param file 文件
     * @return 文件后缀名
     */
    private static String getFileSuffix(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }

    /**
     * 判断某个文件是否是允许的类型
     *
     * @param allowedTypes
     * @param file
     * @return
     */
    private static boolean isAllowedType(String[] allowedTypes, File file) {
        String type = getFileSuffix(file);
        return ArrayUtils.contains(allowedTypes, type);
    }

    /**
     * 判断语音文件的播放时间长度（单位：秒）
     * <p>参考链接: http://www.cnblogs.com/yoyotl/p/5614530.html</p>
     *
     * @param file
     * @return
     */
    private static long getVoicePeriod(File file) {
        //MP3File类支持解析的音频类型，另不准确或不支持wma、wav
        String[] types = {"mp3", "amr"};
        long period = -1;
        try {
            if (ArrayUtils.contains(types, getFileSuffix(file))) {
                MP3File mp3 = new MP3File(file);
                MP3AudioHeader audioHeader = (MP3AudioHeader) mp3.getAudioHeader();
                period = audioHeader.getTrackLength();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        } catch (ReadOnlyFileException e) {
            e.printStackTrace();
        } catch (InvalidAudioFrameException e) {
            e.printStackTrace();
        }
        log.debug("media duration : " + period + " seconds");
        return period;
    }


}
