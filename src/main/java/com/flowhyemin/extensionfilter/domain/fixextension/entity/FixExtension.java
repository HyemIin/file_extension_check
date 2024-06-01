package com.flowhyemin.extensionfilter.domain.fixextension.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FixExtension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @ColumnDefault("false")
    private String isChecked;

    public void updateFixExtension(String isChecked) {
        this.isChecked = isChecked;
    }
}
