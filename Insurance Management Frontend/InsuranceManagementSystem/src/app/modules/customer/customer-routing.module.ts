import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerDashboardComponent } from './components/customer-dashboard/customer-dashboard.component';
import { ProfileComponent } from '../customer/components/profile/profile.component';;

const routes: Routes = [
  {
    path: '', component: CustomerDashboardComponent,
    children: [
      { path: 'profile', component: ProfileComponent },
      { path: '', redirectTo: 'customer/profile', pathMatch: 'full' },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
