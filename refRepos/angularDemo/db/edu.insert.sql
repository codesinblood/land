INSERT INTO edu_university (univ_code,university_name)
VALUES ('U001','Anna University'),
       ('U002','Madurai University');
        
         
INSERT INTO edu_college(id,`code`,`name`,univ_code,city,state,year_opened)
VALUES (1,'C101','SSN Engg college','U001','Chennai','TamilNadu',1998),
       (2,'C102','Sairam Engg college','U001','Kanchipuram','TamilNadu',1985),
       (3,'C103','Thangavelu Engg college','U001','Chennai','TamilNadu',1989),
       (4,'C201','T.J Engg college','U002','Chennai','TamilNadu',2000),
       (5,'C202','Vels Engg college','U002','Avadi','TamilNadu',1999);
             
INSERT INTO edu_department (dept_code,dept_name,univ_code)
VALUES ('D101','Mechanical','U001'),
       ('D102','Civil','U001'),
       ('D103','CSE','U001'),
       ('D201','IT','U002'),
       ('D202','Automobile','U002');
    
         
INSERT INTO edu_college_department (cdept_id,udept_code,college_id)
VALUES (101,'D101',1),
       (102,'D103',1),
       (201,'D102',2),
       (301,'D101',3),
       (302,'D102',3),
       (401,'D201',4),
       (501,'D201',5);
                     

INSERT INTO edu_designation (id,`name`,rank)
VALUES (1,'Principal','1'),
       (2,'HOD','2'),
       (3,'Professor','3'),
       (4,'Nonteaching','4');
            
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
       (13,'Bharathi','1997-10-02','Bharathi@gmail.com',9875523465,5,501,3);

INSERT INTO edu_syllabus(id,cdept_id,syllabus_code,syllabus_name)
VALUES (1, 101, 'M1', 'Transforms & Partial Difference Equations'),
       (2, 102, 'CS2', 'Object Oriented Programming Structure'),
       (3, 201, 'C1', 'Concrete System'),
       (4, 301, 'M2', 'Thermal Processing'),
       (5, 301, 'M3', 'Applied Thermodynamics'),
       (6, 301, 'M4', 'Structure & Design'),
       (7, 302, 'C2', 'Structural Management'),
       (8, 401, 'I1', 'Software Testing'),
       (9, 401, 'I2', 'Web Programming Theory'),
       (10,501, 'I3', 'Artificial Intelligence');
       

INSERT INTO edu_professor_syllabus (emp_id, syllabus_id, semester)
VALUES (1, 1, 3),
       (2, 2, 2),
       (3, 3, 4),
       (4, 1, 1),
       (5, 6, 5),
       (6, 7, 6),
       (7, 8, 5),
       (8, 3, 2),
       (9, 4, 8),
       (10, 10, 7);
INSERT INTO edu_student (id, roll_no, `name`, dob, gender, email, phone, address, acadamic_year, cdept_id, college_id)
VALUES (1, '101CS131', 'Ananthi', '1995-10-02', 'F', 'ananthiperky@gmail.com', 9600504878, 'Chennai', 2013, 102, 1),
       (2, '101CS152', 'Boobalan', '1996-09-16', 'M', 'boobalan@gmail.com', 8546776478, 'Salem', 2015, 102, 1),
       (3, '101M131', 'Elakkiya', '1996-03-01', 'F', 'elakkiyaammulu@gmail.com', 8546455447, 'Erode', 2013, 101, 1),
       (4, '101M142', 'Farheen', '1996-03-07', 'F', 'farheenkhanfarheen@gmail.com', 7708145657, 'Dharmapuri', 2014, 101, 1),
       (5, '101M143', 'Gokul', '1996-02-19', 'M', 'gokul.a@gmail.com', 8542346578, 'Edapady', 2014, 101,1),
       (6, '102C151','Gowri', '1996-02-03', 'M', 'gowri.shankar02@gmail.com', 8148546245, 'Edapady', 2015, 201, 2),
       (7, '102C132', 'Hamsa', '1995-07-24', 'F', 'hamsa.priya@gmail.com', 9784565785, 'Kumarapalayam', 2013, 201, 2),
       (8, '103M141', 'Hari Priya', '1996-09-18', 'F', 'haripriya@gmail.com', 7474572658, 'Mettur', 2012, 301,3),
       (9, '103M152', 'Karthik', '1996-09-25', 'M', 'karthik@gmail.com', 9568427587, 'Salem', 2015, 301, 3),
       (10, '103C141', 'Mani', '1996-10-26', 'M', 'mani.bharathi@gmail.com', 9878965432, 'Salem', 2014,302, 3),
       (11, '103C132', 'Manjunath', '1996-08-31', 'M', 'manjunath@gmail.com', 8015465321, 'Salem', 2013,302, 3),
       (12, '103C143', 'Priya', '1996-02-15', 'F', 'priyadharshini@gmail.com', 9784565245, 'Karnataka', 2014, 302, 3),
       (13, '103C144', 'Pavithra', '1996-05-27', 'F', 'pavipearl@gmail.com', 9445787631, 'Coimbatore', 2014,302, 3),
       (14, '201I131', 'Poovarasan', '1996-06-23', 'M', 'poovcse@gmail.com', 7373504188, 'Radipuram', 2013,401, 4),
       (15, '201I162', 'Rajalakshmi', '1996-05-06', 'F', 'priyankanagarajan@gmail.com', 8478213564, 'Karur', 2016, 401, 4),
       (16, '201I142', 'Renuka', '1996-11-02', 'F', 'renuka@gmail.com', 9785463654, 'Sankari', 2014, 401, 4),
       (17, '201I143', 'Rekhaa', '1997-05-21', 'F', 'rekhaacs@gmail.com', 8745432478, 'vellore', 2014,401, 4),
       (18, '202I131', 'Sabari', '1996-05-20', 'M', 'sabarikannan20@gmail.com', 9600504878, 'Coimbatore', 2013, 501, 5),
       (19, '202I122', 'Suresh Kumar', '1996-08-13', 'M', 'sureshkumarr@gmail.com', 8457624542, 'Rakkipatty', 2012,501,5),
       (20, '202I133', 'Sudharsan', '1996-08-28', 'M', 'sudharsandr@gmail.com', 7598440233, '4 Roads', 2013, 501, 5);
   
   
 INSERT INTO edu_semester_result (stud_id, syllabus_id, semester, grade, credits, result_date)
VALUES (1, 1, 3, '9', 8, '2017-07-23'),
       (2, 2, 2, '8', 6, '2017-07-23'),
       (3, 4, 8, '10', 5, '2017-02-05'),
       (4, 4, 8, '6', 7, '2017-07-23'),
       (5, 1, 1, '7', 6, '2017-02-05'),
       (6, 1, 3, '4', 6, '2017-07-23'),
       (7, 6, 5, '8', 7, '2017-07-23'),
       (8, 8, 5, '9', 7, '2017-07-23'),
       (13, 10, 7, '10', 9, '2017-07-23'),
       (15, 10, 7, '9', 7, '2017-02-05');
    
       
       
       
INSERT INTO edu_semester_fee (cdept_id, stud_id, semester)
VALUES (101, 4, 7),
       (102, 1, 5),
       (501, 20, 6),
       (201, 6, 8),
       (301, 9, 4),
       (401, 10, 5);

