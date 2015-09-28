use jotd;

-- Drop tables

DROP TABLE IF EXISTS Joke;


-- Create tables

CREATE TABLE Joke(
  id      BIGINT AUTO_INCREMENT PRIMARY KEY,
  joke    TEXT(255) NOT NULL
);