truncate recommendation.user cascade;
truncate recommendation.job cascade;

INSERT INTO recommendation.user (id, uuid, email, name) VALUES (1, '111', 'senija.kaleta@gmail.com', 'Senija');
INSERT INTO recommendation.user (id, uuid, email, name) VALUES (2, '222', 'emina.kaleta@gmail.com', 'Emina');
INSERT INTO recommendation.user (id, uuid, email, name) VALUES (3, '333', 'rasim.kaleta@gmail.com', 'Rasim');

INSERT INTO recommendation.job (id, description, name, user_id) VALUES (1, 'opis posla 1', 'programer', 1);
INSERT INTO recommendation.job (id, description, name, user_id) VALUES (2, 'opis posla 2', 'doktor', 2);
INSERT INTO recommendation.job (id, description, name, user_id) VALUES (3, 'opis posla 3', 'inzenjer', 3);