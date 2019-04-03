CREATE TABLE person (
    PRIMARY KEY (id),
    id           BIGINT(20)   AUTO_INCREMENT,
    `name`       VARCHAR(50)  NOT NULL,
    email        VARCHAR(100) UNIQUE,
    address_id   BIGINT(20),
    birth_date   DATE         NOT NULL,
    created_date DATETIME ON UPDATE CURRENT_TIMESTAMP,
                 KEY k_person_addr (address_id),
                 CONSTRAINT fk_person_addr
                 FOREIGN KEY (address_id)
                 REFERENCES address (id) 
);

CREATE TABLE address (
    PRIMARY KEY (id),
    id           BIGINT(20)   AUTO_INCREMENT,
    street       VARCHAR(100) NOT NULL,
    city         VARCHAR(15)  NOT NULL,
    postal_code  INT(11)      NOT NULL
);