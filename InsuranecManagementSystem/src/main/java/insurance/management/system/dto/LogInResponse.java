package insurance.management.system.dto;

public class LogInResponse {

	private String id;
	private String role;
	private String email;
	private String name;
	
	public LogInResponse(String agentId, String fullName, String email, String role) {
		this.id = agentId;
		this.name = fullName;
		this.email = email;
		this.role = role;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
