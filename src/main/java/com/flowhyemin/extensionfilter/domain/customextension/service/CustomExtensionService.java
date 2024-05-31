package com.flowhyemin.extensionfilter.domain.customextension.service;

import com.flowhyemin.extensionfilter.domain.fixextension.entity.FixExtension;
import com.flowhyemin.extensionfilter.domain.fixextension.repository.FixExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomExtensionService {
    private final FixExtensionRepository fixExtensionRepository;
    public List<FixExtension> findAllCustomExtension() {
        return null;
    }
}
