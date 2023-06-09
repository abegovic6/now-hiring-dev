-- Deleting all data

-- DELETE FROM aeesjobs."user";
-- DELETE FROM aeesjobs."job";
-- DELETE FROM aeesjobs."application";
-- DELETE FROM aeesjobs."job2application";

-- Adding users

INSERT INTO aeesjobs."user" (company_name, description, e_mail, first_name, last_name, location, uid, user_type)
VALUES ( 'Google', 'Opis programerske firme', 'begovicami5@gmail.com', null, null, 'Los Angeles',
        '5555', 'COMPANY');
-- INSERT INTO aeesjobs."user" ( company_name, description, e_mail, first_name, last_name, location, uid, user_type)
-- VALUES ( 'Dizajnerska firma', 'Opis dizajnerske firme', 'firma2@gmail.com', null, null, 'Travnik',
--         'Sifra0002', 'COMPANY');

INSERT INTO aeesjobs."user" ( company_name, description, e_mail, first_name, last_name, location, uid, user_type)
VALUES ( null, 'Opis profila1', 'epanjeta1@gmail.com', 'Eldar', 'Panjeta', 'Sarajevo', '4444', 'PRIVATE');
INSERT INTO aeesjobs."user" ( company_name, description, e_mail, first_name, last_name, location, uid, user_type)
VALUES ( null, 'Opis profila2', 'abegovic6@gmail.com', 'Amila', 'Begovic', 'Donji Vakuf', '1111', 'PRIVATE');
INSERT INTO aeesjobs."user" ( company_name, description, e_mail, first_name, last_name, location, uid, user_type)
VALUES ( null, 'Opis profila3', 'eleka1@gmail.com', 'Elma', 'Polutan', 'Sarajevo', '2222', 'PRIVATE');
INSERT INTO aeesjobs."user" ( company_name, description, e_mail, first_name, last_name, location, uid, user_type)
VALUES ( null, 'Opis profila4', 'skaleta1@gmail.com', 'Senija', 'Kaleta', 'Sarajevo', '3333', 'PRIVATE');

-- Adding jobs

INSERT INTO aeesjobs.job ( company_id, description, location, title, type, valid)
VALUES ( '5555', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate vel...', 'Sarajevo', 'Programer', 'IT', '2024-04-01');
INSERT INTO aeesjobs.job ( company_id, description, location, title, type, valid)
VALUES ( '5555', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate vel...', 'Sarajevo', 'Programer Senior', 'IT', '2024-04-01');
INSERT INTO aeesjobs.job ( company_id, description, location, title, type, valid)
VALUES ( '5555', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate vel...', 'Remote', 'Backend programer', 'IT', '2024-04-01');
INSERT INTO aeesjobs.job ( company_id, description, location, title, type, valid)
VALUES ( '5555', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate vel...', 'Mostar', 'Web app designer', 'IT', '2024-04-01');
-- INSERT INTO aeesjobs.job ( company_id, description, location, title, type, valid)
-- VALUES ( 'Sifra0002', 'Istekao posao', 'Mostar', 'Web app designer', 'IT', '2022-04-01');

-- Adding applications

INSERT INTO aeesjobs.application ( coverletter, useruid)
VALUES ( 'COVER LETTER 1', '1111');
INSERT INTO aeesjobs.job2application ( applicationid, jobid)
VALUES ( 1, 1);

INSERT INTO aeesjobs.application ( coverletter, useruid)
VALUES ('COVER LETTER 2', '1111');
INSERT INTO aeesjobs.job2application ( applicationid, jobid)
VALUES ( 2, 2);

INSERT INTO aeesjobs.application ( coverletter, useruid)
VALUES ( 'COVER LETTER 3', '2222');
INSERT INTO aeesjobs.job2application ( applicationid, jobid)
VALUES ( 3, 1);












