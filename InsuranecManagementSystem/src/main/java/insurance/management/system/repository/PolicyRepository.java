package insurance.management.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import insurance.management.system.entity.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {

	boolean existsByPolicyNumber(String policyNumber);

	List<Policy> findByPolicyNumber(String policyNumber);

	@Query("SELECT u.policyNumber FROM Policy u")
	List<String> findAllPolicyNumber();

	Policy findByPolicyId(Integer policyId);


}
