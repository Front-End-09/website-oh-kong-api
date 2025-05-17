package net.javaquides.springboot_api.controller;

import net.javaquides.springboot_api.exception.ResourveNotFoundException;
import net.javaquides.springboot_api.model.Employee;
import net.javaquides.springboot_api.model.Menu;
import net.javaquides.springboot_api.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.FieldError;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    // Add this exception handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        response.put("errors", errors);
        return ResponseEntity.badRequest().body(response);
    }

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping
    //List Menu
    public List<Menu> getAllMenu(){
        return menuRepository.findAll();
    }

    //Store Menu
    @PostMapping
    public ResponseEntity<?> storeMenu (@Valid @RequestBody Menu menu){
        Menu saveMenu = menuRepository.save(menu);
       return ResponseEntity.ok(saveMenu);
    }
    @GetMapping("{id}")
   public ResponseEntity<Map<String, Object>> detailMenu(@PathVariable Long id){
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            Menu menu = menuRepository.findById(id)
                    .orElseThrow(() -> new ResourveNotFoundException("Menu not exist with id: " + id));

            Map<String, Object> result = new LinkedHashMap<>();
            result.put("id", menu.getId());
            result.put("name", menu.getName());
            result.put("url", menu.getUrl());
            result.put("icon", menu.getIcon());
            result.put("displayOrder", menu.getDisplayOrder());
            result.put("createAt", menu.getCreatedAt());
            result.put("updateAt", menu.getUpdatedAt());

            response.put("status", 200);
            response.put("result", result);

            return ResponseEntity.ok(response);
        }catch (ResourveNotFoundException ex){
            response.put("status", 404);
            response.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
   }

}
