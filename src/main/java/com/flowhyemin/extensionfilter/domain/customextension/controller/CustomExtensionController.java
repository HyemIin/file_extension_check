package com.flowhyemin.extensionfilter.domain.customextension.controller;

import com.flowhyemin.extensionfilter.domain.customextension.dto.CustomExtensionCreateRequest;
import com.flowhyemin.extensionfilter.domain.customextension.dto.CustomExtensionCreateResponse;
import com.flowhyemin.extensionfilter.domain.customextension.dto.CustomExtensionDeleteRequest;
import com.flowhyemin.extensionfilter.domain.customextension.dto.CustomExtensionDeleteResponse;
import com.flowhyemin.extensionfilter.domain.customextension.service.CustomExtensionService;
import jakarta.validation.Valid;
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
    public boolean createCustomExtension(@Valid @RequestBody CustomExtensionCreateRequest customExtensionCreateRequest) {
        customExtensionService.createCustomExtension(customExtensionCreateRequest);
        return true;
    }

    @DeleteMapping("/")
    @ResponseBody
    public boolean deleteCustomExtension(@RequestBody CustomExtensionDeleteRequest customExtensionDeleteRequest) {
        customExtensionService.deleteCustomExtension(customExtensionDeleteRequest);
        return true;
    }

    @DeleteMapping("/reset/")
    @ResponseBody
    public boolean deleteAllCustomExtension() {
        customExtensionService.deleteAllCustomExtension();
        return true;
    }
}