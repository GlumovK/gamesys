DROP TABLE IF EXISTS news;

CREATE TABLE news
(
    id               INTEGER PRIMARY KEY AUTO_INCREMENT,
    topic             VARCHAR                 NOT NULL,
    length            INTEGER                 NOT NULL
);