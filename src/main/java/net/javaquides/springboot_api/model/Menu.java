package net.javaquides.springboot_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Menu")
public class Menu {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "icon")
    private String icon;

    @Column(name = "display_order")
    private Integer displayOrder;

    @CreationTimestamp
    @Column(name = "create_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDateTime updatedAt;
    @PrePersist
    @PreUpdate
    private void generateUrlFromName() {
        if (this.name != null && !this.name.isEmpty()) {
            this.url = this.name.toLowerCase()
                    .replaceAll("[^a-z0-9]+", "-")  // Replace non-alphanumeric with hyphen
                    .replaceAll("^-+|-+$", "");     // Trim leading/trailing hyphens
        }
    }
}


