# EProduct - Product Management System

## 📌 Project Overview
Best Store is a product management web application built using **Spring Boot** and **Thymeleaf**, providing an intuitive UI for adding, editing, and managing products. It features form validation, image upload support, and responsive design using **Bootstrap**.

## 🎯 Features
- 🛍️ **Add New Products** (with validation & error handling)
- ✏️ **Edit Product Details** (including image updates)
- 🗂️ **Categorization** (Phones, Computers, Accessories, etc.)
- 💰 **Price Management** (supports decimal values)
- 📷 **Image Upload Support**
- 📅 **Product Creation Date Display**
- 🔄 **User-Friendly Interface with Bootstrap Styling**

## 🛠️ Tech Stack
- **Backend:** Spring Boot, Spring MVC, Thymeleaf
- **Frontend:** Bootstrap 5, HTML5, CSS3
- **Database:** MySQL (or any preferred database)
- **Build Tool:** Maven
- **Version Control:** Git

## 🚀 Installation & Setup

### 1️⃣ Clone the Repository
```sh
 git clone https://github.com/yourusername/best-store.git
 cd best-store
```

### 2️⃣ Configure the Database
- Update `application.properties` with your MySQL credentials.
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/best_store
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### 3️⃣ Run the Application
```sh
mvn spring-boot:run
```

### 4️⃣ Access the Application
Open your browser and go to:
```
http://localhost:8080/products
```

## 📂 Project Structure
```
📦 best-store
 ┣ 📂 src/main/java/com/example/beststore
 ┃ ┣ 📂 controllers
 ┃ ┣ 📂 models
 ┃ ┣ 📂 repositories
 ┃ ┣ 📂 services
 ┃ ┗ 📂 dto
 ┣ 📂 src/main/resources
 ┃ ┣ 📂 templates (Thymeleaf pages)
 ┃ ┣ 📂 static (CSS, JS, Images)
 ┃ ┗ application.properties
 ┣ 📜 pom.xml
 ┗ 📜 README.md
```

## 🖼️ Screenshots
### 📌 Product Form Page
![Product Form](https://via.placeholder.com/800x400)

### 📌 Product List Page
![Product List](https://via.placeholder.com/800x400)

## 🤝 Contributing
Pull requests are welcome! For major changes, please open an issue first to discuss your ideas.

## 📜 License
This project is **open-source** and available under the [MIT License](LICENSE).

## 📬 Contact
For queries, reach out via:
- Email: [samantsaino2003@gmail.com]
- GitHub: [Samant-saini](https://github.com/Samant-saini)

---
🚀 **Happy Coding!**

