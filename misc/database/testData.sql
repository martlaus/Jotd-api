-- Add jokes

INSERT INTO Joke (id, joke, added) VALUES (1, 'I know a guy who’s addicted to brake fluid. He says he can stop any time.', NOW());
INSERT INTO Joke (id, joke, added) VALUES (2, 'A woman gets on a bus with her baby. The bus driver says: ''''Ugh, that''s the ugliest baby I''ve ever seen!'''' The woman walks to the rear of the bus and sits down, fuming. She says to a man next to her: ''''The driver just insulted me!'''' The man says: ''''You go up there and tell him off. Go on, I''ll hold your monkey for you.''''', NOW());
INSERT INTO Joke (id, joke, added) VALUES (3, 'Police arrested two kids yesterday, one was drinking battery acid, the other was eating fireworks. They charged one - and let the other one off.', NOW());
INSERT INTO Joke (id, joke, added) VALUES (4, 'I''m on a whiskey diet. I''ve lost three days already. ', NOW());
INSERT INTO Joke (id, joke, added) VALUES (5, 'A SQL query goes into a bar, walks up to two tables and asks, "Can I join you?"', NOW());
INSERT INTO Joke (id, joke, added) VALUES (6, 'Q: How many prolog programmers does it take to change a lightbulb? A: Yes.', NOW());

-- Add Users
INSERT INTO User (id, email, password, created) VALUES (1, 'admin@admin.kz', 'kaarliema', NOW());
INSERT INTO User (id, email, password, created) VALUES (2, 'user@user.kz', 'siimuema', NOW());

INSERT INTO AuthenticatedUser (id, user_id, token) VALUES (1, 1, 'superUniqueToken');
