INSERT INTO tb_user (username, password, email, status, role, created_at, updated_at)
VALUES ('admin', 'admin123', 'admin@example.com', 1, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO tb_user (username, password, email, status, role, created_at, updated_at)
VALUES ('user', 'user123', 'user@example.com', 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO tb_customer(first_name, last_name, balance, discount, phone_number, street, city, country, user_id)
VALUES ('Semen', 'Shtylev', 100000, 5, '89108439650', 'Kolcevaya', 'Tver', 'Russia', 1);


INSERT INTO tb_customer(first_name, last_name, balance, discount, phone_number, street, city, country, user_id)
VALUES ('SemenUser', 'ShtylevUser', 2000, 3, '8910646800', 'Kolcevaya', 'Tver', 'Russia', 2);