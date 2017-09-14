package dulk.wx4j.material.api;

/**
 * MaterialType.
 *
 * @author Dulk
 * @version 20170913
 * @date 17-9-13
 */
public enum MaterialType {

    IMAGE("image"),
    VOICE("voice"),
    VIDEO("video"),
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
