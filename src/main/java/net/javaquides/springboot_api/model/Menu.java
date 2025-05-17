package net.javaquides.springboot_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Menu")
public class Menu {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Menu name field is required")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "icon", nullable = false)
    @NotBlank(message = "Icon field is required")
    private String icon;

    @Column(name = "display_order", nullable = false)
    @NotNull(message = "Display order field is required")
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


