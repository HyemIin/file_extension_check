package com.flowhyemin.extensionfilter.domain.fixextension.dto;

import com.flowhyemin.extensionfilter.domain.fixextension.entity.FixExtension;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FixExtensionCreateResponse {
    private Long id;
    private String name;
    private Boolean isChecked;

    public static FixExtensionCreateResponse fromEntity(FixExtension fixExtension) {
        return FixExtensionCreateResponse.builder()
            .id(fixExtension.getId())
            .name(fixExtension.getName())
            .isChecked(fixExtension.getIsChecked())
            .build();
    }
}
