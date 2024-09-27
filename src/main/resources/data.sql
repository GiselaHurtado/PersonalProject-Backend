-- Insertar roles
INSERT INTO roles (name) VALUES ('USER');
INSERT INTO roles (name) VALUES ('ADMIN');

-- Insertar usuarios/password 
INSERT INTO users (username, email, password) VALUES ('user', 'user@example.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a');
INSERT INTO users (username, email, password) VALUES ('admin', 'admin@example.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a');
-- Insertar un nuevo usuario de prueba con una contraseña simple
INSERT INTO users (username, email, password) VALUES ('testuser', 'testuser@example.com', 'password123');


-- Asignar roles a usuarios
INSERT INTO user_roles (user_id, role_id) SELECT u.user_id, r.role_id FROM users u, roles r WHERE u.username = 'user' AND r.name = 'USER';
INSERT INTO user_roles (user_id, role_id) SELECT u.user_id, r.role_id FROM users u, roles r WHERE u.username = 'admin' AND r.name = 'USER';
INSERT INTO user_roles (user_id, role_id) SELECT u.user_id, r.role_id FROM users u, roles r WHERE u.username = 'admin' AND r.name = 'ADMIN';