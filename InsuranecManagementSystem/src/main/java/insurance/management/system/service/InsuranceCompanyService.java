package insurance.management.system.service;

import java.util.List;

import insurance.management.system.dto.CompanyDTO;
import insurance.management.system.entity.InsuranceCompany;


public interface InsuranceCompanyService {

	InsuranceCompany createCompany(InsuranceCompany company) throws Exception;

	List<CompanyDTO> getCompanyByCompanyId(String companyId) throws Exception;

	List<CompanyDTO> getAllCompanies();

	List<String> getAllCompanyId();

}
