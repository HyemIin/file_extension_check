package com.flowhyemin.extensionfilter.domain.customextension.repository;

import com.flowhyemin.extensionfilter.domain.customextension.entity.CustomExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomExtensionRepository extends JpaRepository<CustomExtension,Long> {

    void deleteByName(String name);

    Optional<CustomExtension> findByName(String name);
}

