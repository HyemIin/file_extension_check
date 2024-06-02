package com.flowhyemin.extensionfilter.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //커스텀 확장자
    DUPLICATED_CUSTOM_EXTENSION(1001, "이미 등록된 확장자입니다."),
    EXCEEDED_CUSTOM_EXTENSION_REGISTRAION(1002,"등록 가능한 커스텀 확장자 수를 초과했습니다.")

    ;

    private final Integer code;
    private final String message;
}