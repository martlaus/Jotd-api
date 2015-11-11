USE jotd;

-- Drop tables

SET foreign_key_checks = 0;

DROP TABLE Joke;
DROP TABLE User;
DROP TABLE AuthenticatedUser;
DROP TABLE Vote;

SET foreign_key_checks = 1;

-- Create tables

CREATE TABLE Joke (
  id    BIGINT    AUTO_INCREMENT PRIMARY KEY,
  joke  TEXT NOT NULL,
  added TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE User (
  id       BIGINT    AUTO_INCREMENT PRIMARY KEY,
  email    VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255)        NOT NULL,
  role     VARCHAR(255)        NOT NULL,
  created  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE AuthenticatedUser (
  id      BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT              NOT NULL,
  token   VARCHAR(255) UNIQUE NOT NULL,

  FOREIGN KEY (user_id)
  REFERENCES User (id)
    ON DELETE RESTRICT
);

CREATE TABLE Vote (
  id      BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  joke_id BIGINT NOT NULL,

  FOREIGN KEY (user_id)
  REFERENCES User (id)
    ON DELETE RESTRICT,

  FOREIGN KEY (joke_id)
  REFERENCES Joke (id)
    ON DELETE RESTRICT,

  UNIQUE KEY (user_id, joke_id, id)

);


