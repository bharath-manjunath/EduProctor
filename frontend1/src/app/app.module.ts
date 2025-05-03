import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatDatepickerToggle } from '@angular/material/datepicker';

// import { MatToolbarModule, MatSidenavModule, MatListModule, MatIconModule } from '@angular/material';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



import { WelcomeComponent } from './components/welcome/welcome.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AdminDashboardComponent } from './components/dashboard/admin-dashboard/admin-dashboard.component';
import { UserDashboardComponent } from './components/dashboard/user-dashboard/user-dashboard.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DashboardComponent } from './admin/dashboard/dashboard.component';
import { ProfileComponent } from './admin/profile/profile.component';
import { CategoriesComponent } from './admin/categories/categories.component';
import { AddCategoryComponent } from './admin/add-category/add-category.component';
import { ExaminationsComponent } from './admin/examinations/examinations.component';
import { AddExaminationComponent } from './admin/add-examination/add-examination.component';
import { ExaminationQuestionsComponent } from './admin/examination-questions/examination-questions.component';
import { AddQuestionComponent } from './admin/add-question/add-question.component';
import { UpdateExaminationComponent } from './admin/update-examination/update-examination.component';
import { UpdateCategoryComponent } from './admin/update-category/update-category.component';
import { UserWelcomeComponent } from './user/user-welcome/user-welcome.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { LoadExaminationComponent } from './user/load-examination/load-examination.component';
import { ExaminationIntroComponent } from './user/examination-intro/examination-intro.component';
import { ExaminationStartComponent } from './user/examination-start/examination-start.component';
import { LoadResultComponent } from './user/load-result/load-result.component';
import { LoadAllResultsComponent } from './user/load-all-results/load-all-results.component';
import { UserSidebarComponent } from './user/user-sidebar/user-sidebar.component';

@NgModule({
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  declarations: [
    AppComponent,
    WelcomeComponent,
    LoginComponent, 
    RegisterComponent,
    AdminDashboardComponent,
    UserDashboardComponent,
    DashboardComponent,
    ProfileComponent,
    CategoriesComponent,
    AddCategoryComponent,
    ExaminationsComponent,
    AddExaminationComponent,
    ExaminationQuestionsComponent,
    AddQuestionComponent,
    UpdateExaminationComponent,
    UpdateCategoryComponent,
    UserWelcomeComponent,
    UserProfileComponent,
    LoadExaminationComponent,
    ExaminationIntroComponent,
    ExaminationStartComponent,
    LoadResultComponent,
    LoadAllResultsComponent,
    UserSidebarComponent
  ],
  imports: [
    // RouterModule.forRoot([]),
    RouterModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule, // Add this
    ReactiveFormsModule, BrowserAnimationsModule, 
    BrowserAnimationsModule, // Required for animations in Material
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    MatCardModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatSelectModule,
    MatOptionModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
