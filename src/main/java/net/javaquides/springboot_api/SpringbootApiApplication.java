package net.javaquides.springboot_api;

import net.javaquides.springboot_api.model.Menu;
import net.javaquides.springboot_api.model.SubMenu;
import net.javaquides.springboot_api.repository.EmployeeRepository;
import net.javaquides.springboot_api.repository.MenuRepository;
import net.javaquides.springboot_api.repository.SubMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDateTime;

@SpringBootApplication
public class SpringbootApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApiApplication.class, args);
	}

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private SubMenuRepository subMenuRepository;

	@Override
	public void run(String... args) {
		Menu menu = new Menu();
		menu.setDisplayOrder(1);
		menu.setName("Home Page");
		menu.setUrl("/home page");
		menu.setIcon("/icon");
		menu.setCreatedAt(LocalDateTime.now());
		menuRepository.save(menu);

//		SubMenu subMenu = new SubMenu();
//		subMenu.setDisplayOrder(1);
//		subMenu.setName("Home");
//		subMenu.setUrl("/home");
//		subMenu.setIcon("/icon");
//		subMenu.setParentId(1);
//		subMenu.setCreatedAt(LocalDateTime.now());
//		subMenuRepository.save(subMenu);
	}
}
