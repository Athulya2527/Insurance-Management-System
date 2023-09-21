import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Agent } from 'src/app/models/agent';
import { AuthService } from 'src/app/services/auth.service';
import { CompanyService } from 'src/app/services/company.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-agent-details',
  templateUrl: './agent-details.component.html',
  styleUrls: ['./agent-details.component.css']
})
export class AgentDetailsComponent {

  agents : Agent [] = [];
  page: number = 1;
  itemsPerPage: number = 2;
  constructor(
    private authService: AuthService,
    private companyService: CompanyService,
    private router: Router,
 ){}
  ngOnInit(){
   const companyId = this.authService.getId();
   if (companyId) {
     this.companyService.getAllAgent(companyId).subscribe(
       (agent: Agent[]) => {
         if (agent && agent.length > 0) {
           this.agents = agent;
         }
       },
     );
   }
  }
  onExit() {
    this.router.navigate(['/company/profile']);
  }
  private showSweetAlert(title: string, message: string, icon: 'success' | 'error' | 'warning' | 'info' = 'error') {
    Swal.fire({
      title,
      text: message,
      icon,
    });
  }
}
