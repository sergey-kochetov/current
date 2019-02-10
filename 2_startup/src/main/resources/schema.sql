CREATE TABLE IF NOT EXISTS buyer(
    id BIGINT ,
    name VARCHAR(30),
    country VARCHAR(30),
    token int
);

CREATE TABLE IF NOT EXISTS book(
    id BIGINT,
    name VARCHAR(30),
    author VARCHAR(30)
);