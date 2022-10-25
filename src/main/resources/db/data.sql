


DELETE FROM admins WHERE TRUE;
DELETE FROM third_parties WHERE TRUE;
DELETE FROM account_holders WHERE TRUE;

DELETE FROM addresses WHERE TRUE;

DELETE FROM users WHERE TRUE;

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
INSERT INTO users (NAME) VALUES ('John');
INSERT INTO users (NAME) VALUES ('Alfredo');
INSERT INTO users (NAME) VALUES ('Cristian');
INSERT INTO users (NAME) VALUES ('Raúl');

-- insercion de usuarios 'ThirdParty'
INSERT INTO users (NAME) VALUES ('Alberto');
INSERT INTO users (NAME) VALUES ('Bernardo');
INSERT INTO users (NAME) VALUES ('Carlos');
INSERT INTO users (NAME) VALUES ('Daniel');
INSERT INTO users (NAME) VALUES ('Ernesto');
INSERT INTO users (NAME) VALUES ('Fernando');
INSERT INTO users (NAME) VALUES ('Gladys');
INSERT INTO users (NAME) VALUES ('Hernan');
INSERT INTO users (NAME) VALUES ('Ivan');
INSERT INTO users (NAME) VALUES ('Jose');
INSERT INTO users (NAME) VALUES ('Kevin');
INSERT INTO users (NAME) VALUES ('Liliana');
INSERT INTO users (NAME) VALUES ('Maria');
INSERT INTO users (NAME) VALUES ('Neron');
INSERT INTO users (NAME) VALUES ('Orlando');
INSERT INTO users (NAME) VALUES ('Pedro');
INSERT INTO users (NAME) VALUES ('Quentin');
INSERT INTO users (NAME) VALUES ('Ramon');
INSERT INTO users (NAME) VALUES ('Samuel');
INSERT INTO users (NAME) VALUES ('Tomas');
INSERT INTO users (NAME) VALUES ('Ubaldo');
INSERT INTO users (NAME) VALUES ('Veronica');
INSERT INTO users (NAME) VALUES ('Wendy');
INSERT INTO users (NAME) VALUES ('Xochil');
INSERT INTO users (NAME) VALUES ('Yolanda');
INSERT INTO users (NAME) VALUES ('Zoe');



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
INSERT INTO account_holders (user_id, birth_date, address_address_id, mailing_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Alberto') , '1960-02-24', (SELECT address_id FROM addresses WHERE street LIKE 'Numancia' AND house_number LIKE '23') , null);
INSERT INTO account_holders (user_id, birth_date, address_address_id, mailing_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Bernardo') , '1990-03-23', (SELECT address_id FROM addresses WHERE street LIKE 'Gran Via' AND house_number LIKE '50') , null);
INSERT INTO account_holders (user_id, birth_date, address_address_id, mailing_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Carlos') , '2000-12-12', (SELECT address_id FROM addresses WHERE street LIKE 'Napoles' AND house_number LIKE '12') , null);
INSERT INTO account_holders (user_id, birth_date, address_address_id, mailing_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Daniel') , '1998-09-20', (SELECT address_id FROM addresses WHERE street LIKE 'Dénia' AND house_number LIKE '54') , null);
INSERT INTO account_holders (user_id, birth_date, address_address_id, mailing_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Ernesto') , '1997-12-30', (SELECT address_id FROM addresses WHERE street LIKE 'San Blas' AND house_number LIKE '26') , null);
INSERT INTO account_holders (user_id, birth_date, address_address_id, mailing_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Fernando') , '1992-06-10', (SELECT address_id FROM addresses WHERE street LIKE 'Rivolí' AND house_number LIKE '11') , null);
INSERT INTO account_holders (user_id, birth_date, address_address_id, mailing_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Gladys') , '2001-10-05', (SELECT address_id FROM addresses WHERE street LIKE 'Oberweg' AND house_number LIKE '69') , null);
INSERT INTO account_holders (user_id, birth_date, address_address_id, mailing_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Hernan') , '1997-04-05', (SELECT address_id FROM addresses WHERE street LIKE 'Av El Dorado' AND house_number LIKE '89') , null);
INSERT INTO account_holders (user_id, birth_date, address_address_id, mailing_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Ivan') , '1975-09-13', (SELECT address_id FROM addresses WHERE street LIKE 'Jirón Callao' AND house_number LIKE '24') , null);
INSERT INTO account_holders (user_id, birth_date, address_address_id, mailing_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Jose') , '1984-09-28', (SELECT address_id FROM addresses WHERE street LIKE 'Av 9 de Julio' AND house_number LIKE '48') , null);
INSERT INTO account_holders (user_id, birth_date, address_address_id, mailing_address_address_id) VALUES ((SELECT user_id FROM  users WHERE NAME LIKE 'Kevin') , '1982-12-02', (SELECT address_id FROM addresses WHERE street LIKE 'Paseo de Montejo' AND house_number LIKE '33') , null);


