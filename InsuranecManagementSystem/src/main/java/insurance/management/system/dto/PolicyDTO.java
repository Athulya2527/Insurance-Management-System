package insurance.management.system.dto;



public class PolicyDTO {

	private Integer policyId;
	private String policyNumber;
	private String policyType;
	private Integer premiumAmount;
	private Integer CoveragePeriod;
	private Integer CoverageAmount;
	private CompanyDTO company;
	private AgentDTO agent;
	
	public Integer getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}
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
	
	public CompanyDTO getCompany() {
		return company;
	}
	public void setCompany(CompanyDTO company) {
		this.company = company;
	}
	public AgentDTO getAgent() {
		return agent;
	}
	public void setAgent(AgentDTO agent) {
		this.agent = agent;
	}
	
	
	
}
