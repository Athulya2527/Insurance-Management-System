package insurance.management.system.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import insurance.management.system.dto.CompanyDTO;
import insurance.management.system.entity.InsuranceCompany;
import insurance.management.system.exception.AlreadyExistsException;
import insurance.management.system.exception.NoSuchElementFoundException;
import insurance.management.system.repository.InsuranceCompanyRepository;

@Service
public class InsuranceCompanyServiceImp implements InsuranceCompanyService {

	@Autowired
	private InsuranceCompanyRepository companyRepository;

	// Company creation
	@Override
	public InsuranceCompany createCompany(InsuranceCompany company) throws Exception {
		if (companyRepository.existsByEmail(company.getEmail())) {
			throw new AlreadyExistsException("Email already exists ");
		} else {
			String companyId = companyIdGeneration();
			company.setCompanyId(companyId);
			return companyRepository.save(company);
		}
	}

	public String companyIdGeneration() {
		String prefix = "COMPANY";
		String suffix = "0001";

		String companyId = prefix + suffix;
		while (companyRepository.existsByCompanyId(companyId)) {
			int currentSuffix = Integer.parseInt(suffix);
			currentSuffix++;
			suffix = String.format("%04d", currentSuffix);
			companyId = prefix + suffix;
		}
		return companyId;
	}

    // Search by all company
	@Override
	public List<CompanyDTO> getAllCompanies() {
	    List<InsuranceCompany> company = companyRepository.findAll();
	    return convertToCompanyDTO(company);
	}

	// Search by company id
	@Override
	public List<CompanyDTO> getCompanyByCompanyId(String companyId) {
	    List<InsuranceCompany> companies = companyRepository.findByCompanyId(companyId);
	    if (companies.isEmpty()) {
	        throw new NoSuchElementFoundException("No company fou)nd with the given company Id");
	    } else {
	        return convertToCompanyDTO(companies);
	    }
	}

	public List<CompanyDTO> convertToCompanyDTO(List<InsuranceCompany> companies) {
	    List<CompanyDTO> companyDTOs = new ArrayList<>();
	    for (InsuranceCompany company : companies) {
	        CompanyDTO companyDTO = new CompanyDTO();
	        companyDTO.setCompanyId(company.getCompanyId());
	        companyDTO.setRole(company.getRole());
	        companyDTO.setName(company.getName());
	        companyDTO.setEmail(company.getEmail());
	        companyDTO.setContactNumber(company.getContactNumber());
	        companyDTO.setAddress(company.getAddress());
//	        companyDTO.setPolicies((company.getPolicies()));
	        companyDTOs.add(companyDTO);
	    }
	    return companyDTOs;
	}

	@Override
	public List<String> getAllCompanyId() {
		
		return companyRepository.findAllCompanyId();
	}

	
}
