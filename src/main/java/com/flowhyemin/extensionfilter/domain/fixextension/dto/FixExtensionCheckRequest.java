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
public class FixExtensionCheckRequest {
    @Size(min = 1,max = 20)
    @Pattern(regexp = "^[a-z]+$", message = "영어 소문자만 입력해주세요.")
    private String name;
    private String isChecked;

    public FixExtension toEntity() {
        return FixExtension.builder()
            .name(name)
            .isChecked(isChecked)
            .build();
    }
}
