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


INSERT INTO customer (id, name, password) VALUES
	(nextval('customer_id_seq'), 'Serious Sam', 'password'),
	(nextval('customer_id_seq'), 'Wild Bill', 'password'),
	(nextval('customer_id_seq'), 'Dieter Bohlen', 'password'),
	(nextval('customer_id_seq'), 'Tom Cruise', 'password'),
	(nextval('customer_id_seq'), 'Vasya', 'password');
--ALTER SEQUENCE IF EXISTS customer_id_seq RESTART WITH 6;

INSERT INTO category (id, title, description) VALUES
	(nextval('category_id_seq'), 'fruit', 'Some description of fruit category'),
	(nextval('category_id_seq'), 'vegetables', 'Some description of vegetables category'),
	(nextval('category_id_seq'), 'shoes', 'Some description of shoes category'),
	(nextval('category_id_seq'), 'clothes', 'Some description of clothes category'),
	(nextval('category_id_seq'), 'tvs', 'Some description of tvs category');
--ALTER SEQUENCE IF EXISTS category_id_seq RESTART WITH 6;

INSERT INTO commodity (id, title, description, category_id, price) VALUES
	(nextval('commodity_id_seq'),  'pear', '', 1, 1.0),	--fruit
	(nextval('commodity_id_seq'),  'apple', '', 1, 1.2),
	(nextval('commodity_id_seq'),  'orange', '', 1, 1.45),
	(nextval('commodity_id_seq'),  'banana', '', 1, 7.25),
	(nextval('commodity_id_seq'),  'kiwi', '', 1, 11.03),
	(nextval('commodity_id_seq'),  'tomato', '', 2, 5.0),	--vegetables
	(nextval('commodity_id_seq'),  'cucumber', '', 2, 6.30),
	(nextval('commodity_id_seq'),  'radish', '', 2, 8.05),
	(nextval('commodity_id_seq'),  'potato', '', 2, 3.03),
	(nextval('commodity_id_seq'), 'sprouts', '', 2, 4.40),
	(nextval('commodity_id_seq'), 'Polo by Ralph Lauren', 'Men''s Vito Sneaker', 3, 17.50),	--shoes
	(nextval('commodity_id_seq'), 'Clarks', 'Men''s Bushacre 2 Desert Boot', 3, 24.05),
	(nextval('commodity_id_seq'), 'Vans', 'Men''s SK8-Hi Skate Shoe', 3, 11.40),
	(nextval('commodity_id_seq'), 'Florsheim', 'Men''s Brinson Wing Tip Loafer', 3, 4.05),
	(nextval('commodity_id_seq'), 'Diesel', 'Men''s Solar Sneaker', 3, 8.90),
	(nextval('commodity_id_seq'), 'Elodia Velvet Midi Dress Deep Green', '', 4, 11.50),	--clothes
	(nextval('commodity_id_seq'), 'Elodia Velvet Midi Dress Red', '', 4, 18.70),
	(nextval('commodity_id_seq'), 'Chiffon Back Wrap Playsuit Ice Grey', '', 4, 23.60),
	(nextval('commodity_id_seq'), 'Ranisha Eyelash Lace Strappy Playsuit', '', 4, 34.55),
	(nextval('commodity_id_seq'), 'Saira Nude and Black Lace Bandage', '', 4, 34.40),
	(nextval('commodity_id_seq'), 'LG 50LB650V Smart 3D 50" LED TV', 'As advertised on TV', 5, 345.0),	--tvs
	(nextval('commodity_id_seq'), 'SAMSUNG UE55HU7200 Smart 4k Ultra HD 55" Curved LED TV', '5 year guarantee included. View more info .', 5, 967.0),
	(nextval('commodity_id_seq'), 'PANASONIC VIERA TX-40AS640B Smart 3D 40" LED TV', 'With a dual core processor this exclusive Smart 3D TV delivers on performance and includes Freetime, which lets you roll back the TV guide and watch the show you’ve missed from the past seven days.', 5, 1200.0),
	(nextval('commodity_id_seq'), 'LG 49UB820V Smart 4k Ultra HD 49" LED TV', 'As advertised on TV. This exclusive Smart 4k Ultra HD TV offers superior picture quality. With powerful, built-in processors you can upscale content up to four times the resolution of Full HD picture quality.', 5, 1450.0),
	(nextval('commodity_id_seq'), 'SAMSUNG UE32H5500 Smart 32" LED TV', 'As advertised on TV. This exclusive Smart 4k Ultra HD TV offers superior picture quality. With powerful, built-in processors you can upscale content up to four times the resolution of Full HD picture quality. ', 5, 1560.0);
--ALTER SEQUENCE IF EXISTS commodity_id_seq RESTART WITH 26;

INSERT INTO indent (id, customer_id, description, status) VALUES
	(nextval('indent_id_seq'), 1, 'Some descripion of this order', 'NEW'), --'NEW', 'IN_PROCESS', 'DONE'
	(nextval('indent_id_seq'), 1, 'Some descripion of this order', 'IN_PROCESS'),
	(nextval('indent_id_seq'), 3, 'Some descripion of this order', 'DONE'),
	(nextval('indent_id_seq'), 4, 'Some descripion of this order', 'NEW'),
	(nextval('indent_id_seq'), 4, 'Some descripion of this order', 'IN_PROCESS');
--ALTER SEQUENCE IF EXISTS indent_id_seq RESTART WITH 6;

INSERT INTO indent_item (id, indent_id, commodity_id, count) VALUES
	(nextval('indent_item_id_seq'), 1, 10, 5), --indent_id - [1-5], commodity_id - [1-25]
	(nextval('indent_item_id_seq'), 2, 11, 2),
	(nextval('indent_item_id_seq'), 1, 8, 3),
	(nextval('indent_item_id_seq'), 4, 3, 1),
	(nextval('indent_item_id_seq'), 5, 12, 8),
	(nextval('indent_item_id_seq'), 3, 10, 2),
	(nextval('indent_item_id_seq'), 1, 10, 7),
	(nextval('indent_item_id_seq'), 2, 4, 6),
	(nextval('indent_item_id_seq'), 3, 24, 9),
	(nextval('indent_item_id_seq'), 4, 23, 1),
	(nextval('indent_item_id_seq'), 5, 2, 1),
	(nextval('indent_item_id_seq'), 1, 4, 3),
	(nextval('indent_item_id_seq'), 2, 7, 45),
	(nextval('indent_item_id_seq'), 3, 8, 3);
--ALTER SEQUENCE IF EXISTS indent_item_id_seq RESTART WITH 15;
