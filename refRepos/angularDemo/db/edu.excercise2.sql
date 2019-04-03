SELECT s.roll_no, s.`name`, s.gender, s.dob, s.email, s.phone, s.address, c.`name`, d.dept_name,c.city
  FROM edu_student AS s
       JOIN edu_college AS c 
       ON s.college_id =c.id 

       JOIN edu_college_department AS cd 
       ON s.cdept_id = cd.cdept_id 

       JOIN edu_department AS d 
       ON  cd.udept_code = d.dept_code 

       JOIN edu_university AS u 
       ON  u.univ_code = c.univ_code 
WHERE  s.acadamic_year = 2015 
  AND c.city = 'chennai'
  AND u.univ_code = 'U001';