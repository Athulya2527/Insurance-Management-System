import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Agent } from 'src/app/models/agent';
import { AgentService } from 'src/app/services/agent.service';
import { AuthService } from 'src/app/services/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  agents: Agent[] = [];
  firstName: string = '';
  lastName: string = '';

  constructor(
    private agentService: AgentService,
    private authService: AuthService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    const agentId = this.authService.getId();

    if (agentId) {
      // Fetch the agent details using the agentId
      this.agentService.getAgentByAgentId(agentId).subscribe(
        (agents: Agent[]) => {
          if (agents && agents.length > 0) {
            // Assuming the response contains a single agent object
            this.agents = agents;
          }
        },
        (error) => {
          console.error('Error fetching agent details:', error);
          this.showAlert('Error', 'Failed to fetch agent details. Please try again later.', 'error');
        }
      );
    }
  }

  addCustomer() {
    this.router.navigate(['/agent/createcustomer']);
  }

  addPolicy() {
    this.router.navigate(['/agent/createpolicy'])
  }

  search() {
    this.agentService.getCustomers(this.firstName, this.lastName).subscribe(
      (customers) => {
        this.agentService.viewSearchResults(customers);

        // Pass the query parameters using an object
        const queryParams = {
          firstName: this.firstName,
          lastName: this.lastName
        };

        // Navigate to the 'SearchCustomerComponent' with query parameters
        this.router.navigate(['/agent/searchcustomer'], { queryParams });
      },
      (error) => {
        console.error('Error fetching customers:', error);
        this.showAlert('Error', 'Failed to fetch customers. Please try again later.', 'error');
      }
    );
  }

  private showAlert(title: string, message: string, icon: 'success' | 'error' | 'warning' | 'info' = 'error') {
    Swal.fire({
      title,
      text: message,
      icon,
    });
  }
}
