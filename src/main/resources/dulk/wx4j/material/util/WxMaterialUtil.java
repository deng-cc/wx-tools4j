package dulk.wx4j.material.util;

import dulk.wx4j.base.exception.WxException;
import dulk.wx4j.base.util.NetUtil;
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
import java.io.IOException;
import java.util.Arrays;

/**
 * WxMaterialUtil.
 *
 * @author Dulk
 * @version 20170913
 * @date 17-9-13
 */
public class WxMaterialUtil {
    private static Logger log = Logger.getLogger(WxMaterialUtil.class);

    /**
     * 上传临时图片素材
     *
     * @param file
     * @return
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
     * @return
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
     * @return
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
     * @return
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
                mediaId = NetUtil.uploadMediaPost(url, file).getString("thumb_media_id");
            } else {
                mediaId = NetUtil.uploadMediaPost(url, file).getString("media_id");
            }
            log.debug(type.getValue() + " mediaId :" + mediaId);
        } catch (WxException e) {
            log.warn(e.getText());
            throw new WxException(e.getCode());
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
     * <p>
     * 参考链接
     *     mp3/amr: http://www.cnblogs.com/yoyotl/p/5614530.html
     * </p>
     * @param file
     * @return
     */
    private static long getVoicePeriod(File file) {
        String mediaType = getFileSuffix(file).toLowerCase();
        long period = -1;
        try {
            //语音为mp3和amr格式
            if ("mp3".equals(mediaType) || "amr".equals(mediaType)) {
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
