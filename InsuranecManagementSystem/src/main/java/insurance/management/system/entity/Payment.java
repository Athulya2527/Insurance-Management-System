package insurance.management.system.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policyseq")
	@SequenceGenerator(name = "paymentseq", sequenceName = "mypaymenttseq", initialValue = 202310, allocationSize = 1)
	@NotBlank
	@Column(name = "id")
	private Integer id;

	@NotBlank
	@Column(name = "paymentnumber", unique = true)
	private String paymentNumber;
	
	@Column(name = "paymentdate")
	private LocalDate paymentDate;
	
	@NotBlank(message = "Please enter payment amount")
	@Column(name = "paymentamount")
	private Integer amount;
	
	@NotBlank(message = "Payment method is required")
	@Column(name = "paymentmethod")
	private String paymentMethod;
	
	@ManyToOne
    @JoinColumn(name = "policyid")
    private Policy policy;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPaymentNumber() {
		return paymentNumber;
	}

	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	
}
