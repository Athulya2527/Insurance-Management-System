package insurance.management.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import insurance.management.system.dto.CompanyDTO;
import insurance.management.system.dto.Response;
import insurance.management.system.entity.InsuranceCompany;
import insurance.management.system.exception.AlreadyExistsException;
import insurance.management.system.exception.NoSuchElementFoundException;
import insurance.management.system.service.InsuranceCompanyService;

@RestController
@RequestMapping("/insurancecompany")
@CrossOrigin(origins = "http://localhost:4200")
public class InsuranceCompanyController {

	@Autowired
	private InsuranceCompanyService companyService;

	@PostMapping("/createcompany")
	public ResponseEntity<?> createCompany(@RequestBody InsuranceCompany company) {
		try {
			InsuranceCompany createdCompany = companyService.createCompany(company);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdCompany);
		}catch (AlreadyExistsException e) {
			Response errorResponse = new Response(HttpStatus.CONFLICT, e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/viewcompany/{companyId}")
	public ResponseEntity<?> getCompanyById(@PathVariable String companyId) {
		try {
			List<CompanyDTO> companies = companyService.getCompanyByCompanyId(companyId);
			if (companies.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(companies);
		} catch (NoSuchElementFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while retrieving this ");
		}
	}

	@GetMapping("/viewallcompany")
	public ResponseEntity<?> getAllCompanies() {
		try {
			List<CompanyDTO> companies = companyService.getAllCompanies();
			if (companies.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(companies);
		} catch (NoSuchElementFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while retrieving the companies: " + e.getMessage());
		}
	}

	@GetMapping("/allcompanyid")
	public List<String> getAllCompanyId() {
		return companyService.getAllCompanyId();

	}

}
