import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerCreationComponent } from './customer-creation.component';

describe('CustomerCreationComponent', () => {
  let component: CustomerCreationComponent;
  let fixture: ComponentFixture<CustomerCreationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CustomerCreationComponent]
    });
    fixture = TestBed.createComponent(CustomerCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
