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

import insurance.management.system.dto.AgentDTO;
import insurance.management.system.dto.Response;
import insurance.management.system.entity.Agent;
import insurance.management.system.entity.InsuranceCompany;
import insurance.management.system.exception.AlreadyExistsException;
import insurance.management.system.exception.NoSuchElementFoundException;
import insurance.management.system.service.AgentService;

@RestController
@RequestMapping("/agent")
@CrossOrigin(origins = "http://localhost:4200")
public class AgentController {

	@Autowired
	private AgentService agentService;

	@PostMapping("/createagent")
	public ResponseEntity<?> createAgent(@RequestBody Agent agent) {
		try {
			Agent createdAgent = agentService.createAgent(agent);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdAgent);
		} catch (AlreadyExistsException e) {
			Response errorResponse = new Response(HttpStatus.CONFLICT, e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
		} catch (Exception e) {
			Response errorResponse = new Response(HttpStatus.BAD_REQUEST, e.getMessage());
			return ResponseEntity.badRequest().body(errorResponse);
		}
	}

	@GetMapping("/viewagent/{agentId}")
	public ResponseEntity<?> getAgentByAgentId(@PathVariable String agentId) {
		try {
			List<AgentDTO> agents = agentService.getAgentByAgentId(agentId);
			if (agents.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(agents);
		} catch (NoSuchElementFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while retrieving the company");
		}
	}

	@GetMapping("/viewallagent")
	public ResponseEntity<?> getAllAgents() {
		try {
			List<AgentDTO> agents = agentService.getAllAgents();
			if (agents.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(agents);
		} catch (NoSuchElementFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while retrieving this.");
		}
	}

	@GetMapping("/allagent/{companyId}")
	public ResponseEntity<?> getAllAgentsByCompany(@PathVariable String companyId) {
		try {
			InsuranceCompany insuranceCompany = new InsuranceCompany();
			insuranceCompany.setCompanyId(companyId);
			List<Agent> agents = agentService.getAgentsByInsuranceCompany(insuranceCompany);
			if (agents.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(agents);
		} catch (NoSuchElementFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while retrieving this.");
		}
	}
}
