package com.flowhyemin.extensionfilter.domain.customextension.service;

import com.flowhyemin.extensionfilter.domain.customextension.dto.CustomExtensionCreateRequest;
import com.flowhyemin.extensionfilter.domain.customextension.dto.CustomExtensionCreateResponse;
import com.flowhyemin.extensionfilter.domain.customextension.dto.CustomExtensionDeleteRequest;
import com.flowhyemin.extensionfilter.domain.customextension.dto.CustomExtensionDeleteResponse;
import com.flowhyemin.extensionfilter.domain.customextension.entity.CustomExtension;
import com.flowhyemin.extensionfilter.domain.customextension.repository.CustomExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomExtensionService {
    private final CustomExtensionRepository customExtensionRepository;
    public List<CustomExtension> findAllCustomExtension() {
        return customExtensionRepository.findAll();
    }
    @Transactional
    public CustomExtensionCreateResponse createCustomExtension(CustomExtensionCreateRequest customExtensionCreateRequest) {
        CustomExtension customExtension = customExtensionCreateRequest.toEntity();
        customExtensionRepository.save(customExtension);
        return CustomExtensionCreateResponse.fromEntity(customExtension);

    }
    @Transactional
    public CustomExtensionDeleteResponse deleteCustomExtension(CustomExtensionDeleteRequest customExtensionDeleteRequest) {
        CustomExtension customExtension = customExtensionDeleteRequest.toEntity();
        customExtensionRepository.deleteByName(customExtensionDeleteRequest.getName());
        return CustomExtensionDeleteResponse.fromEntity(customExtension);
    }
}
