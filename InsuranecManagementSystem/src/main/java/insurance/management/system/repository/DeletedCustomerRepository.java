package insurance.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import insurance.management.system.entity.DeletedCustomer;

@Repository
public interface DeletedCustomerRepository extends JpaRepository<DeletedCustomer, Integer> {

	boolean existsByCustomerId(String customerId);

}
