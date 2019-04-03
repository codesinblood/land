INSERT INTO edu_university (univ_code,university_name)
VALUES ('U001','Anna University'),
		 ('U002','Madurai University')
		
		 
INSERT INTO edu_college(id,`code`,`name`,univ_code,city,state,year_opened)
VALUES (1,'C101','SSN Engg college','U001','Chennai','TamilNadu',1998),
		 (2,'C102','Sairam Engg college','U001','Kanchipuram','TamilNadu',1985),
		 (3,'C103','Thangavelu Engg college','U001','Chennai','TamilNadu',1989),
		 (4,'C201','T.J Engg college','U002','Chennai','TamilNadu',2000),
		 (5,'C202','Vels Engg college','U002','Avadi','TamilNadu',1999)
		 	 
INSERT INTO edu_department (dept_code,dept_name,univ_code)
VALUES ('D101','Mechanical','U001'),
		 ('D102','Civil','U001'),
		 ('D103','CSE','U001'),
		 ('D201','IT','U002'),
		 ('D202','Automobile','U002')
	
		 
INSERT INTO edu_college_department (cdept_id,udept_code,college_id)
VALUES (101,'D101',1),
		 (102,'D103',1),
		 (201,'D102',2),
		 (301,'D101',3),
		 (302,'D102',3),
		 (401,'D201',4),
		 (501,'D201',5)
		 	 		 

INSERT INTO edu_designation (id,`name`,rank)
VALUES (1,'Principal','1'),
		 (2,'HOD','2'),
		 (3,'Professor','3'),
		 (4,'Nonteaching','4')
			
INSERT INTO edu_employee(id,`name`,dob,email,phone,college_id,cdept_id,desig_id)
VALUES (1,'Lokesh','1997-02-12','lokesh@gmail.com',7418047589,1,101,1)
		 (2,'Saravanan','1997-06-24','Saravanan@gmail.com',7418056789,1,102,2),
		 (3,'Gracya','1996-03-06','Gracya@gmail.com',9687047589,1,101,3),
		 (4,'Jasmine','1997-07-05','Jasmine@gmail.com',9618047566,1,102,3),
		 (5,'Kavya','1997-08-09','Kavya@gmail.com',7418096587,2,201,1),
		 (6,'Liza','1995-10-03','Liza@gmail.com',7968747589,2,201,3),
		 (7,'Isaac','1996-12-10','Isaac@gmail.com',9685047589,3,302,1),
		 (8,'Mani','1996-12-12','Mani@gmail.com',9964527589,3,301,2),
		 (9,'Udhayanan','1994-03-07','Udhayanan@gmail.com',9015447589,3,302,3),
		 (10,'Sabari','1994-03-02','Sabari@gmail.com',6987452311,4,401,1),
		 (11,'Siva','1998-04-08','Siva@gmail.com',9876543210,4,401,2),
		 (12,'Praveen','1996-09-12','Praveen@gmail.com',8654793212,5,501,2),
		 (13,'Bharathi','1997-10-02','Bharathi@gmail.com',9875523465,5,501,3)
		




