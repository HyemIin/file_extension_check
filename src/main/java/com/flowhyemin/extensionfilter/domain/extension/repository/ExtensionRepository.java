package com.flowhyemin.extensionfilter.domain.extension.repository;

import com.flowhyemin.extensionfilter.domain.extension.entity.Extension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtensionRepository extends JpaRepository<Extension,Long> {
}
