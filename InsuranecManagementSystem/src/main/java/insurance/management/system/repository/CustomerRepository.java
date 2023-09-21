package insurance.management.system.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import insurance.management.system.entity.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{

	boolean existsByPassword(String password);

	boolean existsByContactNumber(String contactNumber);

	boolean existsByEmail(String email);
	
	List<Customer> findByFirstNameIgnoreCase(String firstName);

	List<Customer> findByLastNameIgnoreCase(String lastName);

	boolean existsByCustomerId(String customerId);

	Customer findByCustomerId(String customerId);

	Customer findByEmailAndPassword(String username, String password);

	
}
