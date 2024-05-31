package com.flowhyemin.extensionfilter.domain.fixextension.controller;

import com.flowhyemin.extensionfilter.domain.fixextension.dto.FixExtensionCreateRequest;
import com.flowhyemin.extensionfilter.domain.fixextension.dto.FixExtensionCreateResponse;
import com.flowhyemin.extensionfilter.domain.fixextension.service.FixExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/fix")
public class FixExtensionController {
    private final FixExtensionService fixExtensionService;

    @PostMapping("/")
    @ResponseBody
    public boolean createFixExtension(@RequestBody FixExtensionCreateRequest fixExtensionCreateRequest) {
        fixExtensionService.createFixExtension(fixExtensionCreateRequest);
        return true;
    }
}
