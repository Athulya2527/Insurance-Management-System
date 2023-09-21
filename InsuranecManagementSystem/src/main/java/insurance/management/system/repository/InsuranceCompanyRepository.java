package insurance.management.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import insurance.management.system.entity.InsuranceCompany;

@Repository
public interface InsuranceCompanyRepository extends JpaRepository<InsuranceCompany, String> {

	boolean existsByEmail(String email);

	List<InsuranceCompany> findByCompanyId(String companyId);

	boolean existsByCompanyId(String companyId);

	InsuranceCompany getCompanyByCompanyId(String companyId);
	
	InsuranceCompany findByEmailAndPassword(String email, String password);

	@Query("SELECT u.companyId FROM InsuranceCompany u")
	List<String> findAllCompanyId();

}
