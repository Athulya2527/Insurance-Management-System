import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Policy } from 'src/app/models/policy';
import { AgentService } from 'src/app/services/agent.service';
import { AuthService } from 'src/app/services/auth.service';
import { CompanyService } from 'src/app/services/company.service';
import { PolicyService } from 'src/app/services/policy.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-createpolicy',
  templateUrl: './createpolicy.component.html',
  styleUrls: ['./createpolicy.component.css']
})
export class CreatepolicyComponent implements OnInit {
  policyForm!: FormGroup;
  agentId = this.authService.getId();
  companyIds: string[] = [];
  responseData: any;

  constructor(
    private agentService: AgentService,
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private companyService: CompanyService,
    private policyService: PolicyService,
    private router: Router
  ) { }

  ngOnInit() {
    this.policyForm = this.initPolicyForm();
    this.fetchCompanyIds();
  }

  private initPolicyForm(): FormGroup {
    return this.formBuilder.group({
      policyType: ['', Validators.required],
      premiumAmount: [null, Validators.required],
      coverageYear: ['', Validators.required],
      coverageAmount: [null, Validators.required],
      companyId: [null, Validators.required]
    });
  }

  fetchCompanyIds(): void {
    this.companyService.getAllCompanyId().subscribe(
      (companyIds: string[]) => {
        this.companyIds = companyIds;
      },
      (error: any) => {
        console.log('Error fetching company IDs:', error);
        this.showSweetAlert('Error', error.error.message, 'error');
      }
    );
  }

  onSubmit() {
    this.policyForm.markAllAsTouched();
    if (this.policyForm.valid) {
      const companyId: string | null = this.policyForm.get('companyId')?.value;
      const agentId: string | null = this.authService.getId();

      if (companyId !== null && agentId !== null) {
        const policyData: Policy = {
          policyType: this.policyForm.get('policyType')?.value,
          premiumAmount: this.policyForm.get('premiumAmount')?.value,
          coveragePeriod: this.policyForm.get('coverageYear')?.value,
          coverageAmount: this.policyForm.get('coverageAmount')?.value,
          insuranceCompany: {
            companyId: companyId,
          },
          policyId: 0,
          policyNumber: '',
        };

        this.policyService.createPolicy(policyData, agentId, companyId).subscribe(
          (response) => {
            console.log('Policy created:', response);
            this.responseData = response;
            alert("Policy creation Successfully. The policy Number is " + response.policyNumber);

          },
          (error) => {
            console.error('Error creating policy:', error);
            this.showSweetAlert('Error', error.error.message, 'error');
          }
        );
      } else {
        console.error('companyId or agentId is null');
      }
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
