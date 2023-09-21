import { Agent } from "./agent";

  export interface Policy {
    policyId: number;
    policyNumber: string;
    policyType: string;
    premiumAmount: number;
    coveragePeriod: string;
    coverageAmount: number;
    insuranceCompany: {
      companyId: string;
    };
    
  }
  

  
