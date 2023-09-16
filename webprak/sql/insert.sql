-- INSERT INTO category (category_id, name, description)
-- VALUES (1, 'fruits', 'fresh fruits from warm countries');

INSERT INTO category (category_id, name, description) VALUES
    (101, 'Food', NULL),
    (102, 'Household chemicals', NULL),
    (103, 'Clothes', NULL),
    (104, 'Consumer electronics', NULL);

INSERT INTO product (product_id, name, description, category_id) VALUES
    (101, 'Potatoes', NULL, 101),
    (102, 'Lemon juice (1 liter)', NULL, 101),
    (103, 'Soap', NULL, 102),
    (104, 'T-Short', NULL, 103),
    (105, 'Electric teapot', NULL, 104),
    (106, 'Refrigerator', NULL, 104);

INSERT INTO provider (provider_id, name, phone, description, address) VALUES
    (101, 'Food source', '11111111111', 'Test description for food provider', 'Huan-Tobo, 6'),
    (102, 'Chemicals source', '22222222222', 'Test description for chemicals source', 'Gustavo street, 7')
    (103, 'Electronics provider', '33333333333', 'Test description for electronics provider', 'Mike street, 8');

INSERT INTO client (client_id, name, phone, description, address) VALUES
    (101, 'A potato consumer', '44444444444', 'Main potato consumer', 'Walter street, 9');

INSERT INTO supply (supply_id, provider_id, product_id, amount, arrival_time, has_arrived) VALUES
    (101, 101, 101, 20, '2020-12-21', TRUE),
    (102, 101, 101, 10, '2020-11-21', TRUE),
    (103, 101, 102, 10, '2021-01-13', TRUE),
    (104, 102, 103, 10, '2021-02-12', TRUE),
    (105, 102, 104, 10, '2021-01-29', FALSE),
    (106, 103, 105, 10, '2020-11-21', TRUE),
    (107, 103, 106, 10, '2021-01-28', TRUE);

INSERT INTO orders (order_id, client_id, product_id, amount, departure_date, has_departed) VALUES
    (101, 101, 101, 30, '2020-01-20', TRUE),
    (102, 101, 101, 30, '2020-05-20', FALSE),
    (103, 101, 101, 40, '2020-05-21', FALSE),
    (104, 101, 106, 1, '2020-07-01', FALSE);
	
INSERT INTO storage (storage_id, storage_type, free_space) VALUES
	(101, 'refrigirator', 100),
	(102, 'electronics storage', 100),
	(103, 'clothes storage', 100);

INSERT INTO product_instance (instance_id, product_id, amount, arrival_date, exparation_date, supply_id, order_id, storage_id) VALUES
    (101, 101, 10, '2020-12-21', '2021-06-03', 101, 102, 101),
    (102, 101, 10, '2020-12-21', '2021-06-03', 101, 102, 102),
    (103, 101, 10, '2020-12-21', '2021-06-03', 101, 102, 103),
    (104, 101, 10, '2020-12-21', '2021-06-03', 101, 103, 101),
    (105, 101, 15, '2020-11-17', '2021-04-03', 102, 103, 102),
    (106, 101, 15, '2020-11-17', '2021-04-03', 102, 103, 101),
    (107, 101, 15, '2020-11-17', '2021-04-03', 102, NULL, 102),
    (108, 102, 6, '2021-01-13', '2021-03-13',  103, NULL, 103),
    (109, 102, 6, '2021-01-13', '2021-03-13',  103, NULL, 101),
    (110, 103, 30, '2020-11-01', '2030-11-01', 104, NULL, 102),
    (111, 103, 30, '2020-11-01', '2030-11-01', 104, NULL, 103),
    (112, 104, 20, '2021-02-12', NULL, 105, NULL, 102),
    (113, 104, 20, '2021-02-12', NULL, 105, NULL, 101),
    (114, 104, 20, '2021-04-15', NULL, 105, NULL, 102),
    (115, 104, 20, '2021-04-15', NULL, 105, NULL, 101),
    (116, 105, 3, '2021-01-29', NULL,  106, NULL, 103),
    (117, 105, 3, '2021-01-29', NULL,  106, NULL, 102),
    (118, 105, 3, '2021-01-29', NULL,  106, NULL, 103),
    (119, 106, 1, '2021-01-28', NULL,  107, 104, 101);
	