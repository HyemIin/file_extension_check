package com.flowhyemin.extensionfilter.domain.fixextension.service;

import com.flowhyemin.extensionfilter.domain.fixextension.dto.FixExtensionCreateRequest;
import com.flowhyemin.extensionfilter.domain.fixextension.dto.FixExtensionCreateResponse;
import com.flowhyemin.extensionfilter.domain.fixextension.dto.FixExtensionGetResponse;
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
    public List<FixExtensionGetResponse> findAllFixExtension() {
        return fixExtensionRepository.findAll().stream()
            .map(FixExtensionGetResponse::fromEntity)
            .collect(Collectors.toList());
    }

    @Transactional
    public FixExtensionCreateResponse createFixExtension(FixExtensionCreateRequest fixExtensionCreateRequest) {
        FixExtension fixExtension = fixExtensionCreateRequest.toEntity();
        fixExtensionRepository.save(fixExtension);
        return FixExtensionCreateResponse.fromEntity(fixExtension);
    }
}
