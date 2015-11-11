INSERT INTO Joke (id, joke, added) VALUES (1, 'yo moma so fat', '1999-02-02 06:00:01');
INSERT INTO Joke (id, joke, added) VALUES (2, 'yo papa so fat', '1999-02-02 06:00:01');

INSERT INTO User (id, email, password, created) VALUES (1, 'admin@admin.kz', 'kaarliema', '1999-02-02 06:00:01');
INSERT INTO User (id, email, password, created) VALUES (2, 'user@user.kz', 'siimuema', '1999-02-02 06:00:01');
INSERT INTO User (id, email, password, role, created) VALUES
  (3, 'mart@mart.kz', '$2a$10$R.coZxN.slwkXJ2KxEsa4ufhqk8sGbWha/Me5OmXTYEQgL0XEi6OK', 'USER', '1999-02-02 06:00:01');


INSERT INTO AuthenticatedUser (id, user_id, token) VALUES (1, 1, 'superUniqueToken');

INSERT INTO Vote (id, user_id, joke_id, isUpvote) VALUES (1, 1, 1, TRUE);

