DROP TABLE IF EXISTS books, authors;

CREATE TABLE  books(
id BIGSERIAL PRIMARY KEY,
author_id INT NOT NULL,
title VARCHAR(250) NOT NULL,
priceOld  VARCHAR(250) DEFAULT NULL,
price VARCHAR(250) DEFAULT NULL
);

create table authors (
                         author_id BIGSERIAL PRIMARY KEY,
                         first_name VARCHAR(50),
                         last_name VARCHAR(50)
);