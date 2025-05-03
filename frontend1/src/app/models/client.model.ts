export interface Client {
    id?: number;
    email: string;
    enabled: boolean;
    firstName: string;
    lastName: string;
    password: string;
    phone: string;
    userName: string;
    profile: string;
    roleName: string;
  }
  
  export interface AuthenticationRequest {
    userName: string;
    password: string;
  }
  
  export interface AuthenticationResponse {
    jwtToken: string;
  }
  