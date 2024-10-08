package com.example.puppicasso.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    // COMMON
    INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", "Method Not Allowed"),
    DATA_NOT_FOUND(400, "C003", "Data Not Found"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_TYPE_VALUE(400, "C005", "Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),
    IO_EXCEPTION(500, "C007", "IO Exception"),

    // JWT
    INVALID_ACCESS_TOKEN_VERIFICATION_FAILED(400, "JE01", "Invalid Access Token"),
    INVALID_ACCESS_TOKEN_ACCESS_DENIED(400, "JE02", "Access Token Access Denied"),
    INVALID_REFRESH_TOKEN(400, "JE03", "Invalid Refresh Token"),

    // USER
    SIGNIN_PASSWORD_MISMATCH(400, "U001", "Signin Password Mismatch"),
    USER_DUPLICATION(400, "U002", "User is Duplication"),

    // AI
    MAX_IMAGE_SIZE_EXCEEDED(400, "A001", "Max Image Size is Exceeded"),
    INVALID_IMAGE_FILE_EXCEPTION(400, "A002", "Invalid Image File"),
    S3_IO_EXCEPTION(400, "A003", "S3 IO Exception"),
    TIKA_IO_EXCEPTION(400, "A004", "TIKA IO Exception"),

    // Subscription
    INVALID_SUBSCRIPTION(400, "S001", "Invalid Subscription"),
    ;

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
