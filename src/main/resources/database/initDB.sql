DROP TABLE IF EXISTS customers;
CREATE TABLE IF NOT EXISTS customers(
    id SERIAL NOT NULL PRIMARY KEY,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    address varchar(1000) not null,
    budget decimal not null
);