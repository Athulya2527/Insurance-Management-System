package insurance.management.system.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import insurance.management.system.dto.AgentDTO;
import insurance.management.system.dto.CompanyDTO;
import insurance.management.system.dto.PolicyDTO;
import insurance.management.system.entity.Agent;
import insurance.management.system.entity.InsuranceCompany;
import insurance.management.system.entity.Policy;
import insurance.management.system.exception.NoSuchElementFoundException;
import insurance.management.system.repository.AgentRepository;
import insurance.management.system.repository.InsuranceCompanyRepository;
import insurance.management.system.repository.PolicyRepository;

@Service
public class PolicyServiceImp implements PolicyService {

	@Autowired
	private PolicyRepository policyRepository;
	@Autowired
	private InsuranceCompanyRepository companyRepository;
	@Autowired
	private AgentRepository agentRepository;
	
    // Policy creation
	@Override
	public Policy createPolicy( Policy policy, String agentId) throws Exception {
		String policyNumber = generatePolicyNumber(policy.getPolicyType());
		policy.setPolicyNumber(policyNumber);
		Agent agent = agentRepository.getAgentByAgentId(agentId);
		policy.setAgent(agent);
		InsuranceCompany company = companyRepository.getCompanyByCompanyId(policy.getInsuranceCompany().getCompanyId());
		policy.setInsuranceCompany(company);
		Policy createdPolicy = policyRepository.save(policy);
		return createdPolicy;
	}

	// Policy number generation
	private String generatePolicyNumber(String policyType) {
		String prefix = policyType.toUpperCase().trim();
		String year = String.valueOf(LocalDate.now().getYear());
		String suffix = "0000";

		String policyNumber = prefix + year + suffix;
		while (policyRepository.existsByPolicyNumber(policyNumber)) {
			int currentSuffix = Integer.parseInt(suffix);
			currentSuffix++;
			suffix = String.format("%04d", currentSuffix);
			policyNumber = prefix + year + suffix;
		}
		return policyNumber;
	}

	// Policy Updation
	@Override
	public Policy updatePolicy(Integer policyId, Policy policy) {
		Policy existingPolicy = policyRepository.findById(policyId).orElse(null);
		if (existingPolicy != null) {
			existingPolicy.setPolicyNumber(policy.getPolicyNumber());
			existingPolicy.setPolicyType(policy.getPolicyType());
			existingPolicy.setPremiumAmount(policy.getPremiumAmount());
			existingPolicy.setCoveragePeriod(policy.getCoveragePeriod());
			existingPolicy.setCoverageAmount(policy.getCoverageAmount());
			return policyRepository.save(existingPolicy);
		} else {
			return null;
		}
	}

	// Policy Deletion
	@Override
	public void deletePolicy(Integer id) {
		policyRepository.deleteById(id);

	}

	@Override
	public List<PolicyDTO> getPolicyByPolicyNumber(String policyNumber) {
		List<Policy> policy = policyRepository.findByPolicyNumber(policyNumber);
		if(policy.isEmpty()) {
			throw new NoSuchElementFoundException("No policy found with the given policy number");
		}
		return convertToPolicyDTO(policy);
	}

	@Override
	public List<PolicyDTO> getAllpolicies() {
		List<Policy> policy = policyRepository.findAll();
		return convertToPolicyDTO(policy);
	}

	public List<PolicyDTO> convertToPolicyDTO(List<Policy> policies) {
	    List<PolicyDTO> policyDTOs = new ArrayList<>();
	    for (Policy policy : policies) {
	        PolicyDTO policyDTO = new PolicyDTO();
	         policyDTO.setPolicyId(policy.getPolicyId());
	         policyDTO.setPolicyNumber(policy.getPolicyNumber());
	         policyDTO.setPolicyType(policy.getPolicyType());
	         policyDTO.setPremiumAmount(policy.getPremiumAmount());
	         policyDTO.setCoveragePeriod(policy.getCoveragePeriod());
	         policyDTO.setCoverageAmount(policy.getCoverageAmount());
	         policyDTO.setAgent(getAgentDTO(policy));
	         policyDTO.setCompany(getCompanyDTO(policy));
	        policyDTOs.add(policyDTO);
	    }
	    return policyDTOs;
	}

	private AgentDTO getAgentDTO(Policy policy) {
		AgentDTO agentDTO = new AgentDTO();
		agentDTO.setRole(policy.getAgent().getRole());
		agentDTO.setCompanyId(policy.getAgent().getInsuranceCompany().getCompanyId());
		agentDTO.setAgentId(policy.getAgent().getAgentId());
		agentDTO.setFullName(policy.getAgent().getFullName());
		agentDTO.setEmail(policy.getAgent().getEmail());
		agentDTO.setContactNumber(policy.getAgent().getContactNumber());
		agentDTO.setAddress(policy.getAgent().getAddress());
		return agentDTO;
	}
	
	private CompanyDTO getCompanyDTO(Policy policy) {
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setRole(policy.getInsuranceCompany().getRole());
		companyDTO.setCompanyId(policy.getInsuranceCompany().getCompanyId());
		companyDTO.setName(policy.getInsuranceCompany().getName());
		companyDTO.setEmail(policy.getInsuranceCompany().getEmail());
		companyDTO.setContactNumber(policy.getInsuranceCompany().getContactNumber());
		companyDTO.setAddress(policy.getInsuranceCompany().getAddress());
		return companyDTO;
	}

	@Override
	public List<String> getPolicyNumber() {
		
		return policyRepository.findAllPolicyNumber();
	}

	@Override
	public List<PolicyDTO> getPoliciesByAgent(String agentId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}