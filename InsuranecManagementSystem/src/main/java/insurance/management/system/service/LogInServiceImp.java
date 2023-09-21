package insurance.management.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import insurance.management.system.dto.LogInResponse;
import insurance.management.system.entity.Agent;
import insurance.management.system.entity.Customer;
import insurance.management.system.entity.InsuranceCompany;
import insurance.management.system.exception.NoSuchElementFoundException;
import insurance.management.system.repository.AgentRepository;
import insurance.management.system.repository.CustomerRepository;
import insurance.management.system.repository.InsuranceCompanyRepository;

@Service
public class LogInServiceImp implements LogInservice {

	@Autowired
	private AgentRepository agentRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private InsuranceCompanyRepository companyRepository;

	@Override
	public Object checkAccess(String email, String password, String role) throws Exception {
		switch (role.toLowerCase()) {
		case "agent":
			Agent agent = agentRepository.findByEmailAndPassword(email, password);
			if (agent != null) {
				LogInResponse loginRes = new LogInResponse(agent.getAgentId(), agent.getFullName(), agent.getEmail(),
						agent.getRole());
				return loginRes;
			} else {
				throw new NoSuchElementFoundException("Invalid email or password ");
			}
		case "customer":
			Customer customer = customerRepository.findByEmailAndPassword(email, password);
			if (customer != null) {
				LogInResponse loginRes = new LogInResponse(customer.getCustomerId(), customer.getFirstName(), customer.getEmail(),
						customer.getRole());
				return loginRes;
			} else {
				throw new NoSuchElementFoundException("Invalid email or password ");
			}
		case "company":
			InsuranceCompany company = companyRepository.findByEmailAndPassword(email, password);
			if (company != null) {
				LogInResponse loginRes = new LogInResponse(company.getCompanyId(), company.getName(), company.getEmail(),
						company.getRole());
				return loginRes;
			} else {
				throw new NoSuchElementFoundException("Invalid email or password ");
			}
		default:
			throw new Exception("Invalid role");
		}
	}
}
