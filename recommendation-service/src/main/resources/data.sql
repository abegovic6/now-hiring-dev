truncate recommendation.user cascade;
truncate recommendation.job cascade;

INSERT INTO recommendation.user (id, uuid, email, name) VALUES (default, '111', 'senija.kaleta@gmail.com', 'Senija');
INSERT INTO recommendation.user (id, uuid, email, name) VALUES (default, '222', 'emina.kaleta@gmail.com', 'Emina');
INSERT INTO recommendation.user (id, uuid, email, name) VALUES (default, '333', 'rasim.kaleta@gmail.com', 'Rasim');
INSERT INTO recommendation.user (id, uuid, email, name) VALUES (default, '1111', 'abegovic6@etf.unsa.ba', 'Amila Begović');
INSERT INTO recommendation.user (id, uuid, email, name) VALUES (default, '2222', 'eleka1@etf.unsa.ba', 'Elma Leka');
INSERT INTO recommendation.user (id, uuid, email, name) VALUES (default, '3333', 'skaleta1@etf.unsa.ba', 'Senija Kaleta');
INSERT INTO recommendation.user (id, uuid, email, name) VALUES (default, '4444', 'epanjeta1@etf.unsa.ba', 'Eldar Panjeta');
INSERT INTO recommendation.user (id, uuid, email, name) VALUES (default, '5555', 'begovicami5@gmail.com', 'Google');

INSERT INTO recommendation.job (id, description, name, user_id) VALUES (default, 'opis posla 1', 'programer', 1);
INSERT INTO recommendation.job (id, description, name, user_id) VALUES (default, 'opis posla 2', 'doktor', 2);
INSERT INTO recommendation.job (id, description, name, user_id) VALUES (default, 'opis posla 3', 'inzenjer', 3);

INSERT INTO recommendation.review (id, user_creator, user_reciever, comment, number) VALUES (default, 5, 4, 'vrlo dobar korisnik', 4.9);
INSERT INTO recommendation.review (id, user_creator, user_reciever, comment, number) VALUES (default, 7, 4, 'dobar korisnik', 3.7);
