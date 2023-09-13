CREATE TABLE category (
	category_id int PRIMARY KEY,
	name varchar(30) NOT NULL,
	description text 
);

CREATE TABLE provider (
	provider_id int PRIMARY KEY,
	name varchar(30) NOT NULL,
	phone varchar(13),
	description text,
	address text NOT NULL
);

CREATE TABLE client (
	client_id int PRIMARY KEY,
	name varchar(30) NOT NULL,
	phone varchar(13) NOT NULL,
	description text,
	address text NOT NULL
);

CREATE TABLE product (
	product_id int PRIMARY KEY,
	name varchar(30) NOT NULL,
	description text,
	category_id int REFERENCES category (category_id)
);

CREATE TABLE supply (
	supply_id int PRIMARY KEY,
	provider_id int REFERENCES provider (provider_id),
	product_id int REFERENCES product (product_id),
	amount real,
	arrival_time timestamp,
	has_arrived bool NOT NULL
);

CREATE TABLE orders (
	order_id int PRIMARY KEY,
	client_id int REFERENCES client (client_id),
	product_id int REFERENCES product (product_id),
	amount real,
	departure_date timestamp,
	has_departed bool NOT NULL
);

CREATE TABLE storage (
	storage_id int PRIMARY KEY,
	storage_type varchar(30) NOT NULL,
	free_space int NOT NULL
);

CREATE TABLE product_instance (
	instance_id int PRIMARY KEY,
	product_id int REFERENCES product (product_id),
	amount real,
	arrival_date timestamp,
	exparation_date timestamp,
	supply_id int REFERENCES supply (supply_id),
	order_id int REFERENCES orders (order_id),
	storage_id int REFERENCES storage (storage_id)
);


CREATE OR REPLACE FUNCTION decrement_free_space()
	RETURNS TRIGGER
AS $decrement_free_space$
BEGIN
	UPDATE storage SET free_space=free_space-1 WHERE storage_id=NEW.storage_id;  
	RETURN NULL;
END;
$decrement_free_space$ LANGUAGE PLPGSQL;

CREATE OR REPLACE FUNCTION increment_free_space()
	RETURNS TRIGGER
AS $increment_free_space$
BEGIN
	UPDATE storage SET free_space=free_space+1 WHERE storage_id=OLD.storage_id;  
	RETURN NULL;
END;
$increment_free_space$ LANGUAGE PLPGSQL;

DROP TRIGGER IF EXISTS tr_insert ON product_instance;
DROP TRIGGER IF EXISTS tr_delete ON product_instance;

CREATE TRIGGER tr_insert AFTER insert on product_instance for each row execute procedure decrement_free_space();
CREATE TRIGGER tr_delete AFTER delete on product_instance for each row execute procedure increment_free_space();


	


