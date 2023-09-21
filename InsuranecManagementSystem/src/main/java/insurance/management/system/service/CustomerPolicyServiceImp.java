package insurance.management.system.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import insurance.management.system.entity.Customer;
import insurance.management.system.entity.CustomerPolicy;
import insurance.management.system.entity.Policy;
import insurance.management.system.repository.CustomerPolicyRepository;
import insurance.management.system.repository.CustomerRepository;
import insurance.management.system.repository.PolicyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CustomerPolicyServiceImp implements CustomerPolicyService {

	@Autowired
	private CustomerPolicyRepository customerPolicyRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private PolicyRepository policyRepository;
	
	
	@Override
	public CustomerPolicy saveCustomerPolicy(CustomerPolicy customerPolicy) {

		Customer customerData =customerRepository.findByCustomerId(customerPolicy.getCustomer().getCustomerId());
		Policy policyData = policyRepository.findByPolicyId(customerPolicy.getPolicy().getPolicyId());
		System.out.println(policyData);
	    int coveragePeriod = policyData.getCoveragePeriod();
	    customerPolicy.setPolicy(policyData);
		customerPolicy.setCustomer(customerData);
		customerPolicy.setSelectedPolicyNumber(policyData.getPolicyNumber());
		LocalDate endDate = LocalDate.now().plusYears(coveragePeriod);
		customerPolicy.setStartDate(LocalDate.now());
		customerPolicy.setEndDate(endDate);
		
		return customerPolicyRepository.save(customerPolicy);

	}
	

}
