import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AdminDashboardComponent } from './components/dashboard/admin-dashboard/admin-dashboard.component';
// import { UserDashboardComponent } from './components/dashboard/user-dashboard/user-dashboard.component';

import { DashboardComponent } from './admin/dashboard/dashboard.component';
import { ProfileComponent } from './admin/profile/profile.component';
import { CategoriesComponent } from './admin/categories/categories.component';
import { AddCategoryComponent } from './admin/add-category/add-category.component';
import { ExaminationsComponent } from './admin/examinations/examinations.component';
import { AddExaminationComponent } from './admin/add-examination/add-examination.component';
import { ExaminationQuestionsComponent } from './admin/examination-questions/examination-questions.component';
import { AddQuestionComponent } from './admin/add-question/add-question.component';
import {UpdateCategoryComponent} from './admin/update-category/update-category.component'
import {UpdateExaminationComponent} from './admin/update-examination/update-examination.component'


import { UserDashboardComponent } from './user/user-dashboard/user-dashboard.component';
import { UserWelcomeComponent } from './user/user-welcome/user-welcome.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { LoadExaminationComponent } from './user/load-examination/load-examination.component';
import { ExaminationIntroComponent } from './user/examination-intro/examination-intro.component';
import { ExaminationStartComponent } from './user/examination-start/examination-start.component';
import { LoadResultComponent } from './user/load-result/load-result.component';
import { LoadAllResultsComponent } from './user/load-all-results/load-all-results.component';


const routes: Routes = [
  { path: '', component: WelcomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  // { path: 'admin-dashboard', component: AdminDashboardComponent },
  // { path: 'user-dashboard', component: UserDashboardComponent },

  // {path: 'admin', component: DashboardComponent},

  {
    path: 'admin',
    component: DashboardComponent,
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' }, // Default route
      { path: 'home', component: UserDashboardComponent }, // Home section
      { path: 'profile', component: ProfileComponent }, // Profile section
      { path: 'categories', component: CategoriesComponent }, // Categories section
      { path: 'add-category', component: AddCategoryComponent },
      { path: 'update-category/:cid', component: UpdateCategoryComponent },// Add Category section
      { path: 'examinations/:categoryId', component: ExaminationsComponent }, // Examinations section
      { path: 'add-examination', component: AddExaminationComponent }, // Add Examination section
      { path: 'update-examination/:examId', component: UpdateExaminationComponent },
      { path: 'questions/:examId', component: ExaminationQuestionsComponent }, // Questions under an examination
      { path: 'add-question/:examId', component: AddQuestionComponent }, // Add question to an examination
    ],
  },
  {
    path: 'userdashboard',
    component: UserWelcomeComponent,
    children: [
      { path: '', component: UserDashboardComponent }, // Default route for user dashboard
      { path: 'profile', component: UserProfileComponent },
      { path: 'load-examination/:catid', component: LoadExaminationComponent },
      { path: 'examination-intro/:examId', component: ExaminationIntroComponent },
      { path: 'load-result', component: LoadResultComponent },
      { path: 'load-all-results', component: LoadAllResultsComponent },
    ],
  },

  {
    path: 'examination-start/:examId',
    component: ExaminationStartComponent,
  },

  { path: '**', redirectTo: '', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
