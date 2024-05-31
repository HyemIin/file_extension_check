package com.flowhyemin.extensionfilter.domain.customextension.controller;

import com.flowhyemin.extensionfilter.domain.customextension.dto.CustomExtensionCreateRequest;
import com.flowhyemin.extensionfilter.domain.customextension.dto.CustomExtensionCreateResponse;
import com.flowhyemin.extensionfilter.domain.customextension.dto.CustomExtensionDeleteRequest;
import com.flowhyemin.extensionfilter.domain.customextension.dto.CustomExtensionDeleteResponse;
import com.flowhyemin.extensionfilter.domain.customextension.service.CustomExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/custom")
public class CustomExtensionController {
    private final CustomExtensionService customExtensionService;

    @PostMapping("/")
    @ResponseBody
    public boolean createFixExtension(@RequestBody CustomExtensionCreateRequest customExtensionCreateRequest) {
        CustomExtensionCreateResponse customExtensionCreateResponse = customExtensionService.createCustomExtension(customExtensionCreateRequest);
        return true;
    }

    @DeleteMapping("/")
    @ResponseBody
    public boolean deleteFixExtension(@RequestBody CustomExtensionDeleteRequest customExtensionDeleteRequest) {
        CustomExtensionDeleteResponse customExtensionDeleteResponse = customExtensionService.deleteCustomExtension(customExtensionDeleteRequest);
        return true;
    }
}