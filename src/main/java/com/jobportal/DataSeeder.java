package com.jobportal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jobportal.Entities.Application;
import com.jobportal.Entities.ApplicationStatus;
import com.jobportal.Entities.Job;
import com.jobportal.Entities.Role;
import com.jobportal.Entities.Users;
import com.jobportal.Repositories.ApplicationRepo;
import com.jobportal.Repositories.JobRepo;
import com.jobportal.Repositories.UsersRepo;
import com.jobportal.Service.PasswordUtil;

/**
 * Seeds the database with sample data on first run.
 * Skips seeding if data already exists (idempotent).
 */
@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired private UsersRepo usersRepo;
    @Autowired private JobRepo jobRepo;
    @Autowired private ApplicationRepo applicationRepo;

    @Override
    public void run(String... args) {

        // Skip if data already present
        if (usersRepo.count() > 0) return;

        // ─────────────────────────────────────────────
        // RECRUITERS
        // ─────────────────────────────────────────────
        Users r1 = new Users();
        r1.setUserName("sarah.recruiter");
        r1.setEmail("sarah@techcorp.com");
        r1.setPassword(PasswordUtil.hashPassword("password123"));
        r1.setRole(Set.of(Role.ROLE_RECRUITER));
        usersRepo.save(r1);

        Users r2 = new Users();
        r2.setUserName("james.hr");
        r2.setEmail("james@financeplus.com");
        r2.setPassword(PasswordUtil.hashPassword("password123"));
        r2.setRole(Set.of(Role.ROLE_RECRUITER));
        usersRepo.save(r2);

        Users r3 = new Users();
        r3.setUserName("nina.talent");
        r3.setEmail("nina@designstudio.com");
        r3.setPassword(PasswordUtil.hashPassword("password123"));
        r3.setRole(Set.of(Role.ROLE_RECRUITER));
        usersRepo.save(r3);

        // ─────────────────────────────────────────────
        // CANDIDATES
        // ─────────────────────────────────────────────
        Users c1 = new Users();
        c1.setUserName("ali.hassan");
        c1.setEmail("ali.hassan@gmail.com");
        c1.setPassword(PasswordUtil.hashPassword("password123"));
        c1.setRole(Set.of(Role.ROLE_CANDIDATE));
        usersRepo.save(c1);

        Users c2 = new Users();
        c2.setUserName("fatima.malik");
        c2.setEmail("fatima.malik@gmail.com");
        c2.setPassword(PasswordUtil.hashPassword("password123"));
        c2.setRole(Set.of(Role.ROLE_CANDIDATE));
        usersRepo.save(c2);

        Users c3 = new Users();
        c3.setUserName("usman.tariq");
        c3.setEmail("usman.tariq@gmail.com");
        c3.setPassword(PasswordUtil.hashPassword("password123"));
        c3.setRole(Set.of(Role.ROLE_CANDIDATE));
        usersRepo.save(c3);

        Users c4 = new Users();
        c4.setUserName("ayesha.khan");
        c4.setEmail("ayesha.khan@gmail.com");
        c4.setPassword(PasswordUtil.hashPassword("password123"));
        c4.setRole(Set.of(Role.ROLE_CANDIDATE));
        usersRepo.save(c4);

        Users c5 = new Users();
        c5.setUserName("bilal.ahmed");
        c5.setEmail("bilal.ahmed@gmail.com");
        c5.setPassword(PasswordUtil.hashPassword("password123"));
        c5.setRole(Set.of(Role.ROLE_CANDIDATE));
        usersRepo.save(c5);

        // ─────────────────────────────────────────────
        // JOBS  (posted by recruiter usernames)
        // ─────────────────────────────────────────────

        // --- TechCorp jobs (sarah.recruiter) ---
        Job j1 = new Job();
        j1.setJobTitle("Senior Java Backend Developer");
        j1.setJobDesc("Design and develop high-performance RESTful APIs using Spring Boot. " +
                "Collaborate with frontend teams and DevOps on CI/CD pipelines. " +
                "Mentor junior developers and participate in code reviews.");
        j1.setJobLoc("Lahore, Pakistan");
        j1.setJobCompany("TechCorp Solutions");
        j1.setJobPostedBy("sarah.recruiter");
        j1.setJobPosted(LocalDateTime.now().minusDays(10));
        j1.setJobSalary("PKR 150,000 – 200,000 / month");
        j1.setJobQualification("Bachelor's in Computer Science or Software Engineering. " +
                "3+ years of Java / Spring Boot experience.");
        j1.setVacancy(3);
        j1.setApplicationDeadline(LocalDate.now().plusDays(20));
        j1.setJobType("Full-Time");
        jobRepo.save(j1);

        Job j2 = new Job();
        j2.setJobTitle("React Frontend Developer");
        j2.setJobDesc("Build responsive, accessible web interfaces using React and Tailwind CSS. " +
                "Work closely with UX designers and backend engineers to deliver polished products.");
        j2.setJobLoc("Remote");
        j2.setJobCompany("TechCorp Solutions");
        j2.setJobPostedBy("sarah.recruiter");
        j2.setJobPosted(LocalDateTime.now().minusDays(7));
        j2.setJobSalary("PKR 120,000 – 160,000 / month");
        j2.setJobQualification("2+ years React experience. Proficiency in HTML5, CSS3, JavaScript ES6+.");
        j2.setVacancy(2);
        j2.setApplicationDeadline(LocalDate.now().plusDays(15));
        j2.setJobType("Remote");
        jobRepo.save(j2);

        Job j3 = new Job();
        j3.setJobTitle("DevOps Engineer");
        j3.setJobDesc("Manage cloud infrastructure on AWS. Implement and maintain CI/CD pipelines using " +
                "GitHub Actions and Jenkins. Monitor system health and ensure 99.9% uptime SLAs.");
        j3.setJobLoc("Islamabad, Pakistan");
        j3.setJobCompany("TechCorp Solutions");
        j3.setJobPostedBy("sarah.recruiter");
        j3.setJobPosted(LocalDateTime.now().minusDays(3));
        j3.setJobSalary("PKR 180,000 – 230,000 / month");
        j3.setJobQualification("AWS Certified or equivalent. Experience with Docker, Kubernetes, Terraform.");
        j3.setVacancy(1);
        j3.setApplicationDeadline(LocalDate.now().plusDays(25));
        j3.setJobType("Full-Time");
        jobRepo.save(j3);

        // --- FinancePlus jobs (james.hr) ---
        Job j4 = new Job();
        j4.setJobTitle("Financial Analyst");
        j4.setJobDesc("Analyze financial data and prepare detailed reports for senior management. " +
                "Develop financial models, forecasts, and budgeting plans. " +
                "Monitor market trends and investment performance.");
        j4.setJobLoc("Karachi, Pakistan");
        j4.setJobCompany("FinancePlus Group");
        j4.setJobPostedBy("james.hr");
        j4.setJobPosted(LocalDateTime.now().minusDays(14));
        j4.setJobSalary("PKR 100,000 – 140,000 / month");
        j4.setJobQualification("Bachelor's in Finance, Accounting, or Economics. " +
                "CFA Level I preferred. Proficiency in Excel and Power BI.");
        j4.setVacancy(2);
        j4.setApplicationDeadline(LocalDate.now().plusDays(10));
        j4.setJobType("Full-Time");
        jobRepo.save(j4);

        Job j5 = new Job();
        j5.setJobTitle("Risk & Compliance Officer");
        j5.setJobDesc("Identify, assess, and mitigate financial and operational risks. " +
                "Ensure compliance with SBP regulations and internal policies. " +
                "Prepare risk reports for board-level presentations.");
        j5.setJobLoc("Karachi, Pakistan");
        j5.setJobCompany("FinancePlus Group");
        j5.setJobPostedBy("james.hr");
        j5.setJobPosted(LocalDateTime.now().minusDays(5));
        j5.setJobSalary("PKR 130,000 – 170,000 / month");
        j5.setJobQualification("MBA Finance or LLB with compliance background. 3+ years in banking/finance sector.");
        j5.setVacancy(1);
        j5.setApplicationDeadline(LocalDate.now().plusDays(18));
        j5.setJobType("Full-Time");
        jobRepo.save(j5);

        // --- DesignStudio jobs (nina.talent) ---
        Job j6 = new Job();
        j6.setJobTitle("UI/UX Designer");
        j6.setJobDesc("Create wireframes, prototypes, and high-fidelity designs for web and mobile apps. " +
                "Conduct user research and usability testing. Maintain the design system in Figma.");
        j6.setJobLoc("Lahore, Pakistan");
        j6.setJobCompany("PixelCraft Design Studio");
        j6.setJobPostedBy("nina.talent");
        j6.setJobPosted(LocalDateTime.now().minusDays(6));
        j6.setJobSalary("PKR 90,000 – 130,000 / month");
        j6.setJobQualification("Bachelor's in Design or HCI. Strong Figma / Adobe XD portfolio required.");
        j6.setVacancy(2);
        j6.setApplicationDeadline(LocalDate.now().plusDays(12));
        j6.setJobType("Full-Time");
        jobRepo.save(j6);

        Job j7 = new Job();
        j7.setJobTitle("Graphic Designer (Internship)");
        j7.setJobDesc("Assist the design team with branding assets, social media graphics, and marketing materials. " +
                "Great opportunity to build your portfolio in a professional studio environment.");
        j7.setJobLoc("Lahore, Pakistan");
        j7.setJobCompany("PixelCraft Design Studio");
        j7.setJobPostedBy("nina.talent");
        j7.setJobPosted(LocalDateTime.now().minusDays(2));
        j7.setJobSalary("PKR 30,000 – 40,000 / month");
        j7.setJobQualification("Currently enrolled in a Design or Fine Arts programme. " +
                "Knowledge of Adobe Illustrator and Photoshop.");
        j7.setVacancy(3);
        j7.setApplicationDeadline(LocalDate.now().plusDays(30));
        j7.setJobType("Internship");
        jobRepo.save(j7);

        // ─────────────────────────────────────────────
        // APPLICATIONS  (candidates applying for jobs)
        // CV links are publicly available sample/demo resume PDFs
        // ─────────────────────────────────────────────

        // Ali Hassan → Java Developer (SHORTLISTED)
        Application a1 = new Application();
        a1.setJob(j1);
        a1.setUsers(c1);
        a1.setAppliedAt(LocalDateTime.now().minusDays(8));
        a1.setResume("https://mozilla.github.io/pdf.js/web/compressed.tracemonkey-pldi-09.pdf");
        a1.setStatus(ApplicationStatus.SHORTLISTED);
        applicationRepo.save(a1);

        // Ali Hassan → React Developer (UNDER_REVIEW)
        Application a2 = new Application();
        a2.setJob(j2);
        a2.setUsers(c1);
        a2.setAppliedAt(LocalDateTime.now().minusDays(5));
        a2.setResume("https://mozilla.github.io/pdf.js/web/compressed.tracemonkey-pldi-09.pdf");
        a2.setStatus(ApplicationStatus.UNDER_REVIEW);
        applicationRepo.save(a2);

        // Fatima Malik → Financial Analyst (ACCEPTED)
        Application a3 = new Application();
        a3.setJob(j4);
        a3.setUsers(c2);
        a3.setAppliedAt(LocalDateTime.now().minusDays(12));
        a3.setResume("https://mozilla.github.io/pdf.js/web/compressed.tracemonkey-pldi-09.pdf");
        a3.setStatus(ApplicationStatus.ACCEPTED);
        applicationRepo.save(a3);

        // Fatima Malik → Risk Officer (APPLIED)
        Application a4 = new Application();
        a4.setJob(j5);
        a4.setUsers(c2);
        a4.setAppliedAt(LocalDateTime.now().minusDays(3));
        a4.setResume("https://mozilla.github.io/pdf.js/web/compressed.tracemonkey-pldi-09.pdf");
        a4.setStatus(ApplicationStatus.APPLIED);
        applicationRepo.save(a4);

        // Usman Tariq → DevOps Engineer (UNDER_REVIEW)
        Application a5 = new Application();
        a5.setJob(j3);
        a5.setUsers(c3);
        a5.setAppliedAt(LocalDateTime.now().minusDays(2));
        a5.setResume("https://mozilla.github.io/pdf.js/web/compressed.tracemonkey-pldi-09.pdf");
        a5.setStatus(ApplicationStatus.UNDER_REVIEW);
        applicationRepo.save(a5);

        // Usman Tariq → Java Developer (REJECTED)
        Application a6 = new Application();
        a6.setJob(j1);
        a6.setUsers(c3);
        a6.setAppliedAt(LocalDateTime.now().minusDays(9));
        a6.setResume("https://mozilla.github.io/pdf.js/web/compressed.tracemonkey-pldi-09.pdf");
        a6.setStatus(ApplicationStatus.REJECTED);
        applicationRepo.save(a6);

        // Ayesha Khan → UI/UX Designer (SHORTLISTED)
        Application a7 = new Application();
        a7.setJob(j6);
        a7.setUsers(c4);
        a7.setAppliedAt(LocalDateTime.now().minusDays(4));
        a7.setResume("https://mozilla.github.io/pdf.js/web/compressed.tracemonkey-pldi-09.pdf");
        a7.setStatus(ApplicationStatus.SHORTLISTED);
        applicationRepo.save(a7);

        // Ayesha Khan → Graphic Design Internship (APPLIED)
        Application a8 = new Application();
        a8.setJob(j7);
        a8.setUsers(c4);
        a8.setAppliedAt(LocalDateTime.now().minusDays(1));
        a8.setResume("https://mozilla.github.io/pdf.js/web/compressed.tracemonkey-pldi-09.pdf");
        a8.setStatus(ApplicationStatus.APPLIED);
        applicationRepo.save(a8);

        // Bilal Ahmed → React Developer (APPLIED)
        Application a9 = new Application();
        a9.setJob(j2);
        a9.setUsers(c5);
        a9.setAppliedAt(LocalDateTime.now().minusDays(6));
        a9.setResume("https://mozilla.github.io/pdf.js/web/compressed.tracemonkey-pldi-09.pdf");
        a9.setStatus(ApplicationStatus.APPLIED);
        applicationRepo.save(a9);

        // Bilal Ahmed → Financial Analyst (UNDER_REVIEW)
        Application a10 = new Application();
        a10.setJob(j4);
        a10.setUsers(c5);
        a10.setAppliedAt(LocalDateTime.now().minusDays(11));
        a10.setResume("https://mozilla.github.io/pdf.js/web/compressed.tracemonkey-pldi-09.pdf");
        a10.setStatus(ApplicationStatus.UNDER_REVIEW);
        applicationRepo.save(a10);

        System.out.println("==============================================");
        System.out.println("  WorkNest — Sample data seeded successfully!");
        System.out.println("  Recruiter logins  (password: password123)");
        System.out.println("    sarah.recruiter  →  TechCorp  (3 jobs)");
        System.out.println("    james.hr         →  FinancePlus (2 jobs)");
        System.out.println("    nina.talent      →  PixelCraft  (2 jobs)");
        System.out.println("  Candidate logins  (password: password123)");
        System.out.println("    ali.hassan  /  fatima.malik  /  usman.tariq");
        System.out.println("    ayesha.khan  /  bilal.ahmed");
        System.out.println("==============================================");
    }
}