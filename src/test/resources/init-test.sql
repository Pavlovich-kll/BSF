-- truncate table bank;
truncate table address cascade;
truncate table user_contact_information cascade;
truncate table account cascade;
-- truncate table users;


INSERT INTO address (id, address, city, country, state, zip)
VALUES (1 ,'address1', 'city1', 'country1', 'state1', 11111);
INSERT INTO address (id, address, city, country, state, zip)
VALUES (2, 'address2', 'city2', 'country2', 'state2', 22222);

INSERT INTO bank (id, branch_code, branch_name, routing_number, address_id)
VALUES (1, '3800393', 'branch1', '12131415', 1);
INSERT INTO bank (id, branch_code, branch_name, routing_number, address_id)
VALUES (2, '3800456', 'branch2', '98765432', 2);

INSERT INTO user_contact_information (id, address, city, country, email, phone_number)
VALUES (1, 'Exchange line, 4', 'Saint-Petersburg', 'Russia', 'kirill@gmail.com', '+79213463434');
INSERT INTO user_contact_information (id, address, city, country, email, phone_number)
VALUES (2, 'Exchange line, 5', 'Saint-Petersburg', 'Russia', 'vadim@gmail.com', '+7921346634');

INSERT INTO account (id, account_balance, account_status, account_type, create_date)
VALUES (1, 10000.00, 'enable', 'dollars', '2022-09-05 09:13:51.730872');
INSERT INTO account (id, account_balance, account_status, account_type, create_date)
VALUES (2, 10000.00, 'enable', 'dollars', '2022-09-05 09:13:55.704231');

INSERT INTO users (id, creation_date, first_name, last_name, status, bank_id, contact_id, account_id)
VALUES (1, '2022-09-05 09:13:17.781394', 'Kirill', 'Pavlovich', 'enabled', 1, 1, 1);
INSERT INTO users (id, creation_date, first_name, last_name, status, bank_id, contact_id, account_id)
VALUES (2, '2022-09-05 09:13:42.518304', 'Vadim', 'Vadimovich', 'enabled', 2, 2, 2);

