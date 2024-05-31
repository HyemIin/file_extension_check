package com.flowhyemin.extensionfilter.domain.customextension.repository;

import com.flowhyemin.extensionfilter.domain.customextension.entity.CustomExtension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomExtensionRepository extends JpaRepository<CustomExtension,Long> {

    void deleteByName(String name);
}

