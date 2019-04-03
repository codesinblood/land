SELECT c.`code` AS `CODE`,c.name AS COLLEGE_NAME,u.university_name AS UNIVERSITY_NAME,c.city AS CITY,c.state AS STATE,c.year_opened AS YEAR_OPENED,d.dept_name AS DEPARTMENT_NAME,e.`name` AS HOD_NAME
FROM edu_department AS d 
INNER JOIN edu_college AS c ON c.univ_code = d.univ_code
INNER JOIN edu_university AS u ON u.univ_code = c.univ_code
INNER JOIN edu_employee AS e ON c.id = e.college_id 
WHERE d.dept_name 
IN ('IT','CSE')
AND
e.desig_id = 2  

