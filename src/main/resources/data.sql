-- Insertar roles
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

-- Insertar usuarios (asegúrate de que las contraseñas estén codificadas correctamente)
INSERT INTO users (username, password) VALUES ('usuario1', '$2a$12$K0PNWuP6xtBLdt8iFc.Jee6eJuCOHJx/y7gpQdp.I5EXg0Ub.JnEa');
INSERT INTO users (username, password) VALUES ('admin1', '$2a$12$K0PNWuP6xtBLdt8iFc.Jee6eJuCOHJx/y7gpQdp.I5EXg0Ub.JnEa');

-- Asignar roles a usuarios
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1); -- usuario1 tiene ROLE_USER
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1); -- admin1 tiene ROLE_USER
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2); -- admin1 también tiene ROLE_ADMIN

-- Insertar perfiles
INSERT INTO profiles (email, user_id) VALUES ('usuario1@example.com', 1);
INSERT INTO profiles (email, user_id) VALUES ('admin1@example.com', 2);