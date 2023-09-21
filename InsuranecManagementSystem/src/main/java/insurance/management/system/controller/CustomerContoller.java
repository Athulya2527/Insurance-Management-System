package insurance.management.system.controller;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import insurance.management.system.dto.CustomerDTO;
import insurance.management.system.dto.Response;
import insurance.management.system.entity.Customer;
import insurance.management.system.exception.AlreadyExistsException;
import insurance.management.system.exception.NoSuchElementFoundException;
import insurance.management.system.service.CustomerService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerContoller {

	@Autowired
	private CustomerService customerService;
	

	@PostMapping("/createcustomer/{agentId}")
	public ResponseEntity<?> createCustomer(@RequestBody @Valid Customer customer, @PathVariable String agentId) throws Exception {
	    try {
	    	System.out.println(customer);
	        Customer createdCustomer = customerService.createCustomer(customer, agentId);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
	    } catch (AlreadyExistsException e) {
	    	Response errorResponse = new Response(HttpStatus.CONFLICT, e.getMessage());
	    	return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	        
	    } catch (ConstraintViolationException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
//	    }catch (Exception e) {
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//	                .body("An error occurred while retrieving this. ");
	    }
	}

	
	@GetMapping("/viewcustomer")
	public ResponseEntity<?> getCustomers(@RequestParam(required = false)String firstName,
			@RequestParam(required = false) String lastName) throws Exception {

		try {
			if (firstName != null) {
				List<CustomerDTO> customers = customerService.getCustomerByFirstName(firstName);
				return ResponseEntity.status(HttpStatus.OK).body(customers);
			}
			if (lastName != null) {
				List<CustomerDTO> customers = customerService.getCustomerByLastName(lastName);
				return ResponseEntity.status(HttpStatus.OK).body(customers);
			}
			// If no query parameter is provided, return all customers
			List<CustomerDTO> customers = customerService.getAllCustomers();
			return ResponseEntity.status(HttpStatus.OK).body(customers);

		} catch (NoSuchElementFoundException e) {
         	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	@GetMapping("/getcustomer/{customerId}")
	public ResponseEntity<?> getCustoemrByCustomerId(@PathVariable String customerId) {
	    try {
	        List<CustomerDTO> customer = customerService.getCustomerByCustomerId(customerId);
	        if (customer.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(customer);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("An error occurred while retrieving the company: " + e.getMessage());
	    }
	}

	@PutMapping("updatecustomer/{customerId}")
	public ResponseEntity<?> updateCustomer(@PathVariable String customerId, @RequestBody Customer customer) {
	    try {
	        Customer updatedCustomer = customerService.updateCustomer(customerId, customer);
	        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCustomer);
	    } catch (Exception e) {
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(e.getMessage()));
	    }
		return null;
	}


	@DeleteMapping("/deletecustomer/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable String customerId) {
		try {
			customerService.deleteCustomerWithPolicies(customerId);
			Response response = new Response(HttpStatus.CREATED, "Customer successfully deleted");
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (NoSuchElementFoundException e) {
			Response response = new Response(HttpStatus.NOT_FOUND, e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	
	}

}
