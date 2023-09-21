package insurance.management.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import insurance.management.system.entity.Claim;
import insurance.management.system.service.ClaimService;

@RestController
@RequestMapping("/claim")
@CrossOrigin(origins = "http://localhost:4200")
public class ClaimController {
	@Autowired
    private ClaimService claimService;

    @PostMapping
    public ResponseEntity<Claim> submitClaim(@RequestBody Claim claim) {
        Claim submittedClaim = claimService.submitClaim(claim);
        return ResponseEntity.ok(submittedClaim);
    }
}
