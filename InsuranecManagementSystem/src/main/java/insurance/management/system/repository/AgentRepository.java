package insurance.management.system.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import insurance.management.system.entity.Agent;
import insurance.management.system.entity.InsuranceCompany;

@Repository
public interface AgentRepository extends JpaRepository<Agent, String> {

	boolean existsByPassword(String password);
	
	boolean existsByContactNumber(String contactNumber);

	boolean existsByEmail(String email);

	Agent findByEmailAndPassword(String email, String password);

	boolean existsByAgentId(String agentId);

	Agent getAgentByAgentId(String agentId);
	
	List<Agent> findByAgentId(String agentId);

	List<Agent> findByInsuranceCompany(InsuranceCompany insuranceCompany);


}
