import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatepolicyComponent } from './createpolicy.component';

describe('CreatepolicyComponent', () => {
  let component: CreatepolicyComponent;
  let fixture: ComponentFixture<CreatepolicyComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreatepolicyComponent]
    });
    fixture = TestBed.createComponent(CreatepolicyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
