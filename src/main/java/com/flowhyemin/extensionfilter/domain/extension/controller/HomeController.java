package com.flowhyemin.extensionfilter.domain.extension.controller;

import com.flowhyemin.extensionfilter.domain.extension.entity.Extension;
import com.flowhyemin.extensionfilter.domain.extension.service.CustomExtensionService;
import com.flowhyemin.extensionfilter.domain.extension.service.FixExtensionService;
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
        List<Extension> fixExtensionList = fixExtensionService.findAllFixExtension();
        List<Extension> customList = customExtensionService.findAllCustomExtension();

        model.addAttribute("fixList", fixExtensionList);
        model.addAttribute("customList", customList);
        return "home";
    }
}
