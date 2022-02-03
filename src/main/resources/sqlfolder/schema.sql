DROP TABLE IF EXISTS test_table;

CREATE TABLE test_table
(
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100),
  email VARCHAR(100),
  age INT,
  PRIMARY KEY(id)
);