CREATE TABLE customer (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  cruks BOOLEAN NOT NULL
);