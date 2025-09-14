CREATE TABLE IF NOT EXISTS patient (
  patient_id bigint NOT NULL AUTO_INCREMENT,
  familyname varchar(255) DEFAULT NULL,
  givenname varchar(255) NOT NULL,
  dateofbirth date NOT NULL,
  streetaddress varchar(255) DEFAULT NULL,
  sex varchar(255) NOT NULL,
  phone varchar(255) DEFAULT NULL,
  PRIMARY KEY (patient_id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- Add more CREATE TABLE statements as needed


insert into patient (patient_id ,familyname , givenname, dateofbirth, streetaddress , sex, phone  ) values( -1 , 'FName','GName', '1090-01-01', '1 Main St', 'M', '111-111-1111' );