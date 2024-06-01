package com.flowhyemin.extensionfilter.domain.fixextension.dto;

import com.flowhyemin.extensionfilter.domain.fixextension.entity.FixExtension;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FixExtensionCheckRequest {
    private String name;
    private Boolean isChecked;

    public FixExtension toEntity() {
        return FixExtension.builder()
            .name(name)
            .isChecked(isChecked)
            .build();
    }
}
