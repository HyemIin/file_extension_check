package com.flowhyemin.extensionfilter.global.controller;

import com.flowhyemin.extensionfilter.domain.customextension.entity.CustomExtension;
import com.flowhyemin.extensionfilter.domain.customextension.service.CustomExtensionService;
import com.flowhyemin.extensionfilter.domain.fixextension.dto.FixExtensionGetResponse;
import com.flowhyemin.extensionfilter.domain.fixextension.service.FixExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final CustomExtensionService customExtensionService;
    private final FixExtensionService fixExtensionService;

    @GetMapping("/")
    public String home(Model model) {
        List<FixExtensionGetResponse> fixExtensionList = fixExtensionService.findAllFixExtension();
        List<CustomExtension> customList = customExtensionService.findAllCustomExtension();

        model.addAttribute("fixList", fixExtensionList);
        model.addAttribute("customList", customList);
        return "home";
    }
}
