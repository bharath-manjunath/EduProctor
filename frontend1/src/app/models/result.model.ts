import { Examination } from './examination.model';
import { Client } from './client.model';
import { UserQna } from './userqna.model';

export interface Result {
    resultId?: number;
    numOfQuestions?: number;
    correctAnswers?: number;
    marksScored?: number;
    percentage?: number;
    questionsAttempted?: number;
    submitDateTime?: string; // Use string for LocalDateTime to map the ISO format
    examination: Examination;
    client: Client;
    userQnas: any[];
  
    // constructor() {
    //   this.userQnas = [];
    // }
  }