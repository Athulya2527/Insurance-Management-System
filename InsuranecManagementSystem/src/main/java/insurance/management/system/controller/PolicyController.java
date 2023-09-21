package insurance.management.system.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import insurance.management.system.dto.PolicyDTO;
import insurance.management.system.entity.Policy;
import insurance.management.system.exception.NoSuchElementFoundException;
import insurance.management.system.service.PolicyService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/policy")
@CrossOrigin(origins = "http://localhost:4200")
public class PolicyController {

	@Autowired
	private PolicyService policyService;

	
	@PostMapping("/createpolicy/{agentId}")
	public ResponseEntity<?> createPolicy(@RequestBody @Valid Policy policy,  @PathVariable String agentId) {
		try {
			Policy createdPolicies = policyService.createPolicy(policy, agentId);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdPolicies);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/viewpolicy/{policyNumber}")
	public ResponseEntity<?> getCustomers(@PathVariable String policyNumber) throws Exception {

		try {
			if (policyNumber != null) {
				List<PolicyDTO> policy = policyService.getPolicyByPolicyNumber(policyNumber);
				return ResponseEntity.status(HttpStatus.OK).body(policy);
			}
			// If no query parameter is provided, return all customers
			List<PolicyDTO> policy = policyService.getAllpolicies();
			return ResponseEntity.status(HttpStatus.OK).body(policy);

		} catch (NoSuchElementFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/viewallPolicy")
	public ResponseEntity<?> getAllPolicies() {
	    try {
	        List<PolicyDTO> policy = policyService.getAllpolicies();
	        if (policy.isEmpty()) {
	            return ResponseEntity.noContent().build();
	        }
	        return ResponseEntity.ok(policy);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("An error occurred while retrieving the policies ");
	    }
	}


	@PutMapping("update-policy/{id}")
	public Policy updatePolicy(@PathVariable Integer id, @RequestBody Policy policy) {
		return policyService.updatePolicy(id, policy);
	}

	@DeleteMapping("delete-policy/{id}")
	public void deletePolicy(@PathVariable Integer id) {
		policyService.deletePolicy(id);
	}
	
	@GetMapping("/allpolcynumber")
	public List<String> getPolicyNumber(){
		return policyService.getPolicyNumber();
	}

}
