package net.javaquides.springboot_api.repository;
import net.javaquides.springboot_api.model.SubMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubMenuRepository extends JpaRepository<SubMenu, Long> {
}
