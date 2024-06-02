package com.flowhyemin.extensionfilter.domain.fixextension.exception;

import com.flowhyemin.extensionfilter.global.exception.BaseException;
import com.flowhyemin.extensionfilter.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class FixExtensionException extends BaseException {
    private final ErrorCode errorCode;
    public FixExtensionException(ErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
