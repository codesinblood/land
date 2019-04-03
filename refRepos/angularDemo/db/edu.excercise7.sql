UPDATE edu_semester_fee 
   SET paid_year = '2018',
       paid_status = 'PAID' 
 WHERE stud_id = (SELECT id FROM edu_student WHERE roll_no = '101CS131'); 

UPDATE edu_semester_fee 
   SET paid_year = '2018',
       paid_status = 'PAID' 
 WHERE stud_id IN (SELECT id FROM edu_student WHERE roll_no IN ('103M152','102C151'));