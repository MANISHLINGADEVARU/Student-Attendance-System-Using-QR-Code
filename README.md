# Student-Attendance-System-Using-QR-Code

# SYSTEM ARCHITECTURE
 code
 ┌─────────────────────────────────────────────────────────────────┐
 │                     STUDENT ATTENDANCE SYSTEM                   │
 └─────────────────────────────────────────────────────────────────┘

                           ┌──────────────┐
                           │   FRONTEND   │
                           │ (HTML/CSS/JS)│
                           └──────────────┘
                                  │
                    ┌─────────────┼─────────────┐
                    │             │             │
            ┌───────▼──────┐ ┌───▼──────┐ ┌────▼─────────┐
            │  Admin Portal │ │ Faculty  │ │ Student App  │
            │  Dashboard   │ │ Dashboard│ │ (QR Scanner) │
            └───────┬──────┘ └───┬──────┘ └────┬─────────┘
                    │             │             │
                    └─────────────┼─────────────┘
                                  │
                    ┌─────────────▼──────────────┐
                    │      API GATEWAY/LAYER     │
                    │   (REST/Spring Boot)       │
                    └──────────┬──────────────────┘
                               │
            ┌──────────────────┼──────────────────┐
            │                  │                  │
     ┌──────▼────────┐ ┌──────▼────────┐ ┌──────▼────────┐
     │   QR Code     │ │  Attendance   │ │   Reporting   │
     │   Service     │ │   Service     │ │   Service     │
     │ (Generation/  │ │ (Mark/Verify) │ │ (Analytics)   │
     │  Scanning)    │ │               │ │               │
     └──────┬────────┘ └──────┬────────┘ └──────┬────────┘
            │                 │                  │
            └─────────────────┼���─────────────────┘
                              │
                    ┌─────────▼──────────┐
                    │   DATA LAYER       │
                    │  (JDBC/JPA/ORM)    │
                    └─────────┬──────────┘
                              │
            ┌─────────────────┼─────────────────┐
            │                 │                 │
     ┌──────▼──────┐ ┌───────▼──────┐ ┌───────▼─────┐
     │  PostgreSQL │ │    MySQL     │ │   MongoDB   │
     │  Database   │ │   Database   │ │  (Optional) │
     └─────────────┘ └──────────────┘ └─────────────┘

# TECHNOLOGY STACK
<img width="618" height="294" alt="image" src="https://github.com/user-attachments/assets/0f181744-712f-451d-8513-24acb8d708df" />

# DETAILED MODULE ARCHITECTURE
**1. FRONTEND LAYER**
Frontend/
├── admin/
│   ├── dashboard.html          # Main admin dashboard
│   ├── attendance-monitoring.html # Subject-wise tracking
│   ├── reports.html            # Report generation
│   └── admin.css
├── student/
│   ├── attendance-portal.html  # Student attendance marking
│   ├── scan-qr.html            # QR code scanner interface
│   ├── my-attendance.html      # View personal records
│   └── student.css
├── faculty/
│   ├── faculty-dashboard.html  # Faculty dashboard
│   ├── class-management.html   # Class/session management
│   ├── qr-generation.html      # Generate QR codes
│   └── faculty.css
├── common/
│   ├── login.html              # Authentication page
│   ├── header.html             # Common header
│   ├── footer.html             # Common footer
│   └── common.css
└── js/
    ├── qr-scanner.js           # QR scanning functionality
    ├── api-client.js           # API communication
    ├── auth.js                 # Authentication handling
    └── utils.js                # Utility functions 

**2. BACKEND LAYER (Java Spring Boot)**
code
src/main/java/com/attendance/
├── config/
│   ├── SecurityConfig.java
│   ├── DatabaseConfig.java
│   └── JwtConfig.java
├── controller/
│   ├── AuthController.java        # Login/Register
│   ├── QRCodeController.java      # QR generation/validation
│   ├── AttendanceController.java  # Mark/Retrieve attendance
│   ├── StudentController.java     # Student operations
│   ├── AdminController.java       # Admin operations
│   ├── ReportController.java      # Report generation
│   └── FacultyController.java     # Faculty operations
├── service/
│   ├── QRCodeService.java         # QR logic (generate/validate)
│   ├── AttendanceService.java     # Attendance tracking logic
│   ├── UserService.java           # User management
│   ├── ReportService.java         # Report generation logic
│   └── NotificationService.java   # Alerts/notifications
├── repository/
│   ├── StudentRepository.java
│   ├── AttendanceRepository.java
│   ├── QRCodeRepository.java
│   ├── ClassRepository.java
│   ├── SubjectRepository.java
│   └── UserRepository.java
├── model/
│   ├── User.java
│   ├── Student.java
│   ├── Faculty.java
│   ├── Class.java
│   ├── Subject.java
│   ├── AttendanceRecord.java
│   ├── QRCode.java
│   └── Report.java
├── dto/
│   ├── LoginRequest.java
│   ├── LoginResponse.java
│   ├── AttendanceDTO.java
│   ├── StudentDTO.java
│   └── ReportDTO.java
├── security/
│   ├── JwtTokenProvider.java      # JWT token generation/validation
│   ├── CustomUserDetailsService.java
│   └── JwtAuthFilter.java
├── exception/
│   ├── QRCodeException.java
│   ├── AttendanceException.java
│   ├── UserNotFoundException.java
│   └── GlobalExceptionHandler.java
└── Application.java               # Entry point

**3. DATABASE LAYER**
TABLES:
├── users (id, username, email, password, role, created_at)
├── students (id, user_id, enrollment_id, batch, branch)
├── faculty (id, user_id, department, specialization)
├── subjects (id, subject_name, subject_code, faculty_id)
├── classes (id, class_id, subject_id, date, start_time, end_time)
├── qr_codes (id, qr_code_data, class_id, generated_at, expires_at)
├── attendance_records (id, student_id, class_id, qr_code_id, marked_at)
└── reports (id, generated_by, report_type, generated_at, data)

# DEVELOPMENT PHASES & IMPLEMENTATION PLAN

**PHASE 1:** Foundation Setup (Weeks 1-2)
✅ Project structure initialization
✅ Database schema design & migration
✅ User authentication (JWT)
✅ Role-based access control (RBAC)
Deliverable: Basic authentication working
**PHASE 2:** Core Features (Weeks 3-5)
✅ QR Code generation engine
✅ Attendance marking service
✅ Student portal for QR scanning
Deliverable: End-to-end QR scanning & marking
**PHASE 3:** Dashboard & Monitoring (Weeks 6-8)
✅ Admin dashboard
✅ Faculty dashboard
✅ Subject-wise attendance tracking
✅ Real-time attendance updates
Deliverable: Live monitoring capabilities
**PHASE 4:** Reporting & Analytics (Weeks 9-10)
✅ Report generation
✅ Export functionality (PDF/CSV/Excel)
✅ Attendance analytics
✅ Performance metrics
Deliverable: Comprehensive reporting system
**PHASE 5:** Advanced Features (Weeks 11-12)
✅ Notifications & alerts
✅ Bulk operations
✅ Mobile app integration
✅ API documentation
Deliverable: Full-fledged production system
# KEY DEVELOPMENT TASKS (Action Items)
**Backend Tasks**
 Set up Spring Boot project with Maven
 Configure PostgreSQL/MySQL database
 Implement JWT authentication
 Create entity models & repositories
 Implement QR code service (ZXing library)
 Build attendance tracking service
 Create REST API endpoints
 Implement role-based access control
 Add exception handling & validation
 Create report generation service
 Write unit & integration tests
 Set up API documentation (Swagger)
**Frontend Tasks**
 Design responsive UI layouts
 Implement login/authentication page
 Build admin dashboard
 Create faculty dashboard
 Develop student portal
 Implement QR code scanner (jsQR/html5-qrcode)
 Add real-time attendance display
 Create report generation UI
 Implement export functionality
 Add notifications/alerts UI
 Optimize for mobile devices
**DevOps & Deployment**
 Set up GitHub CI/CD pipeline
 Configure Docker containerization
 Set up staging environment
 Implement logging & monitoring
 Configure database backups
 Set up production deployment
# DEPLOYMENT ARCHITECTURE
Code
┌────────────────────────────────────────────────────────────┐
│                    DOCKER CONTAINERS                        │
├────────────────────────────────────────────────────────────┤
│  ┌──────────────┐ ┌──────────────┐ ┌────────────────────┐  │
│  │   Frontend   │ │ Spring Boot  │ │   PostgreSQL       │  │
│  │  (Nginx)     │ │  (Port 8080) │ │   (Port 5432)      │  │
│  └──────────────┘ └──────────────┘ └────────────────────┘  │
└────────────────────────────────────────────────────────────┘
                          │
            ┌──────────��──┴──────────────┐
            │                            │
     ┌──────▼────────┐         ┌────────▼──────┐
     │ Load Balancer │         │  Redis Cache  │
     │  (Reverse     │         │  (Optional)   │
     │   Proxy)      │         └───────────────┘
     └──────┬────────┘
            │
     ┌──────▼────────────────────────────┐
     │     CDN / Cloud Provider          │
     │ (AWS/Azure/GCP/DigitalOcean)      │
     └─────────────────────────────────────┘
SECURITY CONSIDERATIONS
✅ Authentication: JWT with refresh tokens
✅ Authorization: Role-based access control (RBAC)
✅ Encryption: SSL/TLS for data in transit, bcrypt for passwords
✅ Validation: Input validation on frontend & backend
✅ CORS: Properly configured CORS policies
✅ Rate Limiting: API rate limiting to prevent abuse
✅ SQL Injection: Parameterized queries & ORM
✅ CSRF Protection: Spring Security CSRF tokens


MAIN DASHBOARD OF THE STUDENT ATTENDANCE SYSTEM
<img width="1365" height="717" alt="image" src="https://github.com/user-attachments/assets/fad9d601-5b6a-4b01-85ec-9a9d36192d9c" />


SUBJECT WISE ATTENDANCE MONITORING [using QR code]
<img width="1365" height="721" alt="image" src="https://github.com/user-attachments/assets/41b318f2-e0c2-4220-a6b4-5b984d4c7418" />

MARK ATTENDANCE (STUDENT PORTAL)
<img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/3bf4b075-5be8-474b-b32c-a8fc9b3307ee" />
