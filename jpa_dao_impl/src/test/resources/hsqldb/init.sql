DROP TABLE IF EXISTS category CASCADE;
CREATE TABLE category (
  id          INTEGER NOT NULL,
  description VARCHAR(255),
  title       VARCHAR(255),
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS commodity CASCADE;
CREATE TABLE commodity (
  id          INTEGER NOT NULL,
  description VARCHAR(255),
  price       NUMERIC(19, 2),
  title       VARCHAR(255),
  category_id INTEGER,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS customer CASCADE;
CREATE TABLE customer (
  id       INTEGER NOT NULL,
  name     VARCHAR(255),
  password VARCHAR(255),
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS indent CASCADE;
CREATE TABLE indent (
  id          INTEGER NOT NULL,
  description VARCHAR(255),
  status      VARCHAR(255),
  customer_id INTEGER,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS indent_item CASCADE;
CREATE TABLE indent_item (
  indent_id    INTEGER NOT NULL,
  commodity_id INTEGER NOT NULL
);

ALTER TABLE indent_item
ADD CONSTRAINT UK_mnxple8ttkmko4xr0ib80rqdy UNIQUE (commodity_id);

ALTER TABLE commodity
ADD CONSTRAINT FK_bj0td2dx4o1ydex71v0qfq66v
FOREIGN KEY (category_id)
REFERENCES category;

ALTER TABLE indent
ADD CONSTRAINT FK_ep42v16ocer36pr9s7i6akdmc
FOREIGN KEY (customer_id)
REFERENCES customer;

ALTER TABLE indent_item
ADD CONSTRAINT FK_mnxple8ttkmko4xr0ib80rqdy
FOREIGN KEY (commodity_id)
REFERENCES commodity;

ALTER TABLE indent_item
ADD CONSTRAINT FK_bfofqum97yfep8ntg68a9g1wx
FOREIGN KEY (indent_id)
REFERENCES indent;

DROP SEQUENCE IF EXISTS category_id_seq CASCADE;
CREATE SEQUENCE category_id_seq
    START WITH 1;

DROP SEQUENCE IF EXISTS commodity_id_seq CASCADE;
CREATE SEQUENCE commodity_id_seq
    START WITH 1;

DROP SEQUENCE IF EXISTS customer_id_seq CASCADE;
CREATE SEQUENCE customer_id_seq
    START WITH 1;

DROP SEQUENCE IF EXISTS indent_id_seq CASCADE;
CREATE SEQUENCE indent_id_seq
    START WITH 1;