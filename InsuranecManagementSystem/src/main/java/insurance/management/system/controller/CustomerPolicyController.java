package insurance.management.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import insurance.management.system.entity.CustomerPolicy;
import insurance.management.system.service.CustomerPolicyService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerPolicyController {

	@Autowired
	private CustomerPolicyService customerPolicyService;
	

	@PostMapping("/addpolicy")
	public ResponseEntity<?> addPolicyForCustomer(@RequestBody CustomerPolicy customerPolicy) {
		CustomerPolicy createdCustomerPolicy = customerPolicyService.saveCustomerPolicy(customerPolicy);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerPolicy);
	}
}
	
