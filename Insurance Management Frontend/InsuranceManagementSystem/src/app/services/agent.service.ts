import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Agent } from '../models/agent';
import { Customer } from '../models/customer';
import { Policy } from '../models/policy';

@Injectable({
  providedIn: 'root'
})
export class AgentService {
  private searchResult = new BehaviorSubject<any[]>([]);
  searchResults = this.searchResult.asObservable();

  constructor(private http: HttpClient) { }

  getAgentByAgentId(agentId: string): Observable<Agent[]> {
    const url = `http://localhost:8080/agent/viewagent/${agentId}`;
    return this.http.get<Agent[]>(url);
  }

  createCustomer(customer: Customer, agentId: string) {
    const url = `http://localhost:8080/customer/createcustomer/${agentId}`;
    return this.http.post<Customer>(url, customer);
  }
  
  getPolicyNumber(): Observable<string[]> {
    return this.http.get<string[]>('http://localhost:8080/policy/allpolcynumber');
  }

  getCustomers(firstName: string, lastName: string) {
    const params: any = {};
    if (firstName) {
      params.firstName = firstName;
    }
    if (lastName) {
      params.lastName = lastName;
    }
    return this.http.get<any[]>('http://localhost:8080/customer/viewcustomer', { params });
  }

  viewSearchResults(customers: any[]) {
    this.searchResult.next(customers);
  }

  deleteCustomer(customerId: string) {
    const url = `http://localhost:8080/customer/deletecustomer/${customerId}`;
    return this.http.delete<any>(url);
  }
  
  updateCustomer(customerId: string, customer: Customer){
    const url = `http://localhost:8080/customer/updatecustomer/${customerId}`;
    return this.http.put<any>(url, customer);
  }
  
}
