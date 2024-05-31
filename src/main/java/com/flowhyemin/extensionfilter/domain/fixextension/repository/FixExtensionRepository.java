package com.flowhyemin.extensionfilter.domain.fixextension.repository;

import com.flowhyemin.extensionfilter.domain.fixextension.entity.FixExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FixExtensionRepository extends JpaRepository<FixExtension,Long> {

    List<FixExtension> findAllByIsFixed(Boolean isFixed);

}

