package net.javaquides.springboot_api.controller;
import net.javaquides.springboot_api.exception.ResourveNotFoundException;
import net.javaquides.springboot_api.model.Employee;
import net.javaquides.springboot_api.model.SubMenu;
import net.javaquides.springboot_api.repository.EmployeeRepository;
import net.javaquides.springboot_api.repository.MenuRepository;
import net.javaquides.springboot_api.repository.SubMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;

import java.util.List;

@RestController
@RequestMapping("/api/subMenu")
public class SubMenuController {
    @Autowired
    private SubMenuRepository subMenuRepository;

    @GetMapping
    public List<SubMenu> getAllSubMenu(){
        return subMenuRepository.findAll();
    }
}
