package insurance.management.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import insurance.management.system.entity.Agent;
import insurance.management.system.entity.Customer;
import insurance.management.system.entity.InsuranceCompany;
import insurance.management.system.exception.NoSuchElementFoundException;
import insurance.management.system.service.LogInservice;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LogInController {

    @Autowired
    private LogInservice loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody(required = false) Agent agent,
                                   @RequestBody(required = false) Customer customer,
                                   @RequestBody(required = false) InsuranceCompany company) throws Exception {
        String role = null;
        String password = null;
        String email = null;

        if (agent != null) {
            role = agent.getRole();
            password = agent.getPassword();
            email = agent.getEmail();
        } else if (customer != null) {
            role = customer.getRole();
            password = customer.getPassword();
            email = customer.getEmail();
        } else if (company != null) {
            role = company.getRole();
            password = company.getPassword();
            email = company.getEmail();
        }
       
        if (role != null && password != null && email != null) {
            try {
                Object user = loginService.checkAccess(email, password, role);
                if (user != null) {
                    return ResponseEntity.ok().body(user);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access denied");
                }
            } catch (NoSuchElementFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        }
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fill all fields"); 
    }
}

