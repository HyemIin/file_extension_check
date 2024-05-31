package com.flowhyemin.extensionfilter.domain.fixextension.controller;

import com.flowhyemin.extensionfilter.domain.fixextension.dto.FixExtensionCreateRequest;
import com.flowhyemin.extensionfilter.domain.fixextension.dto.FixExtensionCreateResponse;
import com.flowhyemin.extensionfilter.domain.fixextension.service.FixExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/fix")
public class FixExtensionController {
    private final FixExtensionService fixExtensionService;

    @PostMapping("/")
    public String createFixExtension(FixExtensionCreateRequest fixExtensionCreateRequest) {
        FixExtensionCreateResponse fixExtensionCreateResponse = fixExtensionService.createFixExtension(fixExtensionCreateRequest);
        return "home";
    }
}
