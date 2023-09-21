import { Injectable } from '@angular/core';
import { Policy } from '../models/policy';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PolicyService {

  constructor(private http: HttpClient) { }

  createPolicy(policy: Policy, agentId: string, companyId : string){
    console.log(policy);
    const url = `http://localhost:8080/policy/createpolicy/${agentId}`;
    return this.http.post<any>(url, policy);
  }
  
  getPolicyByPolicyNumber(policyNumber: string): Observable<Policy[]>{
    const url = `http://localhost:8080/policy/viewpolicy/${policyNumber}`;
    return this.http.get<Policy[]>(url);
    
  }

}
