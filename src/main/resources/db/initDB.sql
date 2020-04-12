CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE news
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  topic             VARCHAR                 NOT NULL,
  length            INTEGER                 NOT NULL
);

