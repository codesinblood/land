CREATE TABLE edu_university (
    PRIMARY KEY(univ_code),
    univ_code       CHAR(4),
    university_name VARCHAR(100) NOT NULL
);
    
CREATE TABLE edu_college (
    PRIMARY KEY(id),
    id          INT,
    `code`      CHAR(4)      NOT NULL,
    `name`      VARCHAR(100) NOT NULL,
    univ_code   CHAR(4) NOT  NULL,
    city        VARCHAR(50)  NOT NULL,
    state       VARCHAR(50)  NOT NULL,
    year_opened YEAR(4)      NOT NULL,
                KEY k_clg_univ (univ_code),
                CONSTRAINT fk_clg_univ 
                FOREIGN KEY (univ_code) 
                REFERENCES edu_university (univ_code) ON DELETE NO ACTION ON UPDATE NO ACTION

);

CREATE TABLE edu_department (
    PRIMARY KEY(dept_code),
    dept_code CHAR(4),
    dept_name VARCHAR(50) NOT NULL,
    univ_code CHAR(4)     NOT NULL,
              KEY k_dept_univ (univ_code),
              CONSTRAINT fk_dept_univ 
              FOREIGN KEY (univ_code)
              REFERENCES edu_university (univ_code) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE edu_college_department (
    PRIMARY KEY(cdept_id),
    cdept_id   INT, 
    udept_code CHAR(4) NOT NULL,
    college_id INT     NOT NULL,
               KEY k_cdept_udept (udept_code),
               CONSTRAINT fk_cdept_udept 
               FOREIGN KEY (udept_code)
               REFERENCES edu_department (dept_code) ON DELETE NO ACTION ON UPDATE NO ACTION, 
               KEY k_cdept_clg (college_id),
               CONSTRAINT fk_cdept_clg 
               FOREIGN KEY (college_id)
               REFERENCES edu_college (id) ON DELETE NO ACTION ON UPDATE NO ACTION 
);

CREATE TABLE edu_designation (
    PRIMARY KEY(id),
    id     INT,
    `name` VARCHAR(30) NOT NULL,
    rank   CHAR(1) NOT NULL 
);

CREATE TABLE edu_employee (
    PRIMARY KEY(id),
    id         INT,
    `name`     VARCHAR(100) NOT NULL,
    dob        DATE         NOT NULL,
    email      VARCHAR(50)  NOT NULL,
    phone      BIGINT       NOT NULL,
    college_id INT          NOT NULL,
    cdept_id   INT          NOT NULL,
    desig_id   INT          NOT NULL,
               KEY k_emp_desig (desig_id),
               CONSTRAINT fk_emp_desig 
               FOREIGN KEY (desig_id)
               REFERENCES edu_designation (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
               KEY k_emp_cdept (cdept_id),
               CONSTRAINT fk_emp_cdept 
               FOREIGN KEY (cdept_id)
               REFERENCES edu_college_department (cdept_id) ON DELETE NO ACTION ON UPDATE NO ACTION ,
               KEY k_emp_clg (college_id),
               CONSTRAINT fk_emp_clg 
               FOREIGN KEY (college_id)
               REFERENCES edu_college (id) ON DELETE NO ACTION ON UPDATE NO ACTION
     
);

CREATE TABLE edu_syllabus (
    PRIMARY KEY(id),
    id            INT,
    cdept_id      INT NOT NULL,
    syllabus_code CHAR(4) NOT NULL,
    syllabus_name VARCHAR(190) NOT NULL,
                  KEY k_sylb_cdept (cdept_id),
                  CONSTRAINT fk_sylb_cdept 
                  FOREIGN KEY (cdept_id)
                  REFERENCES edu_college_department (cdept_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);


CREATE TABLE edu_professor_syllabus (
    PRIMARY KEY(id),
    id          INT,
    syllabus_id INT NOT NULL,
    semester    TINYINT NOT NULL,
                KEY k_prsylb_sylb (syllabus_id),
                CONSTRAINT fk_prsylb_sylb 
                FOREIGN KEY (syllabus_id)
                REFERENCES edu_syllabus (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE edu_student (
    PRIMARY KEY(id),
    id            INT,
    roll_no       CHAR(8) NOT NULL,
    `name`        VARCHAR(100) NOT NULL,
    dob           DATE NOT NULL,
    gender        CHAR(1) NOT NULL,
    email         VARCHAR(50) NOT NULL,
    phone         BIGINT NOT NULL,
    address       VARCHAR(200) NOT NULL,
    acadamic_year YEAR NOT NULL,
    cdept_id      INT NOT NULL,
    college_id    INT NOT NULL,
                  KEY k_stud_cdept (cdept_id),
                  CONSTRAINT fk_stud_cdept 
                  FOREIGN KEY (cdept_id)
                  REFERENCES edu_college_department (cdept_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
                  KEY k_stud_clg (college_id),
                  CONSTRAINT fk_stud_clg 
                  FOREIGN KEY (college_id)
                  REFERENCES edu_college (id) ON DELETE NO ACTION ON UPDATE NO ACTION
     
);

CREATE TABLE edu_semester_fee (
    
    cdept_id    INT NOT NULL,
    stud_id     INT NOT NULL,
    semester    TINYINT NOT NULL,
    amount      DOUBLE(18,2) NULL,
    paid_year   YEAR NULL, 
    paid_status VARCHAR(10) NOT NULL,
                KEY k_semfee_cdept (cdept_id),
                CONSTRAINT fk_semfee_cdept 
                FOREIGN KEY (cdept_id)
                REFERENCES edu_college_department (cdept_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
                KEY k_semfee_stud (stud_id),
                CONSTRAINT fk_semfee_stud 
                FOREIGN KEY (stud_id)
                REFERENCES edu_student (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE edu_semester_result (
    
    stud_id     INT NOT NULL,
    syllabus_id INT NOT NULL,
    semester    TINYINT NOT NULL,
    grade       VARCHAR(10) NOT NULL,
    credits     FLOAT NOT NULL,
    result_date DATE NOT NULL,
                KEY k_semres_stud (stud_id),
                CONSTRAINT fk_semres_stud 
                FOREIGN KEY (stud_id)
                REFERENCES edu_student (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
                KEY k_semres_sylb (syllabus_id),
                CONSTRAINT fk_semres_sylb 
                FOREIGN KEY (syllabus_id)
                REFERENCES edu_syllabus (id) ON DELETE NO ACTION ON UPDATE NO ACTION
         
);
