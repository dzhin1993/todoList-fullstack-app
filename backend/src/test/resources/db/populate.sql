DELETE FROM user;

INSERT INTO user VALUES (1, 'test@mail.ru', '{noop}password');
INSERT INTO user VALUES (2, 'test2@mail.ru', '{noop}password2');

INSERT INTO task VALUES (1, 'todo1', '2021-01-30 10:00:00', FALSE, 1);
INSERT INTO task VALUES (2, 'todo2', '2021-01-30 11:00:00', FALSE, 1);
INSERT INTO task VALUES (3, 'todo3', '2021-01-30 12:00:00', TRUE, 1);
INSERT INTO task VALUES (4, 'todo3', '2021-01-30 13:00:00', TRUE, 2);
INSERT INTO task VALUES (5, 'todo4', '2021-01-30 14:00:00', TRUE, 2);
INSERT INTO task VALUES (6, 'todo5', '2021-01-30 15:00:00', FALSE, 2);