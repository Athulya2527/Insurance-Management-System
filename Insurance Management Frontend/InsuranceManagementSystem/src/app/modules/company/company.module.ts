import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CompanyRoutingModule } from './company-routing.module';
import { SharedModule } from '../shared.module';
import { CompanyDashboardComponent } from './components/company-dashboard/company-dashboard.component';
import { ProfileComponent } from './components/profile/profile.component';
import { AgentDetailsComponent } from './components/agent-details/agent-details.component';
import { PolicyDetailsComponent } from './components/policy-details/policy-details.component';
import { CustomerDetailsComponent } from './components/customer-details/customer-details.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    CompanyDashboardComponent,
    ProfileComponent,
    AgentDetailsComponent,
    PolicyDetailsComponent,
    CustomerDetailsComponent,
    
  ],
  imports: [
    CommonModule,
    CompanyRoutingModule,
    SharedModule,
    NgxPaginationModule
  ]
})
export class CompanyModule { }
