package insurance.management.system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "agent")
public class Agent {
	
	@Id
	@Column(name = "agentid", unique = true)
	private String agentId;
	
	@Column(name = "password")
	@NotBlank(message = "Please enter proper  user password")
	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;


	@Column(name = "role")
	@NotBlank(message = "Please enter proper user type")
	private String role;

	@NotBlank(message = "Name is required")
	@Column(name = "fullname")
	private String fullName;

	@Column(name = "email", unique = true)
	@Email(message = "Please enter a valid email Id", regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
	private String email;

	@Column(name = "contactnumber")
	@NotBlank(message = "Contact number is required")
	@Pattern(regexp = "\\d{10}", message = "Contact number must be a 10-digit number")
	private String contactNumber;

	@NotBlank(message = "Address is required")
	@Column(name = "address")
	private String address;
	
	@Column(name = "commissionrate")
	private Integer commissionRate;
	
	@ManyToOne
	@JoinColumn(name = "companyid")
	private InsuranceCompany insuranceCompany;
	
	public InsuranceCompany getInsuranceCompany() {
		return insuranceCompany;
	}


	public void setInsuranceCompany(InsuranceCompany insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}


	@Override
	public String toString() {
		return "Agent [ agentId=" + agentId + ", password=" + password + ", role=" + role + ", fullName="
				+ fullName + ", email=" + email + ", contactNumber=" + contactNumber + ", address=" + address
				+ ", commissionRate=" + commissionRate  + "]";
	}


	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(Integer commissionRate) {
		this.commissionRate = commissionRate;
	}

}
