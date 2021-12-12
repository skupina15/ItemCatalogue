INSERT INTO persone (id_persone, user_name, email) VALUES (1, 'user1', 'krneki@krneki.com');
INSERT INTO persone (id_persone, user_name, email) VALUES (2, 'user2', 'user2@krneki.com');
INSERT INTO item (id_item, title, description, daily_price, id_persone, tag) VALUES (11, 'plezalni pas', 'pas za plezanje', 5, 1, 'sportno');
INSERT INTO item (id_item, title, description, daily_price, id_persone, tag) VALUES (12, 'dereze', 'dereze', 10, 1, 'zimsko');
INSERT INTO rent (id_rent, registration_date, id_persone, id_item) VALUES (20, '6-12-2021', 2, 11);