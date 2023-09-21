package insurance.management.system.service;



import java.util.List;

import insurance.management.system.dto.AgentDTO;
import insurance.management.system.entity.Agent;
import insurance.management.system.entity.InsuranceCompany;


public interface AgentService {

	Agent createAgent(Agent agent) ;

	List<AgentDTO> getAllAgents();

	List<AgentDTO> getAgentByAgentId(String agentId) throws Exception;

	List<Agent> getAgentsByInsuranceCompany(InsuranceCompany insuranceCompany);

}
