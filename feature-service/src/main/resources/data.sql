-- Deleting all data

DELETE FROM aeesfeature."user";
DELETE FROM aeesfeature."education";
DELETE FROM aeesfeature."experience";
DELETE FROM aeesfeature."skill";
DELETE FROM aeesfeature."files";

-- Adding users

INSERT INTO aeesfeature."user" (id, uuid, email)
VALUES (50, 'uuid1', 'eleka1@etf.unsa.ba');
INSERT INTO aeesfeature."user" (id, uuid, email)
VALUES (51, 'uuid2', 'epanjeta1@etf.unsa.ba');
INSERT INTO aeesfeature."user" (id, uuid, email)
VALUES (52, 'uuid3', 'abegovic6@etf.unsa.ba');
INSERT INTO aeesfeature."user" (id, uuid, email)
VALUES (53, 'uuid4', 'skaleta1@etf.unsa.ba');

-- Adding education

INSERT INTO aeesfeature."education" (id, user_id, title, description, starting_month, starting_year, end_month, end_year)
VALUES (DEFAULT, 50, 'Education1', 'Description1', 1, 2022, 12, 2022);
INSERT INTO aeesfeature."education" (id, user_id, title, description, starting_month, starting_year, end_month, end_year)
VALUES (DEFAULT, 51, 'Education2', 'Description2', 1, 2022, 12, 2022);

-- Adding skills

INSERT INTO aeesfeature."skill" (id, user_id, title)
VALUES (DEFAULT, 50, 'Skill1');
INSERT INTO aeesfeature."skill" (id, user_id, title)
VALUES (DEFAULT, 51, 'Skill2');


-- Adding education

INSERT INTO aeesfeature."experience" (id, user_id, title, description, starting_month, starting_year, end_month, end_year)
VALUES (DEFAULT, 50, 'Experience1', 'Description1', 1, 2022, 12, 2022);
INSERT INTO aeesfeature."experience" (id, user_id, title, description, starting_month, starting_year, end_month, end_year)
VALUES (DEFAULT, 51, 'Experience2', 'Description2', 1, 2022, 12, 2022);





