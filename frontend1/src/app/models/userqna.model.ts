import { Result } from "./result.model";

export interface UserQna {
    id?: number;
    quesId: number;
    answer: string;
    result?: Result;
    
  }