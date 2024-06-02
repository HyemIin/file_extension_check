package com.flowhyemin.extensionfilter.domain.fixextension.dto;

import com.flowhyemin.extensionfilter.domain.fixextension.entity.FixExtension;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FixExtensionCheckResponse {
    private Long id;
    private String name;
    private String isChecked;

    public static FixExtensionCheckResponse fromEntity(FixExtension fixExtension) {
        return FixExtensionCheckResponse.builder()
            .id(fixExtension.getId())
            .name(fixExtension.getName())
            .isChecked(fixExtension.getIsChecked())
            .build();
    }
}
