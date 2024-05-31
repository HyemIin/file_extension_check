package com.flowhyemin.extensionfilter.domain.fixextension.service;

import com.flowhyemin.extensionfilter.domain.fixextension.dto.FixExtensionGetResponse;
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
        return fixExtensionRepository.findAllByIsFixed(true).stream()
            .map(FixExtensionGetResponse::fromEntity)
            .collect(Collectors.toList());
    }
}
