SELECT c.`code` AS `CODE`,c.name AS COLLEGE_NAME,u.university_name AS UNIVERSITY_NAME,c.city AS CITY,c.state AS STATE,c.year_opened AS YEAR_OPENED,d.dept_name AS DEPARTMENT_NAME,e.`name` AS HOD_NAME
  FROM edu_department AS d
       JOIN edu_college_department AS cd 
       ON cd.udept_code = d.dept_code

       JOIN edu_college AS c 
       ON c.id = cd.college_id

       JOIN edu_university AS u 
       ON u.univ_code = c.univ_code

       JOIN edu_employee AS e 
       ON e.cdept_id = cd.cdept_id

       JOIN edu_designation AS ds 
       ON ds.id = e.desig_id
WHERE dept_name IN ( 'CSE', 'IT' ) 
  AND ds.`name` = 'HOD';

