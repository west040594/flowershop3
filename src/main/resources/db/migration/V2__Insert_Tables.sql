INSERT INTO tb_user (username, password, email, status, role, created_at, updated_at)
VALUES ('admin', 'admin123', 'admin@example.com', 1, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO tb_user (username, password, email, status, role, created_at, updated_at)
VALUES ('user', 'user123', 'user@example.com', 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO tb_customer(first_name, last_name, balance, discount, phone_number, street, city, country, user_id)
VALUES ('Semen', 'Shtylev', 100000, 5, '89108439650', 'Kolcevaya', 'Tver', 'Russia', 1);


INSERT INTO tb_customer(first_name, last_name, balance, discount, phone_number, street, city, country, user_id)
VALUES ('SemenUser', 'ShtylevUser', 2000, 3, '8910646800', 'Kolcevaya', 'Tver', 'Russia', 2);


INSERT INTO tb_category(name, description, image_url, status)
VALUES ('Розы', 'Розы Описание', 'rose.jpg', 1);

INSERT INTO tb_category(name, description, image_url, status)
VALUES ('Тюльпаны', 'Тюльпаны Описание', 'tuplane.jpg', 1);

INSERT INTO tb_category(name, description, image_url, status)
VALUES ('Орхидеи', 'Орхидеи Описание', 'orhidei.jpg', 1);


INSERT INTO tb_product(name, price, category_id, description, image_url, status, in_stock, created_at, updated_at)
VALUES ('Флорибунда', 80, 1, 'Описание Роз Флорибунда', 'florebund.jpg', 1, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO tb_product(name, price, category_id, description, image_url, status, in_stock, created_at, updated_at)
VALUES ('Патио', 100, 1, 'Описание Роз Патио', 'patio.jpg', 1, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO tb_product(name, price, category_id, description, image_url, status, in_stock, created_at, updated_at)
VALUES ('Монте Карло', 100, 2, 'Описание Тюльпанов Монте Карло', 'monte_carlo.jpg', 1, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO tb_product(name, price, category_id, description, image_url, status, in_stock, created_at, updated_at)
VALUES ('Монтрекс', 70, 2, 'Описание Тюльпанов Монтрекс', 'montreux.jpg', 1, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO tb_product(name, price, category_id, description, image_url, status, in_stock, created_at, updated_at)
VALUES ('Камбрия', 90, 3, 'Описание Орхидей Камбрия', 'cambriya.jpg', 1, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO tb_product(name, price, category_id, description, image_url, status, in_stock,  created_at, updated_at)
VALUES ('Брассия', 100, 3, 'Описание Орихдей Брассия', 'brassiya.jpg', 1, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);




