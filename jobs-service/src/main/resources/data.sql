-- Deleting all data

DELETE FROM aeesjobs."user";
DELETE FROM aeesjobs."job";
DELETE FROM aeesjobs."application";
DELETE FROM aeesjobs."job2application";

-- Adding users

INSERT INTO aeesjobs."user" (id, company_name, description, e_mail, first_name, last_name, location, uid, user_type)
VALUES (DEFAULT, 'Programerska firma', 'Opis programerske firme', 'firma1@gmail.com', null, null, 'Sarajevo',
        'Sifra0001', 'COMPANY');
INSERT INTO aeesjobs."user" (id, company_name, description, e_mail, first_name, last_name, location, uid, user_type)
VALUES (DEFAULT, 'Dizajnerska firma', 'Opis dizajnerske firme', 'firma2@gmail.com', null, null, 'Travnik',
        'Sifra0002', 'COMPANY');

INSERT INTO aeesjobs."user" (id, company_name, description, e_mail, first_name, last_name, location, uid, user_type)
VALUES (DEFAULT, null, 'Opis profila1', 'epanjeta@gmail.com', 'Eldar', 'Panjeta', 'Sarajevo', 'Sifra0003', 'PRIVATE');
INSERT INTO aeesjobs."user" (id, company_name, description, e_mail, first_name, last_name, location, uid, user_type)
VALUES (DEFAULT, null, 'Opis profila2', 'abegovic@gmail.com', 'Amila', 'Begovic', 'Donji Vakuf', 'Sifra0004', 'PRIVATE');
INSERT INTO aeesjobs."user" (id, company_name, description, e_mail, first_name, last_name, location, uid, user_type)
VALUES (DEFAULT, null, 'Opis profila3', 'epolutan@gmail.com', 'Elma', 'Polutan', 'Hrasnica', 'Sifra0005', 'PRIVATE');
INSERT INTO aeesjobs."user" (id, company_name, description, e_mail, first_name, last_name, location, uid, user_type)
VALUES (DEFAULT, null, 'Opis profila4', 'skaleta@gmail.com', 'Senija', 'Kaleta', 'Cwilla', 'Sifra0006', 'PRIVATE');

-- Adding jobs

INSERT INTO aeesjobs.job (id, company_id, description, location, title, type, valid)
VALUES (DEFAULT, 'Sifra0001', 'Opis posla 1', 'Sarajevo', 'Programer', 'IT', '2024-04-01');
INSERT INTO aeesjobs.job (id, company_id, description, location, title, type, valid)
VALUES (DEFAULT, 'Sifra0001', 'Opis posla 2', 'Sarajevo', 'Programer Senior', 'IT', '2024-04-01');
INSERT INTO aeesjobs.job (id, company_id, description, location, title, type, valid)
VALUES (DEFAULT, 'Sifra0001', 'Opis posla 3', 'Remote', 'Backend programer', 'IT', '2024-04-01');
INSERT INTO aeesjobs.job (id, company_id, description, location, title, type, valid)
VALUES (DEFAULT, 'Sifra0002', 'Dizajnerski posao', 'Mostar', 'Web app designer', 'IT', '2024-04-01');
INSERT INTO aeesjobs.job (id, company_id, description, location, title, type, valid)
VALUES (DEFAULT, 'Sifra0002', 'Istekao posao', 'Mostar', 'Web app designer', 'IT', '2022-04-01');









