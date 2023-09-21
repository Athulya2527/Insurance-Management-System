package insurance.management.system.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "deletedcustomer")
public class DeletedCustomer {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	private Integer id;
	
	@Column(name = "customerid")
	private String customerId;
	
	@Column(name = "deletedtime")
    private LocalDateTime deletedTime;
	
	@Column(name = "role")
	private String role;

	@Column(name = "prefix")
	private String prefix;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "middlename")
	private String middleName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "contactnumber")
	private String contactNumber;

	@Column(name = "alternatenumber")
	private String alternateNumber;

	@Column(name = "dateofbirth")
	private LocalDate dateOfBirth;

	@Column(name = "marital_status")
	private String maritalStatus;

	@Column(name = "gender")
	private String gender;

	@Column(name = "address")
	private String address;

	@Column(name = "nominee_name")
	private String nomineeName;

	@Column(name = "nominee_phonenumber")
	private String nomineePhoneNumber;

	@Column(name = "nominee_dateofbirth")
	private LocalDate nomineeDateOfBirth;

	@Column(name = "relationship")
	private String relationship;
	

	

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getRole() {
		return role;
	}


	public LocalDateTime getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(LocalDateTime deletedTime) {
		this.deletedTime = deletedTime;
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

	public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	public String getNomineePhoneNumber() {
		return nomineePhoneNumber;
	}

	public void setNomineePhoneNumber(String nomineePhoneNumber) {
		this.nomineePhoneNumber = nomineePhoneNumber;
	}

	public LocalDate getNomineeDateOfBirth() {
		return nomineeDateOfBirth;
	}

	public void setNomineeDateOfBirth(LocalDate nomineeDateOfBirth) {
		this.nomineeDateOfBirth = nomineeDateOfBirth;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

}
