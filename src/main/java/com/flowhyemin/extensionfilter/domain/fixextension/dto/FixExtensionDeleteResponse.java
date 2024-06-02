package com.flowhyemin.extensionfilter.domain.fixextension.dto;

import com.flowhyemin.extensionfilter.domain.customextension.entity.CustomExtension;
import com.flowhyemin.extensionfilter.domain.fixextension.entity.FixExtension;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FixExtensionDeleteResponse {
    private Long id;
    private String name;

    public static FixExtensionDeleteResponse fromEntity(FixExtension fixExtension) {
        return FixExtensionDeleteResponse.builder()
            .id(fixExtension.getId())
            .name(fixExtension.getName())
            .build();
    }
}
