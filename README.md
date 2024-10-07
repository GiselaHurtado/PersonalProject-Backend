# 🚀 Paddle Tennis Couch App 🎾​

Welcome to the **Paddle Tennis Couch App**! 🎾​🚀 This repository contains a collection of web and mobile applications focused on creative user interactions and experiences, from appointment scheduling systems to user management dashboards. 🍃🎾​


## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
    - [Frontend](#frontend)
    - [Backend](#backend)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## 🌟 Features

- 📅 **Appointment Booking System**: Allows users to book, modify, or cancel appointments through an interactive calendar interface.
- 🛠️ **Admin Dashboard**: For administrators to manage users — add, edit, delete users, and track appointments.
- 🧑‍💻 **User Authentication**: Secure login, registration, and role-based access control. 🔐 
- 🎨 **Creative UX/UI**: Designed with user creativity and interaction in mind.

## 🛠️ Technologies

### Frontend
- Vue.js 3
- Vite
- Pinia for state management
- Axios for API requests
- Vue Router for navigation

### Backend
- Java
- Spring Boot
- Spring Data JPA
- MySQL Database
- Spring Security for authentication

## 🚀 Getting Started

### Prerequisites

- Node.js (v14 or later)
- npm or yarn
- Java JDK 11 or later
- Maven
- MySQL

### Installation

#### Frontend

1. Clone the frontend repository:
```bash
git clone https://github.com/FactoriaF5-Asturias/Proyect-SupportApp-Frontend2.git
```
2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm run dev
```


#### Backend
1. Clone the backend repository:
```bash
git clone https://github.com/GiselaHurtado/Proyect-SupportApp-Backend.git
```


2. Configure the database connection in `src/main/resources/application.properties`:
```bash
spring.datasource.url=jdbc:h2:mem:dcbapp
spring.datasource.username=sa
spring.datasource.password=
```

3. Run the application:
```bash
mvn spring-boot:run
```
## 🎮 Usage

1. Open your browser and navigate to `http://localhost:5173` (or the port specified by Vite).
2. Register a new account or log in with existing credentials.
3. Book a new paddle tennis appointment or view/edit existing ones through the calendar.
4. Administrators can manage users from the admin dashboard, where they can view, edit, delete, or add new users.


## 🛣️ API Endpoints

## 🛣️ API Endpoints

- `POST /api/v1/login`: Allows users to log in. Public access: `localhost:8080/api/v1/login`
- `GET /api/v1/register`: Displays the registration form. Public access: `localhost:8080/api/v1/register`
- `POST /api/v1/register`: Allows users to register a new account. Public access: `localhost:8080/api/v1/register`
- `POST /api/v1/users`: Create a new user. Only accessible to users with the `ADMIN` role: `localhost:8080/api/v1/users`
- `GET /api/v1/users`: Get a list of all users. Only accessible to users with the `ADMIN` role: `localhost:8080/api/v1/users`
- `PUT /api/v1/users`: Update user details. Only accessible to users with the `ADMIN` role: `localhost:8080/api/v1/users`
- `DELETE /api/v1/users`: Delete a user. Only accessible to users with the `ADMIN` role: `localhost:8080/api/v1/users`




For a complete list of endpoints and their usage, please refer to the API documentation.

## 🤝 Contributing

Want to make SupportApp even more psychologically awesome? We're all ears! Here's how:

1. Fork it (not with a real fork, please 🍴)
2. Create your feature branch: `git checkout -b feature/MindBlowingFeature`
3. Commit your changes: `git commit -m 'Add some MindBlowingFeature'`
4. Push to the branch: `git push origin feature/MindBlowingFeature`
5. Open a Pull Request (and maybe do some paddle tennis exercises 🧘‍♂️)

## 📜 License

This project has no active licence 🧑‍⚖️.

## 📞 Contact 
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)]([https://www.linkedin.com/in/tu-perfil-de-linkedin](https://www.linkedin.com/in/gisela-hurtado-ela11ian/)) 
[Gisela Hurtado](http://https://www.linkedin.com/in/gisela-hurtado-ela11ian/ "Linkedin")

## 🔗 Project Links: 
- Frontend: [https://github.com/GiselaHurtado/PersonalProject-Frontend.git]
- Backend: [https://github.com/GiselaHurtado/PersonalProject-Backend.git]