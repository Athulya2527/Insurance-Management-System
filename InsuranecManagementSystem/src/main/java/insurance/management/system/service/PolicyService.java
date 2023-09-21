package insurance.management.system.service;

import java.util.List;

import insurance.management.system.dto.PolicyDTO;
import insurance.management.system.entity.Policy;

public interface PolicyService {

	Policy createPolicy(Policy policy, String agentId) throws Exception;
	
	void deletePolicy(Integer id);

	Policy updatePolicy(Integer id, Policy policy);

	List<PolicyDTO> getPolicyByPolicyNumber(String policyNumber) throws Exception;

	List<PolicyDTO> getAllpolicies();

	List<PolicyDTO> getPoliciesByAgent(String agentId);

	List<String> getPolicyNumber();



//	List<PolicyDTO> getPoliciesByAgent(String agentId);



	

	
}
