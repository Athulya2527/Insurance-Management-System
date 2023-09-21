import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { AgentModule } from './modules/agent/agent.module';
import { authGuard } from './guards/auth.guard';
import { ProfileComponent } from './modules/agent/components/profile/profile.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  {
    path: 'agent',
    canActivate: [authGuard],
    loadChildren: () => import('./modules/agent/agent.module').then((m) => m.AgentModule),
  },
  {
    path: 'customer',
    canActivate: [authGuard],
    loadChildren: () => import('./modules/customer/customer.module').then((m) => m.CustomerModule),
  },
  {
    path: 'company',
    canActivate: [authGuard],
    loadChildren: () => import('./modules/company/company.module').then((m) => m.CompanyModule),
  },

  { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
