

DELETE FROM addresses WHERE TRUE;

DELETE FROM users WHERE TRUE;


INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('España', 'Barcelona', 'Numancia', '23', '4-2', '' );
INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('España', 'Madrid', 'Gran Via', '50', '3-1', '' );
INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('España', 'Barcelona', 'Napoles', '12', 'Bajos 3', '' );
INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('España', 'Valencia', 'Dénia', '54', 'Atico', '' );
INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('España', 'Badajoz', 'San Blas', '26', '2-1', '' );
INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('Francia', 'Paris', 'Rivolí', '11', '3-3', '' );
INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('Alemania', 'Frankfurt', 'Oberweg', '69', '3 ', '' );
INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('Colombia', 'Bogotá', 'Av El Dorado', '89', '', '' );
INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('Peru', 'Lima', 'Jirón Callao', '24', 'A ', '' );
INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('Argentina', 'Buenos Aires', 'Av 9 de Julio', '48', 'Apto 2', '' );
INSERT INTO addresses (country, city, street, house_number, home_unit, COMMENT)   VALUES ('Mexico', 'Merida', 'Paseo de Montejo', '33', 'Casa Azul', 'Al lado del monumento' );


INSERT INTO users (NAME) VALUES ('John');
INSERT INTO users (NAME) VALUES ('Alfredo');
INSERT INTO users (NAME) VALUES ('Cristian');
INSERT INTO users (NAME) VALUES ('Raúl');


