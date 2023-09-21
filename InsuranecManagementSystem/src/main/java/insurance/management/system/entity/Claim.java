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
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "claim")
public class Claim {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotBlank
	@Column(name = "id")
    private Integer id;

	@NotBlank
	@Column(name="claimnumber", unique = true)
    private String claimNumber;
	
	@NotBlank(message= "Please select claim date")
	@Column(name="claimndate")
    private LocalDate claimDate;
	
	@Column(name="claimamount")
    private Integer claimAmount;
	
	@Column(name="claimstatus")
    private String claimStatus;
	
	@Column(name="claimdocument")
    private String claimDocument;

	@ManyToOne
    @JoinColumn(name = "policyid")
    private Policy policy;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public LocalDate getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(LocalDate claimDate) {
		this.claimDate = claimDate;
	}

	public Integer getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(Integer claimAmount) {
		this.claimAmount = claimAmount;
	}

	public String getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	public String getClaimDocument() {
		return claimDocument;
	}

	public void setClaimDocument(String claimDocument) {
		this.claimDocument = claimDocument;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

}
