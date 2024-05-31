package com.flowhyemin.extensionfilter.domain.customextension.repository;

import com.flowhyemin.extensionfilter.domain.fixextension.entity.FixExtension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomExtensionRepository extends JpaRepository<FixExtension,Long> {

}

