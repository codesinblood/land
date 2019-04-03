SELECT s.roll_no, s.name AS student_name, s.gender, s.dob, s.email, s.phone, s.address, c.name AS college_name, d.dept_name,  e.name AS HOD_Name,c.city
    FROM edu_college AS c 
       INNER JOIN edu_college_department AS cd
       ON c.id = cd.college_id
      

       INNER JOIN edu_student AS s
       ON cd.cdept_id = s.cdept_id 

       INNER JOIN edu_department AS d
       ON cd.udept_code = d.dept_code

       INNER JOIN edu_university AS u
       ON c.univ_code = u.univ_code
        

       INNER JOIN edu_employee AS e
       ON e.college_id = s.college_id 
               
       INNER JOIN edu_designation AS ds
       ON e.desig_id = ds.id 
       WHERE ds.name = 'HOD'
        AND c.city = 'Chennai'
        AND  u.university_name = 'Anna university'
        LIMIT 0,10;