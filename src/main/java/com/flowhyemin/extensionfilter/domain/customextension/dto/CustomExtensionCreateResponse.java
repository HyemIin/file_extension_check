package com.flowhyemin.extensionfilter.domain.customextension.dto;

import com.flowhyemin.extensionfilter.domain.customextension.entity.CustomExtension;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CustomExtensionCreateResponse {
    private Long id;
    private String name;

    public static CustomExtensionCreateResponse fromEntity(CustomExtension customExtension) {
        return CustomExtensionCreateResponse.builder()
            .id(customExtension.getId())
            .name(customExtension.getName())
            .build();
    }
}
