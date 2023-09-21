import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, ValidatorFn, AbstractControl, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { Customer } from 'src/app/models/customer';
import { CustomerPolicy } from 'src/app/models/customerPolicy';
import { Policy } from 'src/app/models/policy';
import { AgentService } from 'src/app/services/agent.service';
import { AuthService } from 'src/app/services/auth.service';
import { CustomerService } from 'src/app/services/customer.service';
import { PolicyService } from 'src/app/services/policy.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-customer-creation',
  templateUrl: './customer-creation.component.html',
  styleUrls: ['./customer-creation.component.css']
})
export class CustomerCreationComponent implements OnInit {

  showPolicyDetails: boolean = false;
  policy: string[] = [];
  selectedPolicyDetails: Policy | null = null;
  customerForm!: FormGroup;
  policyForm!: FormGroup;
  agentId = this.authService.getId();
  policyNumber: any
  savedCustomerId: string = '';
  savedPolicyId: number = 0;
  constructor(
    private agentService: AgentService,
    private authService: AuthService,
    private policyService: PolicyService,
    private customerService: CustomerService,
    private formBuilder: FormBuilder,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.customerForm = this.initCustomerForm();
    this.policyForm = this.initPolicyForm();
    this.fetchPolicyNumber();

  }

  validateDOB: ValidatorFn = (control: AbstractControl): { [key: string]: boolean } | null => {
    const currentDate = new Date();
    const inputDate = new Date(control.value);

    if (inputDate > currentDate) {
      return { futureDate: true };
    }

    const eighteenYearsAgo = new Date();
    eighteenYearsAgo.setFullYear(eighteenYearsAgo.getFullYear() - 18);
    if (inputDate > eighteenYearsAgo) {
      return { underage: true };
    }
    return null;
  }


  private initCustomerForm(): FormGroup {


    return this.formBuilder.group({
      role: new FormControl('Customer'),
      prefix: new FormControl('', [Validators.required]),
      firstName: new FormControl('', [Validators.required]),
      middleName: new FormControl(''),
      lastName: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
      dateOfBirth: new FormControl('', [Validators.required, this.validateDOB]),
      maritalStatus: new FormControl('', [Validators.required]),
      gender: new FormControl('', [Validators.required]),
      contactNumber: new FormControl('', [Validators.required, Validators.pattern(/^\d{10}$/), Validators.minLength(9), Validators.maxLength(10)]),
      alternateNumber: new FormControl('', [Validators.required, Validators.pattern(/^\d{10}$/)]),
      address: new FormControl('', [Validators.required]),
    });
  }

  onSubmit() {
    if (this.customerForm.valid) {
      const customerData: Customer = this.customerForm.value as Customer;
      const agentId: string | null = this.authService.getId();
      console.log(agentId);
      console.log(customerData);

      if (agentId !== null) {
        this.agentService.createCustomer(customerData, agentId).subscribe({

          next: (response: any) => {
            console.log('Customer created:', response);
            this.showSweetAlert('Success', 'Customer Profile Created', 'success');
            this.savedCustomerId = response.customerId;
            this.customerForm.disable();
            this.showPolicyDetails = true;
          },
          error: (error: HttpErrorResponse) => {
            console.error('Error creating customer:', error);
            this.showSweetAlert('Error', error.error.message, 'error');
          }

        })
      } else {
        console.error('Agent ID is null. Cannot create a customer.');
      }

    }
  }

  fetchPolicyNumber(): void {
    this.agentService.getPolicyNumber().subscribe(
      (policy: string[]) => {
        this.policy = policy;
      },
      (error: any) => {
        console.log('Error fetching user policynumber:', error);
      }
    );
  }

  onPolicyNumberChange(event: any) {
    const policyNumber = event.target.value;
    console.log(policyNumber);
    this.policyService.getPolicyByPolicyNumber(policyNumber).subscribe(
      (policyDetails: Policy[]) => {
        if (policyDetails.length > 0) {
          this.selectedPolicyDetails = policyDetails[0]; // Assuming the API returns a single policy
          console.log('Policy details:', this.selectedPolicyDetails);
          this.savedPolicyId = this.selectedPolicyDetails.policyId;
          // Populate the form fields with policy details:
          this.customerForm.patchValue({
            policyId: this.selectedPolicyDetails.policyId,
            policyType: this.selectedPolicyDetails.policyType,
            premiumAmount: this.selectedPolicyDetails.premiumAmount,
            coveragePeriod: this.selectedPolicyDetails.coveragePeriod,
            coverageAmount: this.selectedPolicyDetails.coverageAmount,
          });
        }
      },
      (error: HttpErrorResponse) => {
        console.error('Error fetching policy details:', error);
        this.showSweetAlert('Error', error.error.message, 'error');
      }
    );
  }

  private initPolicyForm(): FormGroup {
    return this.formBuilder.group({
      nomineeName: new FormControl('', Validators.required),
      nomineePhoneNumber: new FormControl('', [Validators.required, Validators.pattern('[0-9]{10}')]),
      nomineeDateOfBirth: new FormControl('', [Validators.required, this.validateDOB]),
      relationship: new FormControl('', Validators.required),
    });
  }

  onSubmitPolicy() {


    if (this.policyForm.valid) {
      const customerPolicyData: CustomerPolicy = this.policyForm.value as CustomerPolicy;
      customerPolicyData.customer = { customerId: this.savedCustomerId }
      customerPolicyData.policy = { policyId: this.savedPolicyId }
      this.customerService.addPolicyForCustomer(customerPolicyData).subscribe({
        next: (response: any) => {
          console.log('Policy added:', response);
          this.showSweetAlert('Success', 'Policy Added', 'success');

        },
        error: (error: HttpErrorResponse) => {
          console.error('Error creating policy:', error);
          this.showSweetAlert('Error', error.error.message, 'error');
        }
      })
    }

  }
  onCancel() {
    this.router.navigate(['/agent/profile']);
  }
  private showSweetAlert(title: string, message: string, icon: 'success' | 'error' | 'warning' | 'info' = 'error') {
    Swal.fire({
      title,
      text: message,
      icon,
    });
  }
}
