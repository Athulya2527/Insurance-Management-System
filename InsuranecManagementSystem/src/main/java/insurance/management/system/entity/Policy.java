package insurance.management.system.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "policy")
public class Policy {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policyseq")
	@SequenceGenerator(name = "policyseq", sequenceName = "mypolicyseq", initialValue = 2000, allocationSize = 1)
	@Column(name = "policyid")
	private Integer policyId;

	@Column(name = "policynumber", unique = true)
	private String policyNumber;

//	@NotNull(message = "Policy type is required")
	@Column(name = "policytype")
	private String policyType;

//	@NotNull(message = "Premium amount is required")
	@Column(name = "premiumamount")
	private Integer premiumAmount;

//	@NotNull(message = "Coverage period is required")
	@Column(name = "coverageperiod")
	private Integer CoveragePeriod;

//	@NotNull(message = "Coverage amount is required")
	@Column(name = "coverageamount")
	private Integer CoverageAmount;

	@ManyToOne
	@JoinColumn(name = "companyid")
	private InsuranceCompany insuranceCompany;

	@ManyToOne
    @JoinColumn(name = "agentid")
    private Agent agent;
	

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public Integer getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(Integer premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public Integer getCoveragePeriod() {
		return CoveragePeriod;
	}

	public void setCoveragePeriod(Integer coveragePeriod) {
		CoveragePeriod = coveragePeriod;
	}

	public Integer getCoverageAmount() {
		return CoverageAmount;
	}
	public void setCoverageAmount(Integer coverageAmount) {
		CoverageAmount = coverageAmount;
	}

	public InsuranceCompany getInsuranceCompany() {
		return insuranceCompany;
	}

	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public void setInsuranceCompany(InsuranceCompany insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

}