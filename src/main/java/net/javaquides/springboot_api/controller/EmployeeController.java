package net.javaquides.springboot_api.controller;

import net.javaquides.springboot_api.exception.ResourveNotFoundException;
import net.javaquides.springboot_api.model.Employee;
import net.javaquides.springboot_api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;


import java.util.List;
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    //List Employee
    @GetMapping
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    // Build create employee REST API
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    // Detail Employee
    @GetMapping("{id}")
    public ResponseEntity<Map<String, Object>> getEmployeeById(@PathVariable long id) {
        Map<String, Object> response = new LinkedHashMap<>();  // Using LinkedHashMap to maintain insertion order

        try {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new ResourveNotFoundException("Employee not exist with id: " + id));

            // Create the result object with only the fields you want to show
            Map<String, Object> result = new LinkedHashMap<>();
            result.put("id", employee.getId());
            result.put("firstName", employee.getFirstName());
            result.put("lastName", employee.getLastName());
            result.put("emailId", employee.getEmailId());

            response.put("status", 200);
            response.put("result", result);

            return ResponseEntity.ok(response);

        } catch (ResourveNotFoundException ex) {
            response.put("status", 404);  // Changed from 500 to 404 for "Not Found"
            response.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    // Update Employee
    @PutMapping("{id}")
    public ResponseEntity<Map<String, Object>> updateEmployee(@PathVariable long id, @RequestBody Employee employeeDetail) {
        Map<String, Object> response = new HashMap<>();
        try {
            Employee updateEmployee = employeeRepository.findById(id)
                    .orElseThrow(() -> new ResourveNotFoundException("Employee not exist with id:" + id));

            updateEmployee.setFirstName(employeeDetail.getFirstName());
            updateEmployee.setLastName(employeeDetail.getLastName());
            updateEmployee.setEmailId(employeeDetail.getEmailId());

            employeeRepository.save(updateEmployee);

            response.put("success", true);
            response.put("message", "Employee with ID " + id + " was updated successfully.");
            return ResponseEntity.ok(response);

        } catch (ResourveNotFoundException ex) {
            response.put("success", false);
            response.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    //Delete employee
    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new ResourveNotFoundException("Employee not exist with id: " + id));
            employeeRepository.delete(employee);

            response.put("success", true);
            response.put("message", "Employee with ID " + id + " was deleted successfully.");
            return ResponseEntity.ok(response);

        } catch (ResourveNotFoundException ex) {
            response.put("success", false);
            response.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
