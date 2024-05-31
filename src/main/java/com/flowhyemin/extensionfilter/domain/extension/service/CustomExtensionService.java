package com.flowhyemin.extensionfilter.domain.extension.service;

import com.flowhyemin.extensionfilter.domain.extension.entity.Extension;
import com.flowhyemin.extensionfilter.domain.extension.repository.ExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomExtensionService {
    private final ExtensionRepository extensionRepository;
    public List<Extension> findAllCustomExtension() {
        return null;
    }
}
