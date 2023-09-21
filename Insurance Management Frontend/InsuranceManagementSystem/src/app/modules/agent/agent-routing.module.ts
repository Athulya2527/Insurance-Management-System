import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AgentDashboardComponent } from './components/agent-dashboard/agent-dashboard.component';
import { CustomerCreationComponent } from './components/customer-creation/customer-creation.component';
import { SearchCustomerComponent } from './components/search-customer/search-customer.component';
import { ProfileComponent } from './components/profile/profile.component';
import { CreatepolicyComponent } from './components/createpolicy/createpolicy.component';


const routes: Routes = [
  {
    path: '', component: AgentDashboardComponent,
    children: [
      { path: 'profile', component: ProfileComponent },
      { path: 'createcustomer', component: CustomerCreationComponent },
      { path: 'searchcustomer', component: SearchCustomerComponent },
      { path: 'createpolicy', component: CreatepolicyComponent},
      { path: '', redirectTo: 'agent/profile', pathMatch: 'full' },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AgentRoutingModule { }
