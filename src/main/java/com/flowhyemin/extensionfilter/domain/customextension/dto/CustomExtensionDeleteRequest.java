package com.flowhyemin.extensionfilter.domain.customextension.dto;

import com.flowhyemin.extensionfilter.domain.customextension.entity.CustomExtension;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CustomExtensionDeleteRequest {
    private String name;

    public CustomExtension toEntity() {
        return CustomExtension.builder()
            .name(name)
            .build();
    }
}
