package net.javaquides.springboot_api.repository;

import net.javaquides.springboot_api.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}