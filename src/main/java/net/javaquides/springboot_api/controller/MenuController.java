package net.javaquides.springboot_api.controller;

import net.javaquides.springboot_api.exception.ResourveNotFoundException;
import net.javaquides.springboot_api.model.Employee;
import net.javaquides.springboot_api.model.Menu;
import net.javaquides.springboot_api.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;

    @GetMapping
    //List Menu
    public List<Menu> getAllMenu(){
        return menuRepository.findAll();
    }
    //Store Menu
    @PostMapping
    public Menu storeMenu (@RequestBody Menu menu){
       return menuRepository.save(menu);
    }


}
