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


insert into patient (patient_id ,familyname ,givenname, dateofbirth, sex, streetaddress, phone) values(1,'Ferguson','Lucas','1968-06-22','M','2 Warren Street','387-866-1399');
insert into patient (patient_id ,familyname , givenname, dateofbirth, sex, streetaddress, phone) values(2,'Rees','Pippa','1952-09-27','F','745 West Valley Farms Drive','628-423-0993');
insert into patient (patient_id ,familyname , givenname, dateofbirth, sex, streetaddress, phone) values(3,'Arnold','Edward','1952-11-11','M','599 East Garden Ave','123-727-2779');
insert into patient (patient_id ,familyname , givenname, dateofbirth, sex, streetaddress, phone) values(4,'Sharp','Anthony','1946-11-26','M','894 Hall Street','451-761-8383');
insert into patient (patient_id ,familyname , givenname, dateofbirth, sex, streetaddress, phone) values(5,'Ince','Wendy','1958-06-29','F','4 Southampton Road','802-911-9975');
insert into patient (patient_id ,familyname , givenname, dateofbirth, sex, streetaddress, phone) values(6,'Ross','Tracey','1949-12-07','F','40 Sulphur Springs Dr','131-396-5049');
insert into patient (patient_id ,familyname , givenname, dateofbirth, sex, streetaddress, phone) values(7,'Wilson','Claire','1966-12-31','F','12 Cobblestone St','300-452-1091');
insert into patient (patient_id ,familyname , givenname, dateofbirth, sex, streetaddress, phone) values(8,'Buckland','Max','1945-06-24','M','193 Vale St','833-534-0864');
insert into patient (patient_id ,familyname , givenname, dateofbirth, sex, streetaddress, phone) values(9,'Clark','Natalie','1964-06-18','F','12 Beechwood Road','241-467-9197');
insert into patient (patient_id ,familyname , givenname, dateofbirth, sex, streetaddress, phone) values(10,'Bailey','Piers','1959-06-28','M','1202 Bumble Dr','747-815-0557');