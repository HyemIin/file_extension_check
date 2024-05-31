package com.flowhyemin.extensionfilter.domain.fixextension.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
public class FixExtension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isFixed;
}
