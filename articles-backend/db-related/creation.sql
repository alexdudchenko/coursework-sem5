CREATE TABLE files
(
    id   SERIAL PRIMARY KEY,
    name TEXT,
    doc  BYTEA
);

CREATE TABLE users
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(50),
    surname      VARCHAR(50),
    email        VARCHAR(40),
    hashpassword TEXT NOT NULL,
    role         VARCHAR(10)
);

CREATE TABLE articles
(
    id      SERIAL PRIMARY KEY,
    title   VARCHAR(200),
    description TEXT,
    publication_date DATE,
    file_id INT REFERENCES files
);

CREATE TABLE savings
(
    id         SERIAL PRIMARY KEY,
    user_id    INT REFERENCES users,
    article_id INT REFERENCES articles
);

CREATE TABLE tags
(
    id    SERIAL PRIMARY KEY,
    title VARCHAR(30) NOT NULL
);

CREATE TABLE tags_articles
(
    id         SERIAL PRIMARY KEY,
    tag_id     INT REFERENCES tags,
    article_id INT REFERENCES articles
);