package insurance.management.system.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import insurance.management.system.dto.CustomerDTO;
import insurance.management.system.entity.Agent;
import insurance.management.system.entity.Customer;
import insurance.management.system.entity.DeletedCustomer;
import insurance.management.system.exception.AlreadyExistsException;
import insurance.management.system.exception.NoSuchElementFoundException;
import insurance.management.system.repository.AgentRepository;
import insurance.management.system.repository.CustomerPolicyRepository;
import insurance.management.system.repository.CustomerRepository;
import insurance.management.system.repository.DeletedCustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImp implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private DeletedCustomerRepository deletedCustomerRepository;
	@Autowired
	private AgentRepository agentRepository;
	@Autowired
	private CustomerPolicyRepository customerPolicyRepository;

	// Customer Creation
	@Override
	public Customer createCustomer(Customer customer, String agentId) {
		System.out.println(customer.toString());
		if (customerRepository.existsByEmail(customer.getEmail())) {
			throw new AlreadyExistsException("Email already exists");

		} else {
			Agent agent = agentRepository.getAgentByAgentId(agentId);
			String customerId = customerIdGeneration();
			customer.setAgent(agent);
			customer.setCustomerId(customerId);
			LocalDate date = customer.getDateOfBirth();
			customer.setDateOfBirth(date);
			return customerRepository.save(customer);
		}
	}

	public String customerIdGeneration() {
		String prefix = "CUSTOM";
		String suffix = "0001";

		String customerId = prefix + suffix;
		while (customerRepository.existsByCustomerId(customerId)) {
			int currentSuffix = Integer.parseInt(suffix);
			currentSuffix++;
			suffix = String.format("%04d", currentSuffix);
			customerId = prefix + suffix;
		}
		return customerId;
	}

	// Search by all
	@Override
	public List<CustomerDTO> getAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		return convertToCustomerDTO(customers);
	}

	// Search by firstname
	@Override
	public List<CustomerDTO> getCustomerByFirstName(String firstName) {
		List<Customer> customers = customerRepository.findByFirstNameIgnoreCase(firstName);
		if (customers.isEmpty()) {
			throw new NoSuchElementFoundException("No customers found with the given first name: " + firstName);
		} else {
			return convertToCustomerDTO(customers);
		}
	}

	// Search by lastname
	@Override
	public List<CustomerDTO> getCustomerByLastName(String lastName) throws Exception {
		List<Customer> customers = customerRepository.findByLastNameIgnoreCase(lastName);
		if (customers.isEmpty()) {
			throw new NoSuchElementFoundException("No customers found with the given last name: " + lastName);
		} else {
			return convertToCustomerDTO(customers);
		}
	}

	// Get customer value by Customer Id
	@Override
	public List<CustomerDTO> getCustomerByCustomerId(String customerId) {
		Customer customer = customerRepository.findByCustomerId(customerId);
		if (customer == null) {
			throw new NoSuchElementFoundException("No customer found with the given customer Id");
		} else {
			return convertToCustomerDTO(List.of(customer));
		}
	}

	// Coversion of customer to customer dto
	public List<CustomerDTO> convertToCustomerDTO(List<Customer> customers) {
		List<CustomerDTO> customerDTOs = new ArrayList<>();
		for (Customer customer : customers) {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setPrefix(customer.getPrefix());
			customerDTO.setFirstName(customer.getFirstName());
			customerDTO.setLastName(customer.getLastName());
			customerDTO.setLastName(customer.getLastName());
			customerDTO.setContactNumber(customer.getContactNumber());
			customerDTO.setAlternateNumber(customer.getAlternateNumber());
			customerDTO.setEmail(customer.getEmail());
			customerDTO.setDateOfBirth(customer.getDateOfBirth());
			customerDTO.setMaritalStatus(customer.getMaritalStatus());
			customerDTO.setGender(customer.getGender());
			customerDTO.setAddress(customer.getAddress());
//			customerDTO.setNomineeName(customer.getNomineeName());
//			customerDTO.setNomineeDateOfBirth(customer.getNomineeDateOfBirth());
//			customerDTO.setNomineePhoneNumber(customer.getNomineePhoneNumber());
//			customerDTO.setRelationship(customer.getRelationship());
			// customerDTO.setPolicies(customer.getPolicies());

			customerDTOs.add(customerDTO);
		}
		return customerDTOs;
	}

	// Customer Updation

	@Override
	public Customer updateCustomer(String customerId, Customer customer) {
		Customer existingCustomer = customerRepository.findByCustomerId(customerId);

		if (existingCustomer != null) {
			existingCustomer.setPrefix(customer.getPrefix());
			existingCustomer.setFirstName(customer.getFirstName());
			existingCustomer.setMiddleName(customer.getMiddleName());
			existingCustomer.setLastName(customer.getLastName());
			existingCustomer.setContactNumber(customer.getContactNumber());
			existingCustomer.setAlternateNumber(customer.getAlternateNumber());
			existingCustomer.setDateOfBirth(customer.getDateOfBirth());
			existingCustomer.setMaritalStatus(customer.getMaritalStatus());
			existingCustomer.setGender(customer.getGender());
			existingCustomer.setAddress(customer.getAddress());
//	        existingCustomer.setNomineeName(customer.getNomineeName());
//	        existingCustomer.setNomineeDateOfBirth(customer.getNomineeDateOfBirth());
//	        existingCustomer.setNomineePhoneNumber(customer.getNomineePhoneNumber());
//	        existingCustomer.setRelationship(customer.getRelationship());

			return customerRepository.save(existingCustomer);
		} else {
			throw new NoSuchElementFoundException("The customer does not exist");
		}
	}

	@Override
	public void deleteCustomer(String customerId) {
		Customer customer = customerRepository.findByCustomerId(customerId);
		if (customer != null) {
			DeletedCustomer deletedCustomer = new DeletedCustomer();
			deletedCustomer.setCustomerId(customer.getCustomerId());
			// deletedCustomer.setPassword(customer.getPassword());
			deletedCustomer.setRole(customer.getRole());
			deletedCustomer.setPrefix(customer.getPrefix());
			deletedCustomer.setFirstName(customer.getFirstName());
			deletedCustomer.setMiddleName(customer.getMiddleName());
			deletedCustomer.setLastName(customer.getLastName());
			deletedCustomer.setContactNumber(customer.getContactNumber());
			deletedCustomer.setAlternateNumber(customer.getAlternateNumber());
			deletedCustomer.setEmail(customer.getEmail());
			deletedCustomer.setDateOfBirth(customer.getDateOfBirth());
			deletedCustomer.setMaritalStatus(customer.getMaritalStatus());
			deletedCustomer.setGender(customer.getGender());
			deletedCustomer.setAddress(customer.getAddress());
//	    	   deletedCustomer.setNomineeName(customer.getNomineeName());
//	    	   deletedCustomer.setNomineeDateOfBirth(customer.getNomineeDateOfBirth());
//	    	   deletedCustomer.setNomineePhoneNumber(customer.getNomineePhoneNumber());
//	    	   deletedCustomer.setRelationship(customer.getRelationship());
			deletedCustomer.setDeletedTime(LocalDateTime.now());

			deletedCustomerRepository.save(deletedCustomer);
			customerRepository.delete(customer);
		} else {
			throw new NoSuchElementFoundException("The customer does not exist");
		}
	}

	@Transactional
	public void deleteCustomerWithPolicies(String customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new NoSuchElementFoundException("Customer not found"));

		customerPolicyRepository.deleteAll(customer.getCustomerPolicies());
		customerRepository.delete(customer);
	}
}
