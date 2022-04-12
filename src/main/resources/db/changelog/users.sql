CREATE TABLE IF NOT EXISTS USERS (
  username VARCHAR(50) NOT NULL primary key,
  password VARCHAR(100) NOT NULL,
  enabled boolean NOT NULL DEFAULT TRUE
);

CREATE TABLE authorities (
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username
  on authorities (username,authority);