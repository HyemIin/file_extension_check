package com.flowhyemin.extensionfilter.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 공통
    DUPLICATED_EXTENSION(1001, "이미 등록된 확장자입니다."),

    //커스텀 확장자
    EXCEEDED_CUSTOM_EXTENSION_REGISTRAION(1002,"등록 가능한 커스텀 확장자 수를 초과했습니다."),
    NONE_EXISTENCE_CUSTOM_EXTENSION(1003, "입력한 커스텀 확장자는 등록되어 있지 않습니다."),

    //고정 확장자
    EXCEEDED_FIX_EXTENSION_REGISTRAION(1004,"등록 가능한 고정 확장자 수를 초과했습니다."),
    NONE_EXISTENCE_FIX_EXTENSION(1005, "입력한 고정 확장자는 등록되어 있지 않습니다."),
    CHECKED_FIX_EXTENSION(1006,"고정 확장자는 UNCHECK 상태에서 삭제해야합니다.")
    ;

    private final Integer code;
    private final String message;
}
