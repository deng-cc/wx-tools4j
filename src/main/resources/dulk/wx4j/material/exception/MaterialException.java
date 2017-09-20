package dulk.wx4j.material.exception;

/**
 * 素材管理相关的异常
 * <p>
 * 该异常多为素材在上传过程中出现的格式不符、大小不符等
 * </p>
 */
public class MaterialException extends Exception {

    public MaterialException(String message) {
        super(message);
    }
}
