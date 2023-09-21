
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AgentRoutingModule } from './agent-routing.module';
import { AgentDashboardComponent } from './components/agent-dashboard/agent-dashboard.component';
import { CustomerCreationComponent } from './components/customer-creation/customer-creation.component';
import { SearchCustomerComponent } from './components/search-customer/search-customer.component';
import { ProfileComponent } from './components/profile/profile.component';
import { SharedModule } from '../shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { CreatepolicyComponent } from './components/createpolicy/createpolicy.component';



@NgModule({
  declarations: [
    AgentDashboardComponent,
    CustomerCreationComponent,
    SearchCustomerComponent,
    ProfileComponent,
    CreatepolicyComponent,
    

  ],
  imports: [
    CommonModule,
    AgentRoutingModule,
    SharedModule,
    ReactiveFormsModule,
    FormsModule,
    NgxPaginationModule,
    NgbModalModule
  ]
})
export class AgentModule { }
