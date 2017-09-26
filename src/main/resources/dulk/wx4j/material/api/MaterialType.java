package dulk.wx4j.material.api;

/**
 * 素材类型
 */
public enum MaterialType {

    /**
     * 图片
     */
    IMAGE("image"),
    /**
     * 语音
     */
    VOICE("voice"),
    /**
     * 视频
     */
    VIDEO("video"),
    /**
     * 缩略图
     */
    THUMB("thumb");

    private String value;

    MaterialType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
