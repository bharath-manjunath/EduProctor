import { Category } from './category.model'; // Assuming the Category model exists

export interface Examination {
    eid?: number;
    title: string;
    description?: string;
    active: boolean;
    examinationDate?: string;
    category: Category;  // Category is passed as an object (interface)
    questions?: any[];
  }
