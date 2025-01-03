# EduProctor

EduProctor is an Online Examination Portal that allows teachers (admins) to create and monitor examinations while enabling students (users) to attend exams, view results, and assess their knowledge in specific subjects. This platform provides a seamless experience for both admins and students, facilitating a robust online assessment system.

## Tech Stack, Tools, and Frameworks Used

### Backend:
- **Programming Language**: Java
- **Framework**: Spring Boot
- **Database**: H2
- **Build Tool**: Maven
- **ORM**: Spring JPA (Hibernate)
- **Authentication**: Spring Security with JWT

### Frontend:
- **Framework**: Angular 15
- **UI Design**: Angular Material

### Other Tools:
- **Version Control**: Git
- **IDE**: IntelliJ IDEA (for backend), Visual Studio Code (for frontend)

## Features of the Application

### Teacher (Admin) Functionalities:
- **Category Management**:
  - Add categories to organize examinations (e.g., "Mid Term Examinations" includes "Science" and "Social Studies").
- **Examination Management**:
  - Add examinations under specific categories.
  - Define examination statuses: **Active** or **Inactive**.
- **Question Management**:
  - Add questions for each examination, supporting the following types:
    - **Quiz** (Multiple Choice Questions)
    - **Fill-in-the-Blank**
    - **Short Answer**
    - **Essay Questions**
  - Provide correct answers for automated scoring.
  - Update questions and examination statuses as needed.

### Student (User) Functionalities:
- **Explore Categories and Examinations**:
  - View all categories and the active examinations under them.
  - Only examinations marked as **Active** are accessible.
- **Take Examinations**:
  - View instructions before starting an exam.
  - Attempt questions within the stipulated time; the system auto-submits the exam if time runs out.
- **View Results**:
  - See results of the current examination immediately after submission.
  - Access previous examination results in a dedicated history section.

## Steps to Clone and Run Locally

### Prerequisites:
- **Java JDK** (version 11 or above)
- **Node.js** (version 14 or above)
- **Angular CLI**
  
### Clone the Repository:
```bash
git clone https://github.com/your-username/EduProctor.git
cd EduProctor
```

### Backend Setup:
1. Navigate to the backend folder:
   ```bash
   cd backend
   ```
2. Build the project using Maven:
   ```bash
   mvn clean install
   ```
3. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

### Frontend Setup:
1. Navigate to the frontend folder:
   ```bash
   cd ../frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the Angular development server:
   ```bash
   ng serve
   ```
4. Open your browser and navigate to `http://localhost:4200`.

## Author
**Bharath M**
- GitHub: [bharath-manjunath](https://github.com/bharath-manjunath)
- Email: [bharathmgowda77@gmail.com](mailto:bharathmgowda77@gmail.com)


