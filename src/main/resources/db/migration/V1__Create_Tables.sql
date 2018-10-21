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
  user_id INT,

  CONSTRAINT fk_user_id
    FOREIGN KEY (user_id)
    REFERENCES tb_user (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
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
  category_id INT,
  description TEXT,
  image_url VARCHAR(255),
  status SMALLINT DEFAULT 1,
  in_stock INT DEFAULT 0,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,

  CONSTRAINT fk_category_id
    FOREIGN KEY (category_id)
    REFERENCES tb_category (id)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS tb_order (
  id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  customer_id INT NOT NULL,
  total DECIMAL(8,2) NOT NULL,
  status SMALLINT DEFAULT 0,
  created_at TIMESTAMP NOT NULL,
  closet_at TIMESTAMP,

  CONSTRAINT fk_customer_id
    FOREIGN KEY (customer_id)
    REFERENCES tb_customer (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS tb_order_product (
  id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  order_id INT NOT NULL,
  product_id INT NOT NULL REFERENCES tb_product(id),
  quantity INT NOT NULL,

  CONSTRAINT fk_order_id
    FOREIGN KEY (order_id)
    REFERENCES tb_order (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

  CONSTRAINT fk_product_id
    FOREIGN KEY (product_id)
    REFERENCES tb_product (id)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);
