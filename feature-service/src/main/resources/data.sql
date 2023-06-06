-- Deleting all data

DELETE FROM aeesfeature."user";
DELETE FROM aeesfeature."education";
DELETE FROM aeesfeature."experience";
DELETE FROM aeesfeature."skill";
DELETE FROM aeesfeature."files";

-- Adding users

INSERT INTO aeesfeature."user" (uuid, email)
VALUES ( '2222', 'eleka1@etf.unsa.ba');
INSERT INTO aeesfeature."user" ( uuid, email)
VALUES ('4444', 'epanjeta1@etf.unsa.ba');
INSERT INTO aeesfeature."user" ( uuid, email)
VALUES ('1111', 'abegovic6@etf.unsa.ba');
INSERT INTO aeesfeature."user" ( uuid, email)
VALUES ('3333', 'skaleta1@etf.unsa.ba');

-- Adding education

INSERT INTO aeesfeature."education" (user_id, title, description, starting_month, starting_year, end_month, end_year)
VALUES ( 3, 'Education1', 'Description1', 1, 2022, 12, 2022);
INSERT INTO aeesfeature."education" ( user_id, title, description, starting_month, starting_year, end_month, end_year)
VALUES ( 3, 'Education2', 'Description2', 1, 2022, 12, 2022);

-- Adding skills

INSERT INTO aeesfeature."skill" (user_id, title)
VALUES (3, 'Skill1');
INSERT INTO aeesfeature."skill" (user_id, title)
VALUES ( 3, 'Skill2');


-- Adding education

INSERT INTO aeesfeature."experience" (user_id, title, description, starting_month, starting_year, end_month, end_year)
VALUES ( 3, 'Experience1', 'Description1', 1, 2022, 12, 2022);
INSERT INTO aeesfeature."experience" (user_id, title, description, starting_month, starting_year, end_month, end_year)
VALUES ( 3, 'Experience2', 'Description2', 1, 2022, 12, 2022);





