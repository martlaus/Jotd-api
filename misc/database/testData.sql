USE jotd;

-- Add Users
INSERT INTO User (id, email, password, role, created)
VALUES (1, 'admin@admin.kz', '$2a$10$R.coZxN.slwkXJ2KxEsa4ufhqk8sGbWha/Me5OmXTYEQgL0XEi6OK', 'USER', NOW());
INSERT INTO User (id, email, password, role, created)
VALUES (2, 'user@user.kz', '$2a$10$R.coZxN.slwkXJ2KxEsa4ufhqk8sGbWha/Me5OmXTYEQgL0XEi6OK', 'USER', NOW());

-- Add jokes

INSERT INTO Joke (id, joke, added, user) VALUES (1, 'I know a guy who\'s addicted to brake fluid. He says he can stop any time.', NOW(), 1);
INSERT INTO Joke (id, joke, added, user) VALUES (2, 'A woman gets on a bus with her baby. The bus driver says: ''''Ugh, that''s the ugliest baby I''ve ever seen!'''' The woman walks to the rear of the bus and sits down, fuming. She says to a man next to her: ''''The driver just insulted me!'''' The man says: ''''You go up there and tell him off. Go on, I''ll hold your monkey for you.''''', NOW(), 2);
INSERT INTO Joke (id, joke, added, user) VALUES (3, 'Police arrested two kids yesterday, one was drinking battery acid, the other was eating fireworks. They charged one - and let the other one off.', NOW(), 1);
INSERT INTO Joke (id, joke, added, user) VALUES (4, 'I''m on a whiskey diet. I''ve lost three days already. ', NOW(), 1);
INSERT INTO Joke (id, joke, added, user) VALUES (5, 'A SQL query goes into a bar, walks up to two tables and asks, "Can I join you?"', NOW(), 2);
INSERT INTO Joke (id, joke, added, user) VALUES (6, 'Q: How many prolog programmers does it take to change a lightbulb? A: Yes.', NOW(), 1);


INSERT INTO AuthenticatedUser (id, user_id, token) VALUES (1, 1, 'superUniqueToken');


