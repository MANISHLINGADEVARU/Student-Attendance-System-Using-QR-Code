# Student-Attendance-System-Using-QR-Code

# SYSTEM ARCHITECTURE
┌─────────────────────────────────────────────────────────────────┐
│                     STUDENT ATTENDANCE SYSTEM                    │
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

TECHNOLOGY STACK
Layer	Technology	Purpose
Frontend	HTML5, CSS3, JavaScript	User Interfaces
Backend	Java (Spring Boot)	RESTful APIs, Business Logic
QR Processing	ZXing Library	QR Code Generation & Scanning
Database	PostgreSQL/MySQL	Persistent Data Storage
Authentication	JWT/Spring Security	User Authentication & Authorization
Build Tool	Maven/Gradle	Project Build & Dependencies
Server	Apache Tomcat	Application Server

MAIN DASHBOARD OF THE STUDENT ATTENDANCE SYSTEM
<img width="1365" height="717" alt="image" src="https://github.com/user-attachments/assets/fad9d601-5b6a-4b01-85ec-9a9d36192d9c" />


SUBJECT WISE ATTENDANCE MONITORING [using QR code]
<img width="1365" height="721" alt="image" src="https://github.com/user-attachments/assets/41b318f2-e0c2-4220-a6b4-5b984d4c7418" />

MARK ATTENDANCE (STUDENT PORTAL)
<img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/3bf4b075-5be8-474b-b32c-a8fc9b3307ee" />
