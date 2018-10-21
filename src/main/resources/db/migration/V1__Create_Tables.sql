CREATE TABLE IF NOT EXISTS tb_user (
  id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  status SMALLINT DEFAULT 1,
  role SMALLINT DEFAULT 2,
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tb_customer (
  id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  balance DECIMAL(8,2),
  discount INT,
  phone_number VARCHAR(30),
  street VARCHAR(100),
  city VARCHAR(50),
  country VARCHAR(50),
  user_id INT REFERENCES tb_user(id),
);


CREATE TABLE IF NOT EXISTS tb_category (
  id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name VARCHAR(100) NOT NULL,
  description TEXT,
  image_url VARCHAR(255),
  status SMALLINT DEFAULT 1
);


CREATE TABLE IF NOT EXISTS tb_product (
  id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  name VARCHAR(255) NOT NULL,
  price DECIMAL(6,2) NOT NULL,
  category_id INT NOT NULL REFERENCES tb_category(id),
  description TEXT,
  image_url VARCHAR(255),
  status SMALLINT DEFAULT 1,
  in_stock INT,
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tb_order (
  id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  customer_id INT NOT NULL REFERENCES tb_customer(id),
  total DECIMAL(8,2) NOT NULL,
  status SMALLINT DEFAULT 1,
  created_at TIMESTAMP NOT NULL,
  closet_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tb_order_product (
  id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  order_id INT NOT NULL REFERENCES tb_order(id),
  product_id INT NOT NULL REFERENCES tb_product(id),
  quantity INT NOT NULL
);
