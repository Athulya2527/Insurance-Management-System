package insurance.management.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import insurance.management.system.dto.AgentDTO;
import insurance.management.system.entity.Agent;
import insurance.management.system.entity.InsuranceCompany;
import insurance.management.system.exception.AlreadyExistsException;
import insurance.management.system.exception.NoSuchElementFoundException;
import insurance.management.system.repository.AgentRepository;
import insurance.management.system.repository.InsuranceCompanyRepository;

@Service
public class AgentServiceImp implements AgentService {

	@Autowired
	private AgentRepository agentRepository;
	@Autowired
	private InsuranceCompanyRepository companyRepository;

	@Override
	public Agent createAgent(Agent agent) {

		if (agentRepository.existsByEmail(agent.getEmail())) {
			throw new AlreadyExistsException("Email already exists ");
		} else {
			String agentId = agentIdGeneration();
			agent.setAgentId(agentId);
			InsuranceCompany company = companyRepository
					.getCompanyByCompanyId(agent.getInsuranceCompany().getCompanyId());
//			agent.setCompanyId(agent.getInsuranceCompany().getCompanyId());
			agent.setInsuranceCompany(company);
			return agentRepository.save(agent);
		}
	}

	public String agentIdGeneration() {
		String prefix = "AGENT";
		String suffix = "0001";

		String agentId = prefix + suffix;
		while (agentRepository.existsByAgentId(agentId)) {
			int currentSuffix = Integer.parseInt(suffix);
			currentSuffix++;
			suffix = String.format("%04d", currentSuffix);
			agentId = prefix + suffix;
		}
		return agentId;
	}

	@Override
	public List<AgentDTO> getAllAgents() {
		List<Agent> agent = agentRepository.findAll();
		return convertToAgentDTO(agent);
	}

	// Search by agent id
	@Override
	public List<AgentDTO> getAgentByAgentId(String agentId) {
		List<Agent> agent = agentRepository.findByAgentId(agentId);
		if (agent == null) {
			throw new NoSuchElementFoundException("No agent found with the given agentId");
		} else {
			return convertToAgentDTO(agent);
		}
	}

	public List<AgentDTO> convertToAgentDTO(List<Agent> agents) {
		List<AgentDTO> agentDTOs = new ArrayList<>();
		for (Agent agent : agents) {
			AgentDTO agentDTO = new AgentDTO();
			agentDTO.setAgentId(agent.getAgentId());
//			agentDTO.setCompanyId(agent.getCompanyId());
			agentDTO.setRole(agent.getRole());
			agentDTO.setFullName(agent.getFullName());
			agentDTO.setEmail(agent.getEmail());
			agentDTO.setContactNumber(agent.getContactNumber());
			agentDTO.setAddress(agent.getAddress());
			agentDTO.setCommissionRate(agent.getCommissionRate());
			agentDTOs.add(agentDTO);
		}
		return agentDTOs;
	}


	@Override
	public List<Agent> getAgentsByInsuranceCompany(InsuranceCompany insuranceCompany) {
		return agentRepository.findByInsuranceCompany(insuranceCompany);
	}

}
