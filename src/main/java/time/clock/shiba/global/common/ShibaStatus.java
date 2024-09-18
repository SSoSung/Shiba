package time.clock.shiba.global.common;

public enum ShibaStatus {

    SUCCESS(0),
    OK(200),
    CREATED(201),
    BAD_REQUEST(400),
    FORBIDDEN(403),
    NOT_FOUND(404),
    NOT_FOUND_USER(-10),
    METHOD_NOT_ALLOWED(405),
    REDIRECT(302),
    SYSTEM_ERROR(-500);

    private final int code;

    ShibaStatus(int code) {
        this.code = code;
    }
}
