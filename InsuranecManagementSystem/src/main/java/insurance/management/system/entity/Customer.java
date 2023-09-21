package insurance.management.system.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@Column(name = "customerid", unique = true)
	private String customerId;

	@Column(name = "password")
//	@NotBlank(message = "Please enter proper  user password")
//	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;

	@Column(name = "role")
//	@NotBlank(message = "Please enter proper user type")
	private String role;

	@Column(name = "prefix")
	private String prefix;

//	@NotBlank(message = "First name is required")
	@Column(name = "firstname")
	private String firstName;

	@Column(name = "middlename")
	private String middleName;

//	@NotBlank(message = "Last name is required")
	@Column(name = "lastname")
	private String lastName;

	@Column(name = "email", unique = true)
//	@Email(message = "Please enter a valid email Id", regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
	private String email;

	@Column(name = "contactnumber")
//	@NotBlank(message = "Contact number is required")
//	@Pattern(regexp = "\\d{10}", message = "Contact number must be a 10-digit number")
	private String contactNumber;

	@Column(name = "alternatenumber")
	private String alternateNumber;
//
//	@Column(name = "dateofbirth")
//	private LocalDate dateOfBirth;
	

	@Column(name = "dateofbirth")
	private LocalDate dateOfBirth;

	@Column(name = "marital_status")
	private String maritalStatus;

	@Column(name = "gender")
	private String gender;

	@Column(name = "address")
	private String address;

	@ManyToOne
	@JoinColumn(name = "agent_id")
	private Agent agent;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<CustomerPolicy> customerPolicies;
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getAlternateNumber() {
		return alternateNumber;
	}

	public void setAlternateNumber(String alternateNumber) {
		this.alternateNumber = alternateNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public List<CustomerPolicy> getCustomerPolicies() {
		return customerPolicies;
	}

	public void setCustomerPolicies(List<CustomerPolicy> customerPolicies) {
		this.customerPolicies = customerPolicies;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", password=" + password + ", role=" + role + ", prefix=" + prefix
				+ ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", email="
				+ email + ", contactNumber=" + contactNumber + ", alternateNumber=" + alternateNumber + ", dateOfBirth="
				+ dateOfBirth + ", maritalStatus=" + maritalStatus + ", gender=" + gender + ", address=" + address
				+ ", agent=" + agent + "]";
	}

	
	

}
