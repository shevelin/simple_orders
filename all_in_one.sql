DROP SEQUENCE IF EXISTS customer_id_seq CASCADE;
CREATE SEQUENCE customer_id_seq INCREMENT 1 MINVALUE 1;

DROP TABLE IF EXISTS customer CASCADE;
CREATE TABLE customer (
	id		integer	PRIMARY KEY DEFAULT nextval('customer_id_seq'::regclass),
	name		text	UNIQUE NOT NULL,
	password	text	NOT NULL
);

DROP SEQUENCE IF EXISTS category_id_seq CASCADE;
CREATE SEQUENCE category_id_seq INCREMENT 1 MINVALUE 1;

DROP TABLE IF EXISTS category CASCADE;
CREATE TABLE category (
	id		integer PRIMARY KEY DEFAULT nextval('category_id_seq'::regclass),
	title		text	NOT NULL,
	description	text	NOT NULL	
);

DROP SEQUENCE IF EXISTS commodity_id_seq CASCADE;
CREATE SEQUENCE commodity_id_seq INCREMENT 1 MINVALUE 1;

DROP TABLE IF EXISTS commodity CASCADE;
CREATE TABLE commodity (
	id		integer PRIMARY KEY DEFAULT nextval('commodity_id_seq'::regclass),
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
	id		integer PRIMARY KEY DEFAULT nextval('indent_id_seq'::regclass),
	customer_id	integer REFERENCES customer (id) NOT NULL,
	description	text	NOT NULL,
	status 		indent_status_type
);

DROP SEQUENCE IF EXISTS indent_item_id_seq CASCADE;
CREATE SEQUENCE indent_item_id_seq INCREMENT 1 MINVALUE 1;

DROP TABLE IF EXISTS indent_item CASCADE;
CREATE TABLE indent_item (
	id		integer PRIMARY KEY DEFAULT nextval('indent_item_id_seq'::regclass),
	indent_id	integer REFERENCES indent (id) NOT NULL,
	commodity_id	integer REFERENCES commodity (id) NOT NULL,
	count		integer CONSTRAINT positive_count CHECK (count > 0)
);


INSERT INTO customer (id, name, password) VALUES
	(1, 'Serious Sam', 'password'),
	(2, 'Wild Bill', 'password'),
	(3, 'Dieter Bohlen', 'password'),
	(4, 'Tom Cruise', 'password'),
	(5, 'Vasya', 'password');
ALTER SEQUENCE IF EXISTS customer_id_seq RESTART WITH 6;

INSERT INTO category (id, title, description) VALUES
	(1, 'fruit', 'Some description of fruit category'),
	(2, 'vegetables', 'Some description of vegetables category'),
	(3, 'shoes', 'Some description of shoes category'),
	(4, 'clothes', 'Some description of clothes category'),
	(5, 'tvs', 'Some description of tvs category');
ALTER SEQUENCE IF EXISTS category_id_seq RESTART WITH 6;

INSERT INTO commodity (id, title, description, category_id, price) VALUES
	(1,  'pear', '', 1, 1.0),	--fruit
	(2,  'apple', '', 1, 1.2),
	(3,  'orange', '', 1, 1.45),
	(4,  'banana', '', 1, 7.25),
	(5,  'kiwi', '', 1, 11.03),
	(6,  'tomato', '', 2, 5.0),	--vegetables
	(7,  'cucumber', '', 2, 6.30),
	(8,  'radish', '', 2, 8.05),
	(9,  'potato', '', 2, 3.03),
	(10, 'sprouts', '', 2, 4.40),
	(11, 'Polo by Ralph Lauren', 'Men''s Vito Sneaker', 3, 17.50),	--shoes
	(12, 'Clarks', 'Men''s Bushacre 2 Desert Boot', 3, 24.05),
	(13, 'Vans', 'Men''s SK8-Hi Skate Shoe', 3, 11.40),
	(14, 'Florsheim', 'Men''s Brinson Wing Tip Loafer', 3, 4.05),
	(15, 'Diesel', 'Men''s Solar Sneaker', 3, 8.90),
	(16, 'Elodia Velvet Midi Dress Deep Green', '', 4, 11.50),	--clothes
	(17, 'Elodia Velvet Midi Dress Red', '', 4, 18.70),
	(18, 'Chiffon Back Wrap Playsuit Ice Grey', '', 4, 23.60),
	(19, 'Ranisha Eyelash Lace Strappy Playsuit', '', 4, 34.55),
	(20, 'Saira Nude and Black Lace Bandage', '', 4, 34.40),
	(21, 'LG 50LB650V Smart 3D 50" LED TV', 'As advertised on TV', 5, 345.0),	--tvs
	(22, 'SAMSUNG UE55HU7200 Smart 4k Ultra HD 55" Curved LED TV', '5 year guarantee included. View more info .', 5, 967.0),
	(23, 'PANASONIC VIERA TX-40AS640B Smart 3D 40" LED TV', 'With a dual core processor this exclusive Smart 3D TV delivers on performance and includes Freetime, which lets you roll back the TV guide and watch the show you’ve missed from the past seven days.', 5, 1200.0),
	(24, 'LG 49UB820V Smart 4k Ultra HD 49" LED TV', 'As advertised on TV. This exclusive Smart 4k Ultra HD TV offers superior picture quality. With powerful, built-in processors you can upscale content up to four times the resolution of Full HD picture quality.', 5, 1450.0),
	(25, 'SAMSUNG UE32H5500 Smart 32" LED TV', 'As advertised on TV. This exclusive Smart 4k Ultra HD TV offers superior picture quality. With powerful, built-in processors you can upscale content up to four times the resolution of Full HD picture quality. ', 5, 1560.0);
ALTER SEQUENCE IF EXISTS commodity_id_seq RESTART WITH 26;

INSERT INTO indent (id, customer_id, description, status) VALUES
	(1, 1, 'Some descripion of this order', 'NEW'), --'NEW', 'IN_PROCESS', 'DONE'
	(2, 1, 'Some descripion of this order', 'IN_PROCESS'),
	(3, 3, 'Some descripion of this order', 'DONE'),
	(4, 4, 'Some descripion of this order', 'NEW'),
	(5, 4, 'Some descripion of this order', 'IN_PROCESS');
ALTER SEQUENCE IF EXISTS indent_id_seq RESTART WITH 6;

INSERT INTO indent_item (id, indent_id, commodity_id, count) VALUES
	(1, 1, 10, 5), --indent_id - [1-5], commodity_id - [1-25]
	(2, 2, 11, 2),
	(3, 1, 8, 3),
	(4, 4, 3, 1),
	(5, 5, 12, 8),
	(6, 3, 10, 2),
	(7, 1, 10, 7),
	(8, 2, 4, 6),
	(9, 3, 24, 9),
	(10, 4, 23, 1),
	(11, 5, 2, 1),
	(12, 1, 4, 3),
	(13, 2, 7, 45),
	(14, 3, 8, 3);
ALTER SEQUENCE IF EXISTS indent_item_id_seq RESTART WITH 15;
