package insurance.management.system.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import insurance.management.system.entity.Claim;
import insurance.management.system.repository.ClaimRepository;

@Service
public class ClaimServiceImp implements ClaimService {

	@Autowired
    private ClaimRepository claimRepository;

    public Claim submitClaim(Claim claim) {
        String claimReferenceNumber = generateClaimReferenceNumber();
        claim.setClaimNumber(claimReferenceNumber);
        return claimRepository.save(claim);
    }

    private String generateClaimReferenceNumber() {
        return "CLAIM" + String.format("%06d", new Random().nextInt(1000000));
    }

}