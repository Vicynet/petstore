DROP USER IF EXISTS 'petuser'@'localhost';
CREATE USER 'petuser'@'localhost' IDENTIFIED BY 'petuser123';
GRANT ALL PRIVILEGES ON petstore_db.* TO 'petuser'@'localhost';

DROP DATABASE IF EXISTS petstore_db;
CREATE DATABASE petstore_db;