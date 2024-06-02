package com.flowhyemin.extensionfilter.domain.customextension.dto;

import com.flowhyemin.extensionfilter.domain.customextension.entity.CustomExtension;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CustomExtensionGetResponse {
    private String name;

    public static CustomExtensionGetResponse fromEntity(CustomExtension customExtension) {
        return CustomExtensionGetResponse.builder()
            .name(customExtension.getName())
            .build();
    }
}
