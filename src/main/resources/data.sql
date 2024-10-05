-- Insertar roles
INSERT INTO roles (role_id, name) VALUES (default, 'ROLE_USER');
INSERT INTO roles (role_id, name) VALUES (default, 'ROLE_ADMIN');

-- Insertar usuarios/password/email
-- Contraseñas hasheadas usando bcrypt para seguridad (puedes generar nuevas contraseñas hasheadas si es necesario)
INSERT INTO users (user_id, username, email, password) VALUES (default, 'user', 'user@example.com', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, email, password) VALUES (default, 'admin', 'admin@example.com', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, email, password) VALUES (default, 'dakota.rice', 'dakota.rice@example.com', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, email, password) VALUES (default, 'minerva.hooper', 'minerva.hooper@example.com', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, email, password) VALUES (default, 'sage.rodriguez', 'sage.rodriguez@example.com', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, email, password) VALUES (default, 'philip.chaney', 'philip.chaney@example.com', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, email, password) VALUES (default, 'doris.greene', 'doris.greene@example.com', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, email, password) VALUES (default, 'mason.porter', 'mason.porter@example.com', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, email, password) VALUES (default, 'alden.chen', 'alden.chen@example.com', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');
INSERT INTO users (user_id, username, email, password) VALUES (default, 'colton.hodges', 'colton.hodges@example.com', '$2a$12$8LegtLQWe717tIPvZeivjuqKnaAs5.bm0Q05.5GrAmcKzXw2NjoUO');

-- Relacionar roles con usuarios (por defecto, ROLE_USER)
INSERT INTO roles_users (role_id, user_id) VALUES (1, 1); -- ROLE_USER para el usuario
INSERT INTO roles_users (role_id, user_id) VALUES (2, 2); -- ROLE_ADMIN para el admin
INSERT INTO roles_users (role_id, user_id) VALUES (1, 3); -- ROLE_USER para Dakota Rice
INSERT INTO roles_users (role_id, user_id) VALUES (1, 4); -- ROLE_USER para Minerva Hooper
INSERT INTO roles_users (role_id, user_id) VALUES (1, 5); -- ROLE_USER para Sage Rodriguez
INSERT INTO roles_users (role_id, user_id) VALUES (1, 6); -- ROLE_USER para Philip Chaney
INSERT INTO roles_users (role_id, user_id) VALUES (1, 7); -- ROLE_USER para Doris Greene
INSERT INTO roles_users (role_id, user_id) VALUES (1, 8); -- ROLE_USER para Mason Porter
INSERT INTO roles_users (role_id, user_id) VALUES (1, 9); -- ROLE_USER para Alden Chen
INSERT INTO roles_users (role_id, user_id) VALUES (1, 10); -- ROLE_USER para Colton Hodges

-- Insertar perfiles relacionados con los usuarios
INSERT INTO profiles (email, user_id) VALUES ('user@example.com', 1);
INSERT INTO profiles (email, user_id) VALUES ('admin@example.com', 2);
INSERT INTO profiles (email, user_id) VALUES ('dakota.rice@example.com', 3);
INSERT INTO profiles (email, user_id) VALUES ('minerva.hooper@example.com', 4);
INSERT INTO profiles (email, user_id) VALUES ('sage.rodriguez@example.com', 5);
INSERT INTO profiles (email, user_id) VALUES ('philip.chaney@example.com', 6);
INSERT INTO profiles (email, user_id) VALUES ('doris.greene@example.com', 7);
INSERT INTO profiles (email, user_id) VALUES ('mason.porter@example.com', 8);
INSERT INTO profiles (email, user_id) VALUES ('alden.chen@example.com', 9);
INSERT INTO profiles (email, user_id) VALUES ('colton.hodges@example.com', 10);
