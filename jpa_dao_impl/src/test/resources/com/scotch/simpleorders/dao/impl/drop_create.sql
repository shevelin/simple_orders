DROP SEQUENCE IF EXISTS customer_id_seq CASCADE;
CREATE SEQUENCE customer_id_seq INCREMENT 1 MINVALUE 1;

DROP TABLE IF EXISTS customer CASCADE;
CREATE TABLE customer (
	id		integer	PRIMARY KEY,
	name		text	UNIQUE NOT NULL,
	password	text	NOT NULL
);

DROP SEQUENCE IF EXISTS category_id_seq CASCADE;
CREATE SEQUENCE category_id_seq INCREMENT 1 MINVALUE 1;

DROP TABLE IF EXISTS category CASCADE;
CREATE TABLE category (
	id		integer PRIMARY KEY,
	title		text	NOT NULL,
	description	text	NOT NULL
);

DROP SEQUENCE IF EXISTS commodity_id_seq CASCADE;
CREATE SEQUENCE commodity_id_seq INCREMENT 1 MINVALUE 1;

DROP TABLE IF EXISTS commodity CASCADE;
CREATE TABLE commodity (
	id		integer PRIMARY KEY,
	title		text	NOT NULL,
	description	text	NOT NULL,
	category_id	integer REFERENCES category (id) NOT NULL,
	price		numeric	NOT NULL
);

DROP SEQUENCE IF EXISTS indent_id_seq CASCADE;
CREATE SEQUENCE indent_id_seq INCREMENT 1 MINVALUE 1;

DROP TYPE IF EXISTS indent_status_type CASCADE;
CREATE TYPE indent_status_type AS ENUM ('NEW', 'IN_PROCESS', 'DONE');

DROP TABLE IF EXISTS indent CASCADE;
CREATE TABLE indent (
	id		integer PRIMARY KEY,
	customer_id	integer REFERENCES customer (id) NOT NULL,
	description	text	NOT NULL,
	status 		indent_status_type
);

DROP SEQUENCE IF EXISTS indent_item_id_seq CASCADE;
CREATE SEQUENCE indent_item_id_seq INCREMENT 1 MINVALUE 1;

DROP TABLE IF EXISTS indent_item CASCADE;
CREATE TABLE indent_item (
	id		integer PRIMARY KEY,
	indent_id	integer REFERENCES indent (id) NOT NULL,
	commodity_id	integer REFERENCES commodity (id) NOT NULL,
	count		integer CONSTRAINT positive_count CHECK (count > 0)
);