package com.flowhyemin.extensionfilter.domain.customextension.dto;

import com.flowhyemin.extensionfilter.domain.customextension.entity.CustomExtension;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CustomExtensionDeleteResponse {
    private Long id;
    private String name;

    public static CustomExtensionDeleteResponse fromEntity(CustomExtension customExtension) {
        return CustomExtensionDeleteResponse.builder()
            .id(customExtension.getId())
            .name(customExtension.getName())
            .build();
    }
}
