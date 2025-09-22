# DevTracker Backend ⚡

[![Java](https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/SpringBoot-3.2.2-green?logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![License](https://img.shields.io/badge/License-MIT-green)](LICENSE)

**DevTracker Backend** is the RESTful API server powering the DevTracker project management app. It handles projects, tasks, teams, and user authentication with a robust backend built on **Java Spring Boot** and **PostgreSQL**.

---

## 🌟 Features

- ✅ User authentication (login/register)  
- ✅ CRUD operations for projects and tasks  
- ✅ Assign tasks to team members  
- ✅ REST API ready for frontend integration  
- ✅ PostgreSQL database integration  

---

## 🛠 Tech Stack

**Backend:** Java, Spring Boot  
**Database:** PostgreSQL  
**Build Tool:** Maven  
**API Testing:** Postman / Swagger  

---

## 🚀 Installation

1. **Clone the repository**  
   ```bash
   git clone <your-backend-repo-url>
   cd devtracker-backend
   ```

2. **Configure the database**  
   - Create a PostgreSQL database (e.g., `devtracker`)  
   - Update `application.properties` with your credentials:  
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/devtracker
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Build and run**  
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

The backend will run on `http://localhost:8080` by default.

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/YourFeature`
3. Commit your changes: `git commit -m "Add your feature"`
4. Push to the branch: `git push origin feature/YourFeature`
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

Made with ❤️ by Vignesh P.  
Connect with me: [LinkedIn](https://www.linkedin.com/in/vignesh-p-46153a302/) | [GitHub](https://github.com/jarvis2754)
