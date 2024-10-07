# 🚀 Paddle Tennis Couch App 🎾​

Welcome to the **Paddle Tennis Couch App**! 🎾​🚀 This repository contains a collection of web and mobile applications focused on creative user interactions and experiences, from appointment scheduling systems to user management dashboards. 🍃🎾​
<img width="905" alt="Captura de pantalla 2024-10-07 a la(s) 20 19 01" src="https://github.com/user-attachments/assets/de042358-f0e4-406f-9b59-ba17c6c9f0ad">
<img width="926" alt="Captura de pantalla 2024-10-07 a la(s) 20 19 23" src="https://github.com/user-attachments/assets/cc80a01c-ecf3-4bb3-9db5-ef9ccbe947e7">
<img width="926" alt="Captura de pantalla 2024-10-07 a la(s) 20 19 28" src="https://github.com/user-attachments/assets/f8f07aa5-7415-4829-bde6-8adacea307cb">
<img width="926" alt="Captura de pantalla 2024-10-07 a la(s) 20 19 28" src="https://github.com/user-attachments/assets/3bb0ce7a-04d7-4f73-ac40-5bf4715e6fb3">
<img width="905" alt="Captura de pantalla 2024-10-07 a la(s) 20 19 07" src="https://github.com/user-attachments/assets/dde2debc-953f-4426-bdcb-05944d85bf9f">
<img width="926" alt="Captura de pantalla 2024-10-07 a la(s) 20 19 39" src="https://github.com/user-attachments/assets/f0738dc3-a432-46e8-b35c-aa5e3697fdad">
<img width="987" alt="Captura de pantalla 2024-10-07 a la(s) 20 21 12" src="https://github.com/user-attachments/assets/f68f4c1b-01d9-4ef8-9891-9c58abde3770">
<img width="926" alt="Captura de pantalla 2024-10-07 a la(s) 20 20 37" src="https://github.com/user-attachments/assets/07fb0c53-a5ad-49c7-aa9a-3bee8a9203d4">
<img width="926" alt="Captura de pantalla 2024-10-07 a la(s) 20 20 46" src="https://github.com/user-attachments/assets/40ad2b16-4467-42c7-9d15-d76744fa2681">
<img width="926" alt="Captura de pantalla 2024-10-07 a la(s) 20 19 34" src="https://github.com/user-attachments/assets/1c5d2cc0-df5a-4b45-9027-7cda84d3499c">





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


## User Flow ​👩‍💻​
1. Register/Login: New users can register, and existing users can log in.
2. Book an Appointment: Once logged in, users can view a calendar and schedule appointments.
3. Manage Appointments: Users can modify or cancel their existing appointments.
<img width="755" alt="Captura de pantalla 2024-10-07 a la(s) 19 27 56" src="https://github.com/user-attachments/assets/68a1cec0-d298-4ab3-a707-cfd9ce978d5a">



## Admin Flow ​👨‍💻​
1. Login: Admin users log in with elevated permissions.
2. Dashboard: Admins access a dashboard to view and manage registered users.
3. User Management: Admins can add, edit, or delete users.
4. Logout: Admins can safely log out of the system.
<img width="309" alt="Captura de pantalla 2024-10-07 a la(s) 19 28 21" src="https://github.com/user-attachments/assets/1b364d10-0198-4c62-84d8-ea0bacb03eeb">

## Diagram 📚​
<img width="632" alt="Captura de pantalla 2024-10-07 a la(s) 10 55 15" src="https://github.com/user-attachments/assets/8d8bd27b-c429-4c07-be2c-01bf7eb5f297">


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
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)]([https://www.linkedin.com/in/tu-perfil-de-linkedin](www.linkedin.com/in/giselahurtado)) 
[Gisela Hurtado](www.linkedin.com/in/giselahurtado "Linkedin")

## 🔗 Project Links: 
- Frontend: [https://github.com/GiselaHurtado/PersonalProject-Frontend.git]
- Backend: [https://github.com/GiselaHurtado/PersonalProject-Backend.git]
