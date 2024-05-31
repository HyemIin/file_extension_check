package com.flowhyemin.extensionfilter.domain.fixextension.dto;

import com.flowhyemin.extensionfilter.domain.fixextension.entity.FixExtension;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FixExtensionCreateRequest {
    private String name;

    public FixExtension toEntity() {
        return FixExtension.builder()
            .name(name)
            .isChecked(false)
            .build();
    }
}
