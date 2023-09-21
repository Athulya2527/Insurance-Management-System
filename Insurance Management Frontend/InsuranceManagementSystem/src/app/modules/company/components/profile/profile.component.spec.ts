import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileComponent } from './profile.component';
import { HttpClientModule } from '@angular/common/http';
import { AuthService } from 'src/app/services/auth.service';
import { CompanyService } from 'src/app/services/company.service';
import { of } from 'rxjs';

describe('ProfileComponent', () => {
  let component: ProfileComponent;
  let fixture: ComponentFixture<ProfileComponent>;
  let authService: jasmine.SpyObj<AuthService>;
  let companyService: jasmine.SpyObj<CompanyService>;

  beforeEach(() => {
    authService = jasmine.createSpyObj('AuthService', ['getId']);
    companyService = jasmine.createSpyObj('CompanyService', ['getCompanyByCompanyId']);

    TestBed.configureTestingModule({
      declarations: [ProfileComponent],
      imports: [HttpClientModule],
      providers: [
        { provide: AuthService, useValue: authService },
        { provide: CompanyService, useValue: companyService },
      ],
    });
    fixture = TestBed.createComponent(ProfileComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should set the company property if companyId exists', () => {
    const companyId = 'COMPANY0001'; 
    const mockCompany = [{
      "companyId": "COMPANY0001",
      "role": "Company",
      "name": "LIC Insurance Company",
      "email": "info@licinsurance.com",
      "contactNumber": "9844577890",
      "address": "Ernakulam, City",

    }];

    // Configure the spies to return values
    authService.getId.and.returnValue(companyId);
     companyService.getCompanyByCompanyId.and.returnValue(of(mockCompany));

    // Call the ngOnInit method
    component.ngOnInit();

    // Expectations
    expect(authService.getId).toHaveBeenCalled();
    expect(companyService.getCompanyByCompanyId).toHaveBeenCalledWith(companyId);
     expect(component.company).toEqual(mockCompany);
  });
});
