import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AgentService } from 'src/app/services/agent.service';
import { NgxPaginationModule } from 'ngx-pagination';
import { Customer } from 'src/app/models/customer';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { CustomerService } from 'src/app/services/customer.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-search-customer',
  templateUrl: './search-customer.component.html',
  styleUrls: ['./search-customer.component.css']
})
export class SearchCustomerComponent {

  firstName!: string;
  lastName!: string;
  customers: Customer[] = [];
  pageSize: number = 10;
  currentPage: number = 1;
  isEditing: boolean = false;
  editingCustomer: any;
  customerData!: Customer;
  page: number = 1;
  itemsPerPage: number = 5;

  editCustomerForm = new FormGroup({
    customerId: new FormControl(''),
    prefix: new FormControl(''),
    firstName: new FormControl(''),
    middleName: new FormControl(''),
    lastName: new FormControl(''),
    email: new FormControl(''),
    contactNumber: new FormControl(''),
    alternateNumber: new FormControl(''),
    dateOfBirth: new FormControl(''),
    maritalStatus: new FormControl(''),
    gender: new FormControl(''),
  });

  constructor(
    private agentService: AgentService,
    private customerService: CustomerService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.route.queryParams.subscribe((params) => {
      const firstName = params['firstName'];
      const lastName = params['lastName'];

      // Perform the search with the retrieved parameters
      this.agentService.getCustomers(firstName, lastName).subscribe(
        (customers) => {
          this.customers = customers;
        },
        (error) => {
          console.error('Error fetching customers:', error);

          // Display a SweetAlert2 error message
          Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Failed to fetch customers. Please try again later.',
          });
        }
      );
    });
  }
  onEdit(customerId: string) {
    const modalEdit = document.getElementById('editModal');
    if (modalEdit != null) {
      modalEdit.classList.add('show');
      modalEdit.style.display = 'block';
      // Use the customerId to fetch the customer data
      this.customerService.getCustomerByCustomerId(customerId).subscribe(
        (customer) => {
          if (customer) {
            this.customerData = customer[0];
            // Populate the form fields with customer details
            this.editCustomerForm.patchValue({
              customerId: this.customerData.customerId,
              prefix: this.customerData.prefix,
              firstName: this.customerData.firstName,
              middleName: this.customerData.middleName,
              lastName: this.customerData.lastName,
              email: this.customerData.email,
              contactNumber: this.customerData.contactNumber,
              alternateNumber: this.customerData.alternateNumber,
              dateOfBirth: this.customerData.dateOfBirth,
              maritalStatus: this.customerData.maritalStatus,
              gender: this.customerData.gender,
              // Add other form fields as needed
            });
          } else {
            console.log('Customer not found.');
          }
        },
        (error: HttpErrorResponse) => {
          console.error('Error fetching customer details:', error);
          Swal.fire('Error', error.error.message, 'error');
        }
      );
    }
  }

  closeEdit() {
    const modalEdit = document.getElementById('editModal');
    if (modalEdit != null) {
      modalEdit.classList.add('show');
      modalEdit.style.display = 'none';
    }
  }
  onUpdate() {
    if (this.editCustomerForm.valid) {
      const updatedData: Customer = this.editCustomerForm.value as Customer;
      updatedData.customerId = this.customerData.customerId;
  
      // Show the loading spinner before making the HTTP request
      Swal.fire({
        title: 'Updating Customer',
        text: 'Please wait...',
        icon: 'info',
        allowOutsideClick: false,
        showCancelButton: false,
        showConfirmButton: false
      });
      this.agentService.updateCustomer(updatedData.customerId, updatedData).subscribe(
        (response: any) => {
          console.log('Customer updated successfully:', response);
          Swal.close();
        },
        (error: HttpErrorResponse) => {
          console.error('Error updating customer:', error);
          Swal.close();
        }
      );
    }
  }
  
  onExit() {
    this.router.navigate(['/agent/profile']);
  }

  onDelete(customerId: string) {
    Swal.fire({
      title: 'Confirm Deletion',
      text: 'Do you want to delete this customer?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it',
      cancelButtonText: 'Cancel'
    }).then((result) => {
      if (result.isConfirmed) {
        console.log(customerId);
        this.agentService.deleteCustomer(customerId).subscribe(
          () => {
            Swal.fire('Success', 'Customer deleted successfully', 'success');
          },
          (error) => {
            console.error('Error deleting customer:', error);
            Swal.fire('Error', error.error.message, 'error');
          }
        );
      }
    });
  }
  onClose() {
    const modalDelete = document.getElementById('modal');
    if (modalDelete != null) {
      modalDelete.classList.add('show');
      modalDelete.style.display = 'none';
    }
  }
}
  
  






