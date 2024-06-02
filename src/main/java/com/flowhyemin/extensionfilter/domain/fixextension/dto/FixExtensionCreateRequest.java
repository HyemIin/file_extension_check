package com.flowhyemin.extensionfilter.domain.fixextension.dto;

import com.flowhyemin.extensionfilter.domain.fixextension.entity.FixExtension;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FixExtensionCreateRequest {
    @Size(min = 1,max = 20,message = "크기가 1에서 20 사이여야 합니다.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "영어 소문자만 입력해주세요.")
    private String name;

    public FixExtension toEntity() {
        return FixExtension.builder()
            .name(name)
            .isChecked("false")
            .build();
    }
}
