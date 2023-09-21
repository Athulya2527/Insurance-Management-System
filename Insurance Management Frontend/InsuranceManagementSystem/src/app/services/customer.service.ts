import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../models/customer';
import { Observable } from 'rxjs';
import { CustomerPolicy } from '../models/customerPolicy';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseUrl = 'http://localhost:8080/customer';

  constructor(private http: HttpClient) { }

  getCustomerByCustomerId(customerId: string): Observable<Customer[]> {
    const url = `${this.baseUrl}/getcustomer/${customerId}`;
    return this.http.get<Customer[]>(url);
  }
  addPolicyForCustomer(customerPolicy: CustomerPolicy): Observable<any> {
    const url = `${this.baseUrl}/addpolicy`;
    return this.http.post(url, customerPolicy);
  }
  
}
