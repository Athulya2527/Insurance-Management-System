package insurance.management.system.service;

import java.util.List;


import insurance.management.system.dto.CustomerDTO;
import insurance.management.system.entity.Customer;

public interface CustomerService {
	
	Customer createCustomer(Customer customer, String agentId) throws Exception;

	List<CustomerDTO> getAllCustomers();

	List<CustomerDTO> getCustomerByFirstName(String firstName) throws Exception;
	
	List<CustomerDTO> getCustomerByLastName(String lastName) throws Exception;

	Customer updateCustomer(String customerId, Customer customer) throws Exception;

	void deleteCustomer(String customerId) throws Exception;

	List<CustomerDTO> getCustomerByCustomerId(String customerId) throws Exception;

	void deleteCustomerWithPolicies(String customerId);

}
