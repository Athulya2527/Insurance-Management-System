import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Company } from '../models/company';
import { Agent } from '../models/agent';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private http: HttpClient) { }

  getCompanyByCompanyId(companyId: string): Observable<Company[]>{
    const url = `http://localhost:8080/insurancecompany/viewcompany/${companyId}`;
    return this.http.get<Company[]>(url);
  }

  getAllCompanyId(): Observable<string[]> {
    return this.http.get<string[]>('http://localhost:8080/insurancecompany/allcompanyid');
  }
  getAllAgent(companyId: string): Observable<Agent[]>{
    const url = `http://localhost:8080/agent/allagent/${companyId}`;
    return this.http.get<Agent[]>(url);
  }
}
