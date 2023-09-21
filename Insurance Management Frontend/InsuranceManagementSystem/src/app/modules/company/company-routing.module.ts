import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompanyDashboardComponent } from './components/company-dashboard/company-dashboard.component';
import { ProfileComponent } from '../company/components/profile/profile.component';
import { AgentDetailsComponent } from './components/agent-details/agent-details.component';
import { PolicyDetailsComponent } from './components/policy-details/policy-details.component';
import { CustomerDetailsComponent } from './components/customer-details/customer-details.component';

const routes: Routes = [
  {
    path: '', component: CompanyDashboardComponent,
    children: [
      { path: 'profile', component: ProfileComponent },
      { path: '', redirectTo: 'company/profile', pathMatch: 'full' },
      { path: 'agentdetails', component: AgentDetailsComponent },
      { path: 'policydetails', component: PolicyDetailsComponent },
      { path: 'customerdetails', component: CustomerDetailsComponent },
      
    ],
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompanyRoutingModule { }
