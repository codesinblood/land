ALTER TABLE edu_semester_fee 
     MODIFY paid_status VARCHAR(100) DEFAULT 'UNPAID', 
     MODIFY amount      DOUBLE(18,2) DEFAULT 30000;
       
INSERT INTO edu_semester_fee (cdept_id, stud_id, semester)
VALUES (101, 4, 7),
       (102, 1, 5),
       (501, 20,6),
       (201, 6, 8),
       (301, 9, 4),
       (401, 10, 5);

