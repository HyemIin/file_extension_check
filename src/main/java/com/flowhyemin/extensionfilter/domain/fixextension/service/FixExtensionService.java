package com.flowhyemin.extensionfilter.domain.fixextension.service;

import com.flowhyemin.extensionfilter.domain.fixextension.dto.*;
import com.flowhyemin.extensionfilter.domain.fixextension.entity.FixExtension;
import com.flowhyemin.extensionfilter.domain.fixextension.repository.FixExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FixExtensionService {
    private final FixExtensionRepository fixExtensionRepository;

    @Transactional
    public FixExtensionCreateResponse createFixExtension(FixExtensionCreateRequest fixExtensionCreateRequest) {
        FixExtension fixExtension = fixExtensionCreateRequest.toEntity();
        fixExtensionRepository.save(fixExtension);
        return FixExtensionCreateResponse.fromEntity(fixExtension);
    }
    @Transactional
    public void checkFixExtension(FixExtensionCheckRequest fixExtensionCheckRequest) {
        FixExtension fixExtension = fixExtensionRepository.findByName(fixExtensionCheckRequest.getName()).orElseThrow();
        fixExtension.updateFixExtension(fixExtensionCheckRequest.getIsChecked());
    }
    @Transactional
    public FixExtensionDeleteResponse deleteFixExtension(FixExtensionDeleteRequest fixExtensionDeleteRequest) {
        FixExtension fixExtension = fixExtensionDeleteRequest.toEntity();
        fixExtensionRepository.deleteByName(fixExtensionDeleteRequest.getName());
        return FixExtensionDeleteResponse.fromEntity(fixExtension);
    }
    @Transactional(readOnly = true)
    public List<FixExtensionGetResponse> findAllFixExtension() {
        return fixExtensionRepository.findAllByOrderByIdAsc().stream()
            .map(FixExtensionGetResponse::fromEntity)
            .collect(Collectors.toList());
    }

}
