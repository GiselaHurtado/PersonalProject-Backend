-- Insertar roles
INSERT INTO roles (role_id, name) VALUES (default, 'ROLE_USER');
INSERT INTO roles (role_id, name) VALUES (default, 'ROLE_ADMIN');

-- Insertar usuarios/password/email
INSERT INTO users (user_id, username, email, password) VALUES (default, 'user', 'user@example.com', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, email, password) VALUES (default, 'admin', 'admin@example.com', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');

-- Relacionar roles con usuarios
INSERT INTO roles_users (role_id, user_id) VALUES (1, 1); -- ROLE_USER para el usuario
INSERT INTO roles_users (role_id, user_id) VALUES (2, 2); -- ROLE_ADMIN para el admin

-- Insertar perfiles relacionados con los usuarios
INSERT INTO profiles (email, user_id) VALUES ('user@example.com', 1);
INSERT INTO profiles (email, user_id) VALUES ('admin@example.com', 2);
