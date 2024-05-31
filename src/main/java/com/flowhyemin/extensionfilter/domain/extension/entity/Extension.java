package com.flowhyemin.extensionfilter.domain.extension.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class Extension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Boolean name;
    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isDefault;
    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isFixed;
}
