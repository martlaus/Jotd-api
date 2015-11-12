INSERT INTO Joke (id, joke, added, upvotes, downvotes) VALUES (1, 'yo moma so fat', '1999-02-02 06:00:01', 0, 0);
INSERT INTO Joke (id, joke, added, upvotes, downvotes) VALUES (2, 'yo papa so fat', '1999-02-02 06:00:01', 0, 0);

INSERT INTO User (id, email, password, created, role) VALUES (1, 'admin@admin.kz', 'kaarliema', '1999-02-02 06:00:01', 'USER');
INSERT INTO User (id, email, password, created, role) VALUES (2, 'user@user.kz', 'siimuema', '1999-02-02 06:00:01', 'USER');
INSERT INTO User (id, email, password, created, role) VALUES (3, 'mart@mart.kz', '$2a$10$R.coZxN.slwkXJ2KxEsa4ufhqk8sGbWha/Me5OmXTYEQgL0XEi6OK', '1999-02-02 06:00:01', 'USER');

INSERT INTO AuthenticatedUser (id, user_id, token) VALUES (1, 1, 'superUniqueToken');

INSERT INTO Vote (id, user_id, joke_id, isUpvote) VALUES (1, 1, 1, TRUE);
-- INSERT INTO Vote (id, user_id, joke_id, isUpvote) VALUES (2, 1, 1, FALSE);


