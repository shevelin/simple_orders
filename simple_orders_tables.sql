CREATE TABLE customer (
	id		integer	PRIMARY KEY,
	name		text	UNIQUE NOT NULL,
	password	text	NOT NULL
);

CREATE TABLE category (
	id		integer PRIMARY KEY,
	title		text	NOT NULL,
	description	text	NOT NULL	
);

CREATE TABLE commodity (
	id		integer PRIMARY KEY,
	title		text	NOT NULL,
	description	text	NOT NULL,
	category_id	integer REFERENCES category (id) NOT NULL,
	price		money	NOT NULL	
);

CREATE TYPE indent_status_type AS ENUM ('new', 'inprogress', 'done');

CREATE TABLE indent (
	id		integer PRIMARY KEY,
	customer_id	integer REFERENCES customer (id) NOT NULL,
	description	text	NOT NULL,
	status 		indent_status_type
);

CREATE TABLE indent_item (
	id		integer PRIMARY KEY,
	indent_id	integer REFERENCES indent (id) NOT NULL,
	commodity_id	integer REFERENCES commodity (id) NOT NULL,
	count		integer CONSTRAINT positive_count CHECK (count > 0)
);
