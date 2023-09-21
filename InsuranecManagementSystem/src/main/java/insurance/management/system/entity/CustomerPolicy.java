package insurance.management.system.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customerpolicy")
public class CustomerPolicy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Integer id;
	
	@Column(name = "selected_policy_number")
	private String selectedPolicyNumber;


	@Column(name = "startdate")
	private LocalDate startDate;
	
	@Column(name = "enddate")
	private LocalDate endDate;
	
	@Column(name = "nominee_name")
	private String nomineeName;

	@Column(name = "nominee_phonenumber")
	private String nomineePhoneNumber;

	@Column(name = "nominee_dateofbirth")
	private LocalDate nomineeDateOfBirth;

	@Column(name = "relationship")
	private String relationship;

	@ManyToOne
	@JoinColumn(name="policy_id")
	private Policy policy;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;


	public CustomerPolicy() {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSelectedPolicyNumber() {
		return selectedPolicyNumber;
	}

	public void setSelectedPolicyNumber(String selectedPolicyNumber) {
		this.selectedPolicyNumber = selectedPolicyNumber;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
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

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
}
