package insurance.management.system.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "insurancecompany")
public class InsuranceCompany  {
	
	@Id
	@Column(name = "companyid", unique = true)
	@NotNull(message = "Please enter company id")
	private String companyId;

	@Column(name = "password")
	@NotBlank(message = "Please enter proper user password")
	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;

	@Column(name = "role")
	@NotBlank(message = "Please enter proper user type")
	private String role;

	@NotNull(message = "Name is required")
	@Column(name = "name")
	private String name;

	@Column(name = "email", unique = true)
	@Email(message = "Please enter a valid email Id", regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
	private String email;

	@Column(name = "contactnumber")
	@NotBlank(message = "Contact number is required")
	@Pattern(regexp = "\\d{10}", message = "Contact number must be a 10-digit number")
	private String contactNumber;

	@NotNull(message = "Address is required")
	@Column(name = "address")
	private String address;

	
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "InsuranceCompany [companyId=" + companyId + ", password=" + password + ", role=" + role
				+ ", name=" + name + ", email=" + email + ", contactNumber=" + contactNumber + ", address=" + address
				+ "]";
	}

//	public List<Policy> getPolicies() {
//		return policies;
//	}
//
//	public void setPolicies(List<Policy> policies) {
//		this.policies = policies;
//	}

	
	
	
	
}
