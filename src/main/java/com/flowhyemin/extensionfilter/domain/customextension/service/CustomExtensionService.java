package com.flowhyemin.extensionfilter.domain.customextension.service;

import com.flowhyemin.extensionfilter.domain.customextension.dto.*;
import com.flowhyemin.extensionfilter.domain.customextension.entity.CustomExtension;
import com.flowhyemin.extensionfilter.domain.customextension.exception.CustomExtensionException;
import com.flowhyemin.extensionfilter.domain.customextension.repository.CustomExtensionRepository;
import com.flowhyemin.extensionfilter.domain.fixextension.repository.FixExtensionRepository;
import com.flowhyemin.extensionfilter.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomExtensionService {
    private final CustomExtensionRepository customExtensionRepository;
    private final FixExtensionRepository fixExtensionRepository;

    @Transactional
    public CustomExtensionCreateResponse createCustomExtension(CustomExtensionCreateRequest customExtensionCreateRequest) {
        if (customExtensionRepository.findByName(customExtensionCreateRequest.getName()).isPresent()
            || fixExtensionRepository.findByName(customExtensionCreateRequest.getName()).isPresent()) {
            throw new CustomExtensionException(ErrorCode.DUPLICATED_EXTENSION);
        }
        if (customExtensionRepository.findAll().size() >= 200) {
            throw new CustomExtensionException(ErrorCode.EXCEEDED_CUSTOM_EXTENSION_REGISTRAION);
        }
        CustomExtension customExtension = customExtensionRepository.save(customExtensionCreateRequest.toEntity());

        return CustomExtensionCreateResponse.fromEntity(customExtension);
    }
    @Transactional
    public CustomExtensionDeleteResponse deleteCustomExtension(CustomExtensionDeleteRequest customExtensionDeleteRequest) {
        CustomExtension customExtension = customExtensionRepository.findByName(customExtensionDeleteRequest.getName())
            .orElseThrow(() -> new CustomExtensionException(ErrorCode.NONE_EXISTENCE_CUSTOM_EXTENSION)
        );
        customExtensionRepository.delete(customExtension);
        return CustomExtensionDeleteResponse.fromEntity(customExtension);
    }
    @Transactional
    public void deleteAllCustomExtension() {
        customExtensionRepository.deleteAll();
    }
    @Transactional(readOnly = true)
    public List<CustomExtensionGetResponse> findAllCustomExtension() {
        return customExtensionRepository.findAll().stream()
            .map(CustomExtensionGetResponse::fromEntity)
            .collect(Collectors.toList());

    }
}
