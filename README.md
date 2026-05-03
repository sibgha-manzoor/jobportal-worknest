# 💼 WorkNest — Job Portal

A full-stack job portal built with **Spring Boot** connecting recruiters and candidates.

## ✨ Features
- Role-based login (Candidate & Recruiter)
- Recruiters can post, edit, and delete jobs
- Candidates can browse listings and apply
- Spring Security authentication
- MySQL database with JPA entity management

## 🛠️ Tech Stack
Java 17 · Spring Boot · Spring Security · Spring Data JPA · MySQL · Thymeleaf · Lombok · Maven

## ⚙️ Setup

**Prerequisites:** Java 17+, Maven, MySQL

```bash
git clone https://github.com/sibgha-manzoor/jobportal-worknest.git
cd jobportal-worknest
```

Update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jobportal
spring.datasource.username=your_username
spring.datasource.password=your_password
```

Then run:
```bash
mvn clean install
mvn spring-boot:run
```
App runs at `http://localhost:8080`

## 👥 Roles
| Role | Access |
|------|--------|
| Candidate | Register, browse & apply for jobs |
| Recruiter | Register, post & manage job listings |

## 📌 Future Plans
- Admin dashboard · Email notifications · Skill-based job recommendations