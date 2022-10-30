

DELETE FROM roles WHERE TRUE;
DELETE FROM user_roles WHERE TRUE;
DELETE FROM admins WHERE TRUE;
DELETE FROM third_parties WHERE TRUE;
DELETE FROM account_holders WHERE TRUE;

DELETE FROM addresses WHERE TRUE;

DELETE FROM users WHERE TRUE;

INSERT INTO roles (role_name) VALUES ('ROLE_ADMIN'),
                                ('ROLE_USER'),
                                ('ROLE_THIRDPARTY');

-- Insercion de direcciones
INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('España', 'Barcelona', 'Numancia', '23', '4-2', '' );
INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('España', 'Madrid', 'Gran Via', '50', '3-1', '' );
INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('España', 'Barcelona', 'Napoles', '12', 'Bajos 3', '' );
INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('España', 'Valencia', 'Dénia', '54', 'Atico', '' );
INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('España', 'Badajoz', 'San Blas', '26', '2-1', '' );
INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('Francia', 'Paris', 'Rivolí', '11', '3-3', '' );
INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('Alemania', 'Frankfurt', 'Oberweg', '69', '3 ', '' );
INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('Colombia', 'Bogotá', 'Av El Dorado', '89', 'apto 3', '' );
INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('Peru', 'Lima', 'Jirón Callao', '24', 'A ', '' );
INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('Argentina', 'Buenos Aires', 'Av 9 de Julio', '48', 'Apto 2', '' );
INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('Mexico', 'Merida', 'Paseo de Montejo', '33', 'Casa Azul', 'Al lado del monumento' );

-- insercion de usuarios Administradores
INSERT INTO users (name, username, email, password) VALUES ('John', 'john', 'john@email.com', '$2a$10$EmDCvndz9V.qV4s48TCXYOplNJc54si3hM2yby.stIO3ETgMoWFbS');
INSERT INTO users (name, username, email, password) VALUES ('Alfredo', 'alfredo', 'alfredo@email.com', '$2a$10$EmDCvndz9V.qV4s48TCXYOplNJc54si3hM2yby.stIO3ETgMoWFbS');
INSERT INTO users (name, username, email, password) VALUES ('Cristian', 'cristian', 'cristian@email.com', '$2a$10$EmDCvndz9V.qV4s48TCXYOplNJc54si3hM2yby.stIO3ETgMoWFbS');
INSERT INTO users (name, username, email, password) VALUES ('Raúl', 'raúl', 'raúl@email.com', '$2a$10$EmDCvndz9V.qV4s48TCXYOplNJc54si3hM2yby.stIO3ETgMoWFbS');


-- insercion de usuarios 'Users'
INSERT INTO users (name, username, email, password) VALUES ('Alberto', 'alberto', 'alberto@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Bernardo', 'bernardo', 'bernardo@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Carlos', 'carlos', 'carlos@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Daniel', 'daniel', 'daniel@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Ernesto', 'ernesto', 'ernesto@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Fernando', 'fernando', 'fernando@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Gladys', 'gladys', 'gladys@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Hernan', 'hernan', 'hernan@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Ivan', 'ivan', 'ivan@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Jose', 'jose', 'jose@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Kevin', 'kevin', 'kevin@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Liliana', 'liliana', 'liliana@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Maria', 'maria', 'maria@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Nancy', 'nancy', 'nancy@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Orlando', 'orlando', 'orlando@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Pedro', 'pedro', 'pedro@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Quentin', 'quentin', 'quentin@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Ramon', 'ramon', 'ramon@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Samuel', 'samuel', 'samuel@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Tomas', 'tomas', 'tomas@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Ubaldo', 'ubaldo', 'ubaldo@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Veronica', 'veronica', 'veronica@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Wendy', 'wendy', 'wendy@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Xochil', 'xochil', 'xochil@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Yolanda', 'yolanda', 'yolanda@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');
INSERT INTO users (name, username, email, password) VALUES ('Zoe', 'zoe', 'zoe@email.com', '$2a$10$5VmIyfjdIhdb6GKfIkxwSelOCbI516ZxAfOJic21gHk.KnWE8N.gK');


-- insercion y enlace de administradores con usuarios
INSERT INTO admins VALUES ((SELECT user_id FROM users WHERE NAME LIKE 'John') );
INSERT INTO admins VALUES ((SELECT user_id FROM users WHERE NAME LIKE 'Alfredo') );
INSERT INTO admins VALUES ((SELECT user_id FROM users WHERE NAME LIKE 'Cristian') );
INSERT INTO admins VALUES ((SELECT user_id FROM users WHERE NAME LIKE 'Raúl') );


-- insercion y enlace de entidades 'ThirdParty'
INSERT INTO third_parties (user_id, hashed_key) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Samuel') , 'ssss');
INSERT INTO third_parties (user_id, hashed_key) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Tomas') , 'tttt');
INSERT INTO third_parties (user_id, hashed_key) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Ubaldo') , 'uuuu');
INSERT INTO third_parties (user_id, hashed_key) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Veronica') , 'vvvv');
INSERT INTO third_parties (user_id, hashed_key) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Wendy') , 'wwww');
INSERT INTO third_parties (user_id, hashed_key) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Xochil') , 'xxxx');
INSERT INTO third_parties (user_id, hashed_key) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Yolanda') , 'yyyy');
INSERT INTO third_parties (user_id, hashed_key) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Zoe') , 'zzzz');


-- insercion y enlace de entidades 'AccountHolders'
INSERT INTO account_holders (user_id, birth_date, main_address_address_id, secondary_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Alberto') , '1960-02-24', (SELECT address_id FROM addresses WHERE street LIKE 'Numancia' AND house_number LIKE '23') , null);
INSERT INTO account_holders (user_id, birth_date, main_address_address_id, secondary_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Bernardo') , '1990-03-23', (SELECT address_id FROM addresses WHERE street LIKE 'Gran Via' AND house_number LIKE '50') , null);
INSERT INTO account_holders (user_id, birth_date, main_address_address_id, secondary_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Carlos') , '2000-12-12', (SELECT address_id FROM addresses WHERE street LIKE 'Napoles' AND house_number LIKE '12') , null);
INSERT INTO account_holders (user_id, birth_date, main_address_address_id, secondary_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Daniel') , '1998-09-20', (SELECT address_id FROM addresses WHERE street LIKE 'Dénia' AND house_number LIKE '54') , null);
INSERT INTO account_holders (user_id, birth_date, main_address_address_id, secondary_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Ernesto') , '1997-12-30', (SELECT address_id FROM addresses WHERE street LIKE 'San Blas' AND house_number LIKE '26') , null);
INSERT INTO account_holders (user_id, birth_date, main_address_address_id, secondary_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Fernando') , '1992-06-10', (SELECT address_id FROM addresses WHERE street LIKE 'Rivolí' AND house_number LIKE '11') , null);
INSERT INTO account_holders (user_id, birth_date, main_address_address_id, secondary_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Gladys') , '2001-10-05', (SELECT address_id FROM addresses WHERE street LIKE 'Oberweg' AND house_number LIKE '69') , null);
INSERT INTO account_holders (user_id, birth_date, main_address_address_id, secondary_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Hernan') , '1997-04-05', (SELECT address_id FROM addresses WHERE street LIKE 'Av El Dorado' AND house_number LIKE '89') , null);
INSERT INTO account_holders (user_id, birth_date, main_address_address_id, secondary_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Ivan') , '1975-09-13', (SELECT address_id FROM addresses WHERE street LIKE 'Jirón Callao' AND house_number LIKE '24') , null);
INSERT INTO account_holders (user_id, birth_date, main_address_address_id, secondary_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Jose') , '1984-09-28', (SELECT address_id FROM addresses WHERE street LIKE 'Av 9 de Julio' AND house_number LIKE '48') , null);
INSERT INTO account_holders (user_id, birth_date, main_address_address_id, secondary_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Kevin') , '1982-12-02', (SELECT address_id FROM addresses WHERE street LIKE 'Paseo de Montejo' AND house_number LIKE '33') , null);


-- insercion en user_roles para enlazar administradores con sus roles de Admin
INSERT INTO user_roles (user_id, role_id) VALUES(( (SELECT user_id FROM users WHERE NAME LIKE 'John')  ), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_ADMIN' ) );
INSERT INTO user_roles (user_id, role_id) VALUES(( (SELECT user_id FROM users WHERE NAME LIKE 'Alfredo')  ), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_ADMIN' ) );
INSERT INTO user_roles (user_id, role_id) VALUES(( (SELECT user_id FROM users WHERE NAME LIKE 'Cristian')  ), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_ADMIN' ) );
INSERT INTO user_roles (user_id, role_id) VALUES(( (SELECT user_id FROM users WHERE NAME LIKE 'Raúl')  ), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_ADMIN' ) );


-- insercion de Roles para usuarios 'AccountHolders'
INSERT INTO user_roles (user_id, role_id) VALUES( (SELECT user_id FROM users WHERE NAME LIKE 'Alberto'), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_USER') );
INSERT INTO user_roles (user_id, role_id) VALUES( (SELECT user_id FROM users WHERE NAME LIKE 'Bernardo'), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_USER') );
INSERT INTO user_roles (user_id, role_id) VALUES( (SELECT user_id FROM users WHERE NAME LIKE 'Carlos'), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_USER') );
INSERT INTO user_roles (user_id, role_id) VALUES( (SELECT user_id FROM users WHERE NAME LIKE 'Daniel'), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_USER') );
INSERT INTO user_roles (user_id, role_id) VALUES( (SELECT user_id FROM users WHERE NAME LIKE 'Ernesto'), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_USER') );
INSERT INTO user_roles (user_id, role_id) VALUES( (SELECT user_id FROM users WHERE NAME LIKE 'Fernando'), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_USER') );
INSERT INTO user_roles (user_id, role_id) VALUES( (SELECT user_id FROM users WHERE NAME LIKE 'Gladys'), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_USER') );
INSERT INTO user_roles (user_id, role_id) VALUES( (SELECT user_id FROM users WHERE NAME LIKE 'Hernan'), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_USER') );
INSERT INTO user_roles (user_id, role_id) VALUES( (SELECT user_id FROM users WHERE NAME LIKE 'Ivan'), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_USER') );
INSERT INTO user_roles (user_id, role_id) VALUES( (SELECT user_id FROM users WHERE NAME LIKE 'Jose'), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_USER') );
INSERT INTO user_roles (user_id, role_id) VALUES( (SELECT user_id FROM users WHERE NAME LIKE 'Kevin'), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_USER') );


-- insercion de Roles para usuarios 'ThirdParty'
INSERT INTO user_roles (user_id, role_id) VALUES( (SELECT user_id FROM users WHERE NAME LIKE 'Samuel'), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_THIRDPARTY') );
INSERT INTO user_roles (user_id, role_id) VALUES( (SELECT user_id FROM users WHERE NAME LIKE 'Tomas'), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_THIRDPARTY') );
INSERT INTO user_roles (user_id, role_id) VALUES( (SELECT user_id FROM users WHERE NAME LIKE 'Ubaldo'), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_THIRDPARTY') );
INSERT INTO user_roles (user_id, role_id) VALUES( (SELECT user_id FROM users WHERE NAME LIKE 'Veronica'), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_THIRDPARTY') );
INSERT INTO user_roles (user_id, role_id) VALUES( (SELECT user_id FROM users WHERE NAME LIKE 'Wendy'), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_THIRDPARTY') );
INSERT INTO user_roles (user_id, role_id) VALUES( (SELECT user_id FROM users WHERE NAME LIKE 'Xochil'), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_THIRDPARTY') );
INSERT INTO user_roles (user_id, role_id) VALUES( (SELECT user_id FROM users WHERE NAME LIKE 'Yolanda'), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_THIRDPARTY') );
INSERT INTO user_roles (user_id, role_id) VALUES( (SELECT user_id FROM users WHERE NAME LIKE 'Zoe'), ( SELECT ID FROM roles WHERE role_name LIKE 'ROLE_THIRDPARTY') );
