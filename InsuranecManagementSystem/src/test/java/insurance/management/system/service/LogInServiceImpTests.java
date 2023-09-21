package insurance.management.system.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import insurance.management.system.InsuranecManagementSystemApplication;
import insurance.management.system.dto.LogInResponse;
import insurance.management.system.entity.Agent;
import insurance.management.system.repository.AgentRepository;
import insurance.management.system.repository.CustomerRepository;
import insurance.management.system.repository.InsuranceCompanyRepository;

@SpringBootTest(classes= InsuranecManagementSystemApplication.class)
public class LogInServiceImpTests {

	@Autowired
	LogInservice loginService;

	@MockBean
	AgentRepository agentRepository;

	@MockBean
	CustomerRepository customerRepository;

	@MockBean
	InsuranceCompanyRepository companyRepository;

	@Test
	public void testCheckAccess() throws Exception {
		String role= "agent";
		String email ="aa@gmail.com";
		String password = "aaa";
		
		Agent agent = new Agent();
		agent.setEmail("aa@gmail.com");
		agent.setPassword("bb");
		agent.setAgentId("rthb");
		agent.setRole("fg");
		
		
		Mockito.when(agentRepository.findByEmailAndPassword(email, password)).thenReturn(agent);
		LogInResponse response=(LogInResponse)loginService.checkAccess(email, password, role);
		assertEquals("aa@gmail.com",response.getEmail());
	}

}
