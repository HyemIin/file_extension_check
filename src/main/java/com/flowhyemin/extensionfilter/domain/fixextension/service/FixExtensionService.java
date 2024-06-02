package com.flowhyemin.extensionfilter.domain.fixextension.service;

import com.flowhyemin.extensionfilter.domain.customextension.repository.CustomExtensionRepository;
import com.flowhyemin.extensionfilter.domain.fixextension.dto.*;
import com.flowhyemin.extensionfilter.domain.fixextension.entity.FixExtension;
import com.flowhyemin.extensionfilter.domain.fixextension.exception.FixExtensionException;
import com.flowhyemin.extensionfilter.domain.fixextension.repository.FixExtensionRepository;
import com.flowhyemin.extensionfilter.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FixExtensionService {
    private final FixExtensionRepository fixExtensionRepository;
    private final CustomExtensionRepository customExtensionRepository;

    @Transactional
    public FixExtensionCreateResponse createFixExtension(FixExtensionCreateRequest fixExtensionCreateRequest) {
        if (fixExtensionRepository.findByName(fixExtensionCreateRequest.getName()).isPresent()
            || customExtensionRepository.findByName(fixExtensionCreateRequest.getName()).isPresent()) {
            throw new FixExtensionException(ErrorCode.DUPLICATED_EXTENSION);
        }
        if (fixExtensionRepository.findAll().size() >= 20) {
            throw new FixExtensionException(ErrorCode.EXCEEDED_FIX_EXTENSION_REGISTRAION);
        }
        FixExtension fixExtension = fixExtensionRepository.save(fixExtensionCreateRequest.toEntity());
        return FixExtensionCreateResponse.fromEntity(fixExtension);
    }
    @Transactional
    public FixExtensionCheckResponse checkFixExtension(FixExtensionCheckRequest fixExtensionCheckRequest) {
        FixExtension fixExtension = fixExtensionRepository.findByName(fixExtensionCheckRequest.getName())
            .orElseThrow(() -> new FixExtensionException(ErrorCode.NONE_EXISTENCE_FIX_EXTENSION));
        fixExtension.updateFixExtensionCheckBox(fixExtensionCheckRequest.getIsChecked());
        return FixExtensionCheckResponse.fromEntity(fixExtension);
    }
    @Transactional
    public FixExtensionDeleteResponse deleteFixExtension(FixExtensionDeleteRequest fixExtensionDeleteRequest) {
        FixExtension fixExtension = fixExtensionRepository.findByName(fixExtensionDeleteRequest.getName())
            .orElseThrow(() -> new FixExtensionException(ErrorCode.NONE_EXISTENCE_FIX_EXTENSION));
        fixExtensionRepository.delete(fixExtension);
        return FixExtensionDeleteResponse.fromEntity(fixExtension);
    }
    @Transactional(readOnly = true)
    public List<FixExtensionGetResponse> findAllFixExtension() {
        return fixExtensionRepository.findAllByOrderByIdAsc().stream()
            .map(FixExtensionGetResponse::fromEntity)
            .collect(Collectors.toList());
    }

}
