import { Component } from '@angular/core';
import { Company } from 'src/app/models/company';
import { AuthService } from 'src/app/services/auth.service';
import { CompanyService } from 'src/app/services/company.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  company: Company[] = [];
  showBox = false;

  constructor(
     private authService: AuthService,
     private companyService: CompanyService,
  ){}

  ngOnInit(){
    const companyId = this.authService.getId();
    if (companyId) {
      this.companyService.getCompanyByCompanyId(companyId).subscribe(
        (company: Company[]) => {
          if (company && company.length > 0) {
            this.company = company;
          }
        },
      );
    }
  }
  
  toggleBox(event: Event) {
    event.preventDefault();
    this.showBox = !this.showBox;
  }
  closeBox() {
    this.showBox = false;
  }
  private showSweetAlert(title: string, message: string, icon: 'success' | 'error' | 'warning' | 'info' = 'error') {
    Swal.fire({
      title,
      text: message,
      icon,
    });
  }
}
