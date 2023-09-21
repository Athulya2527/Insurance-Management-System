import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from 'src/app/models/customer';
import { AuthService } from 'src/app/services/auth.service';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  customers: Customer[] = [];
  showNomineeDetails: boolean = false;
  showPolicyDetails: boolean = false;

  constructor(
    private customerService: CustomerService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    const customerId = this.authService.getId();

    if (customerId) {
      this.customerService.getCustomerByCustomerId(customerId).subscribe(
        (customers: Customer[]) => {
          if (customers && customers.length > 0) {
            // Assuming the response contains a single customer object
            this.customers = customers;

          }
        },
      );
    }
  }
  getNominee() {
    // If the flag is currently true (details are shown), set it to false (hide details)
    // If the flag is currently false (details are hidden), set it to true (show details)
    this.showNomineeDetails = !this.showNomineeDetails;
  }
  getPolicy() {
    this.showPolicyDetails = !this.showPolicyDetails;
  }



}
