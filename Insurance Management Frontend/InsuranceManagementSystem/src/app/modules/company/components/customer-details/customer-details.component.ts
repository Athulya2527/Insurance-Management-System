import { Component } from '@angular/core';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css']
})
export class CustomerDetailsComponent {


  private showSweetAlert(title: string, message: string, icon: 'success' | 'error' | 'warning' | 'info' = 'error') {
    Swal.fire({
      title,
      text: message,
      icon,
    });
  }
}
