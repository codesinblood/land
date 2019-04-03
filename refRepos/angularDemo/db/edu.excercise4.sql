SELECT *
  FROM edu_department AS d
       INNER JOIN edu_college_department AS cd
       ON d.dept_code = cd.udept_code

       INNER JOIN edu_employee AS e 
       ON cd.cdept_id = e.cdept_id 

       INNER JOIN edu_designation AS dn
       ON   e.desig_id = dn.id 

       INNER JOIN edu_college AS c 
       ON c.id = e.college_id

       INNER JOIN edu_university AS u 
       ON u.univ_code = c.univ_code
WHERE u.univ_code = 'U001' 
ORDER BY c.`name` , dn.rank ASC;