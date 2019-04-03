SELECT s.id,s.roll_no,s.`name`,s.dob,s.gender,s.gender,s.email,s.phone,s.address,s.acadamic_year,sr.grade,sr.credits,c.`name`,
         ((  sr.credits * sr.grade ) / sr.credits) AS GPA
  FROM edu_student AS s
       INNER JOIN edu_college AS c
       ON c.id = s.college_id

       INNER JOIN edu_semester_result AS sr
       ON s.id = sr.stud_id
ORDER BY c.`name`, sr.semester
LIMIT 0,10;