package com.flowhyemin.extensionfilter.domain.customextension.exception;

import com.flowhyemin.extensionfilter.global.exception.BaseException;
import com.flowhyemin.extensionfilter.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class CustomExtensionException extends BaseException {
    private final ErrorCode errorCode;
    public CustomExtensionException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
