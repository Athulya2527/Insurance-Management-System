package insurance.management.system.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import insurance.management.system.InsuranecManagementSystemApplication;
import insurance.management.system.entity.Agent;
import insurance.management.system.entity.InsuranceCompany;
import insurance.management.system.exception.AlreadyExistsException;
import insurance.management.system.repository.AgentRepository;
import insurance.management.system.repository.InsuranceCompanyRepository;

@SpringBootTest(classes = InsuranecManagementSystemApplication.class)
public class AgentServiceImpTests {

	@Autowired
	AgentService agentService;

	@Mock
	AgentRepository agentRepository;

	@Mock
	InsuranceCompanyRepository companyRepository;

	@Test
	public void testCreateAgent() {
		// Arrange
		Agent agent = new Agent();
		agent.setEmail("aabb@gmail.com");
		agent.setRole("agent");
		agent.setAgentId("agent100");
		agent.setFullName("aabb");
		agent.setContactNumber("7778129878");
		agent.setAddress("aabbccddff");
		agent.setCommissionRate(1500);

		InsuranceCompany company = new InsuranceCompany();
		company.setCompanyId("company123");

		agent.setInsuranceCompany(company);

		Mockito.when(agentRepository.existsByEmail(agent.getEmail())).thenReturn(false);
		Mockito.when(agentRepository.save(any())).thenReturn(agent);
		Mockito.when(companyRepository.getCompanyByCompanyId(company.getCompanyId())).thenReturn(company);

		// Act
		Agent createdAgent = agentService.createAgent(agent);

		// Assert
		assertNotNull(createdAgent);
		assertEquals(company, createdAgent.getInsuranceCompany());
		assertNotNull(createdAgent.getAgentId());

		// Verify that the repository methods were called
		Mockito.verify(agentRepository, times(1)).existsByEmail(agent.getEmail());
		Mockito.verify(agentRepository, times(1)).save(agent);
		Mockito.verify(companyRepository, times(1)).getCompanyByCompanyId(company.getCompanyId());
	}

	@Test
	public void testCreateAgentWithExistingEmail() {
		// Arrange
		Agent agent = new Agent();
		agent.setEmail("existing@example.com");

		when(agentRepository.existsByEmail(agent.getEmail())).thenReturn(true);

		// Act and Assert
		assertThrows(AlreadyExistsException.class, () -> {
			agentService.createAgent(agent);
		});

		// Verify that the repository method was called
		verify(agentRepository, times(1)).existsByEmail(agent.getEmail());
		verify(agentRepository, never()).save(agent);
	}
}
