package com.flowhyemin.extensionfilter.domain.fixextension.controller;

import com.flowhyemin.extensionfilter.domain.fixextension.dto.FixExtensionCheckRequest;
import com.flowhyemin.extensionfilter.domain.fixextension.dto.FixExtensionCreateRequest;
import com.flowhyemin.extensionfilter.domain.fixextension.dto.FixExtensionDeleteRequest;
import com.flowhyemin.extensionfilter.domain.fixextension.service.FixExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/check/")
    @ResponseBody
    public boolean checkFixExtension(@RequestBody FixExtensionCheckRequest fixExtensionCheckRequest) {
        fixExtensionService.checkFixExtension(fixExtensionCheckRequest);
        return true;
    }

    @DeleteMapping("/")
    @ResponseBody
    public boolean deleteFixExtension(@RequestBody FixExtensionDeleteRequest fixExtensionDeleteRequest) {
        fixExtensionService.deleteFixExtension(fixExtensionDeleteRequest);
        return true;
    }

}
