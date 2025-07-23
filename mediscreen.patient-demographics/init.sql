CREATE DATABASE IF NOT EXISTS patient-demographics;
USE patient-demographics;

CREATE TABLE IF NOT EXISTS patient (
    id INT AUTO_INCREMENT PRIMARY KEY,
    column1 VARCHAR(255),
    column2 INT
);

CREATE TABLE IF NOT EXISTS patient (
  patient_id bigint NOT NULL AUTO_INCREMENT,
  familyname varchar(255) DEFAULT NULL,
  givenname varchar(255) NOT NULL,
  dateofbirth date(6) NOT NULL,
  streetaddress varchar(255) DEFAULT NULL,
  sex varchar(255) NOT NULL,
  phone varchar(255) DEFAULT NULL,
  PRIMARY KEY (patient_id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
-- Add more CREATE TABLE statements as needed

-- INSERT statement
INSERT INTO patient (patient_id,familyname,givenname,dateofbirth,streetaddress,sex,phone) VALUES (-1,'John','Smith','1987-05-12 00:00:00.000000','1 Main St, 19999','M','1234567896');
INSERT INTO patient (patient_id,familyname,givenname,dateofbirth,streetaddress,sex,phone) VALUES (-2,'Jessica','Parker','1997-05-11 00:00:00.000000','5 Roder Road, 19999','F','1234500000');