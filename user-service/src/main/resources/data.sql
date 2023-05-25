-- truncate all tables
truncate aeesuser.location cascade;
truncate aeesuser."user" cascade;

-- insert into example
INSERT INTO aeesuser.example (id, text) VALUES (1, 'The API is running');

-- insert into location
INSERT INTO aeesuser.location (city, country, creation_ts, modification_ts) VALUES ('Sarajevo', 'Bosnia and Herzegovina', '2023-04-01 16:02:20.000000', '2023-04-01 16:02:22.000000');
INSERT INTO aeesuser.location (city, country, creation_ts, modification_ts) VALUES ('Banja Luka', 'Bosnia and Herzegovina', '2023-04-01 16:02:20.000000', '2023-04-01 16:02:22.000000');
INSERT INTO aeesuser.location (city, country, creation_ts, modification_ts) VALUES ('Tuzla', 'Bosnia and Herzegovina', '2023-04-01 16:02:20.000000', '2023-04-01 16:02:22.000000');
INSERT INTO aeesuser.location (city, country, creation_ts, modification_ts) VALUES ('Mostar', 'Bosnia and Herzegovina', '2023-04-01 16:02:20.000000', '2023-04-01 16:02:22.000000');
INSERT INTO aeesuser.location (city, country, creation_ts, modification_ts) VALUES ('Travnik', 'Bosnia and Herzegovina', '2023-04-01 16:02:20.000000', '2023-04-01 16:02:22.000000');
INSERT INTO aeesuser.location (city, country, creation_ts, modification_ts) VALUES ('Donji Vakuf', 'Bosnia and Herzegovina', '2023-04-01 16:02:20.000000', '2023-04-01 16:02:22.000000');
INSERT INTO aeesuser.location (city, country, creation_ts, modification_ts) VALUES ('Zenica', 'Bosnia and Herzegovina', '2023-04-01 16:02:20.000000', '2023-04-01 16:02:22.000000');
INSERT INTO aeesuser.location (city, country, creation_ts, modification_ts) VALUES ('New York', 'United States of America', '2023-04-01 16:02:20.000000', '2023-04-01 16:02:22.000000');
INSERT INTO aeesuser.location (city, country, creation_ts, modification_ts) VALUES ('Los Angeles', 'United States of America', '2023-04-01 16:02:20.000000', '2023-04-01 16:02:22.000000');
INSERT INTO aeesuser.location (city, country, creation_ts, modification_ts) VALUES ('Boston', 'United States of America', '2023-04-01 16:02:20.000000', '2023-04-01 16:02:22.000000');
INSERT INTO aeesuser.location (city, country, creation_ts, modification_ts) VALUES ('Chicago', 'United States of America', '2023-04-01 16:02:20.000000', '2023-04-01 16:02:22.000000');
INSERT INTO aeesuser.location (city, country, creation_ts, modification_ts) VALUES ('Berlin', 'Germany', '2023-04-01 16:02:20.000000', '2023-04-01 16:02:22.000000');
INSERT INTO aeesuser.location (city, country, creation_ts, modification_ts) VALUES ('Munich', 'Germany', '2023-04-01 16:02:20.000000', '2023-04-01 16:02:22.000000');
INSERT INTO aeesuser.location (city, country, creation_ts, modification_ts) VALUES ('Dresden', 'Germany', '2023-04-01 16:02:20.000000', '2023-04-01 16:02:22.000000');
INSERT INTO aeesuser.location (city, country, creation_ts, modification_ts) VALUES ('Hamburg', 'Germany', '2023-04-01 16:02:20.000000', '2023-04-01 16:02:22.000000');
INSERT INTO aeesuser.location (city, country, creation_ts, modification_ts) VALUES ('Belgrade', 'Serbia', '2023-04-01 16:02:20.000000', '2023-04-01 16:02:22.000000');
INSERT INTO aeesuser.location (city, country, creation_ts, modification_ts) VALUES ('Zagreb', 'Croatia', '2023-04-01 16:02:20.000000', '2023-04-01 16:02:22.000000');
INSERT INTO aeesuser.location (city, country, creation_ts, modification_ts) VALUES ('Split', 'Croatia', '2023-04-01 16:02:20.000000', '2023-04-01 16:02:22.000000');
INSERT INTO aeesuser.location (city, country, creation_ts, modification_ts) VALUES ('Dubrovnik', 'Croatia', '2023-04-01 16:02:20.000000', '2023-04-01 16:02:22.000000');



-- insert into user
INSERT INTO aeesuser."user" (location_id, uuid, email, password, user_type, company_name, first_name, last_name, description, creation_ts, modification_ts, verified) VALUES (6, '1111', 'abegovic6@etf.unsa.ba', 'Sifra0001', 'PRIVATE', null, 'Amila', 'Begovic', 'Opis', now(), now(), true);
INSERT INTO aeesuser."user" (location_id, uuid, email, password, user_type, company_name, first_name, last_name, description, creation_ts, modification_ts, verified) VALUES (1, '2222', 'eleka1@etf.unsa.ba', 'Sifra0002', 'PRIVATE', null, 'Elma', 'Polutan', 'Opis', now(), now(), true);
INSERT INTO aeesuser."user" (location_id, uuid, email, password, user_type, company_name, first_name, last_name, description, creation_ts, modification_ts, verified) VALUES (1, '3333', 'skaleta1@etf.unsa.ba', 'Sifra0003', 'PRIVATE', null, 'Senija', 'Kaleta', 'Opis', now(), now(), true);
INSERT INTO aeesuser."user" (location_id, uuid, email, password, user_type, company_name, first_name, last_name, description, creation_ts, modification_ts, verified) VALUES (1, '4444', 'epanjeta1@etf.unsa.ba', 'Sifra0004', 'PRIVATE', null, 'Eldar', 'Panjeta', 'Opis', now(), now(), true);
INSERT INTO aeesuser."user" (location_id, uuid, email, password, user_type, company_name, first_name, last_name, description, creation_ts, modification_ts, verified) VALUES (9, '5555', 'begovicami5@gmail.com', 'Sifra0005', 'COMPANY', 'Google', null, null, 'Opis', now(), now(), true);

-- insert into connection
INSERT INTO aeesuser.connection (user_from, user_to, creation_ts, status, modification_ts) VALUES (1, 5, now(), 'ACCEPTED', now());

