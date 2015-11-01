INSERT INTO Joke (id, joke, added) VALUES (1, 'yo moma so fat', '1999-02-02 06:00:01');
INSERT INTO Joke (id, joke, added) VALUES (2, 'yo papa so fat', '1999-02-02 06:00:01');

INSERT INTO User (id, email, password, created) VALUES (1, 'admin@admin.kz', 'kaarliema', '1999-02-02 06:00:01');
INSERT INTO User (id, email, password, created) VALUES (2, 'user@user.kz', 'siimuema', '1999-02-02 06:00:01');

INSERT INTO AuthenticatedUser (id, user_id, token) VALUES (1, 1, 'superUniqueToken');
