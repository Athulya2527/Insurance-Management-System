import { Customer } from "./customer";
import { Policy } from "./policy";

export interface CustomerPolicy {
    id: number;
    selectedPolicyNumber: string;
    startDate: string; 
    endDate: string;   
    nomineeName: string;
    nomineePhoneNumber: string;
    nomineeDateOfBirth: string; 
    relationship: string;
    policy: Policy | {policyId: number};       
    customer: Customer | { customerId : string};   
  }