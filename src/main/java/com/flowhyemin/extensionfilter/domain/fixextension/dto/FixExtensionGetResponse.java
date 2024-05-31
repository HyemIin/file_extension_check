package com.flowhyemin.extensionfilter.domain.fixextension.dto;

import com.flowhyemin.extensionfilter.domain.fixextension.entity.FixExtension;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FixExtensionGetResponse {
    private String name;
    private Boolean isFixed;

    public static FixExtensionGetResponse fromEntity(FixExtension fixExtension) {
        return FixExtensionGetResponse.builder()
            .name(fixExtension.getName())
            .isFixed(fixExtension.getIsFixed())
            .build();
    }
}
