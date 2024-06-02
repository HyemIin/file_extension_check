package com.flowhyemin.extensionfilter.domain.customextension.dto;

import com.flowhyemin.extensionfilter.domain.customextension.entity.CustomExtension;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CustomExtensionCreateRequest {
    @Size(min = 1,max = 20)
    @Pattern(regexp = "^[a-z]+$", message = "영어 소문자만 입력해주세요.")
    private String name;

    public CustomExtension toEntity() {
        return CustomExtension.builder()
            .name(name)
            .build();
    }
}
