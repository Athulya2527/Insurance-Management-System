package insurance.management.system.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import insurance.management.system.InsuranecManagementSystemApplication;
import insurance.management.system.entity.Agent;
import insurance.management.system.entity.Customer;
import insurance.management.system.exception.AlreadyExistsException;
import insurance.management.system.repository.AgentRepository;
import insurance.management.system.repository.CustomerRepository;

@SpringBootTest(classes= InsuranecManagementSystemApplication.class)
public class CustomerServiceImpTests {
	
	@MockBean
	CustomerRepository customerRepository;
	
	@MockBean
	AgentRepository agentRepository;
	
	@Autowired
	CustomerService customerService;

	@Test
	public void testCreateCustomerSuccess() throws Exception {
	   
	    Customer customer = new Customer();
	    customer.setPassword("athulya@173");
	    customer.setRole("Customer");
	    customer.setPrefix("Miss");
	    customer.setFirstName("Athulya");
	    customer.setMiddleName("");
	    customer.setLastName("P");
	    customer.setEmail("athulya1@1com.co.in");
	    customer.setContactNumber("7778129878");
	    customer.setAlternateNumber("8764322111");
	    customer.setDateOfBirth(LocalDate.parse("1998-10-15"));
	    customer.setMaritalStatus("Unmarried");
	    customer.setGender("Female");
	    customer.setAddress("Panagavil, Kozhikode, 673019");
	    
	    String agentId = "agent123";
        Agent agent = new Agent();
        agent.setAgentId(agentId);
	    
        Mockito.when(agentRepository.getAgentByAgentId(agentId)).thenReturn(agent);
	    Mockito.when(customerRepository.existsByEmail(customer.getEmail())).thenReturn(false);
	    Mockito.when(customerRepository.save(any(Customer.class))).thenReturn(customer);
	    
        Customer createdCustomer = customerService.createCustomer(customer, agentId);

        // Assert
        assertNotNull(createdCustomer);
        assertEquals(agent, createdCustomer.getAgent());
        assertNotNull(createdCustomer.getCustomerId());
        assertEquals(customer.getDateOfBirth(), createdCustomer.getDateOfBirth());

//        Verify that the repository methods were called
        Mockito.verify(agentRepository, times(1)).getAgentByAgentId(agentId);
        Mockito.verify(customerRepository, times(1)).existsByEmail(customer.getEmail());
        Mockito.verify(customerRepository, times(1)).save(customer);
        
    }
	
	@Test
    public void testCreateCustomerWithExistingEmail() {
        // Arrange
        Customer customer = new Customer();
        customer.setEmail("existing@example.com");
        String agentId = "agent123";

        Mockito.when(customerRepository.existsByEmail(customer.getEmail())).thenReturn(true);

        // Act and Assert
        assertThrows(AlreadyExistsException.class, () -> {
            customerService.createCustomer(customer, agentId);
        });

        // Verify that the repository method was called
        Mockito.verify(customerRepository, times(1)).existsByEmail(customer.getEmail());
        Mockito.verify(customerRepository, never()).save(customer);
    }
}   
	    

