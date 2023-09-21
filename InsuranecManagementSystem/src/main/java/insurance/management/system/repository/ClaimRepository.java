package insurance.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import insurance.management.system.entity.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Integer> {

}
