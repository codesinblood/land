package com.objectfrontier.training.jdbc.service;

public interface Statements {

    String CREATE_PERSON = new StringBuilder().append("INSERT INTO  person_service (first_name, last_name , email, address_id, birth_date, created_date)")
                                              .append("VALUES (?, ?, ?, ?, ?,?)                                                                         ").toString();

    String READ_PERSON = new StringBuilder().append("SELECT id, first_name , last_name , email, address_id, birth_date, created_date ")
                                            .append("FROM person_service                                                             ")
                                            .append("WHERE id = ?                                                                    ").toString();

    String DELETE_PERSON = new StringBuilder().append("DELETE              ")
                                              .append("FROM person_service ")
                                              .append("WHERE id = ?        ").toString();


    String UPDATE_PERSON = new StringBuilder().append("UPDATE person_service                                        ")
                                              .append("SET first_name = ?, last_name = ?, email = ?, birth_date = ? ")
                                              .append("WHERE id = ?                                                 ").toString();

    String READALL_PERSON = new StringBuilder().append("SELECT id, first_name , last_name , email, address_id, birth_date, created_date ")
                                               .append("FROM person                                                            ").toString();

    String DELETE_ADDRESS = new StringBuilder().append("DELETE FROM address_service WHERE id = ?").toString();

    String UPDATE_ADDRESS = new StringBuilder().append("UPDATE address_service SET street = ?, city = ?, postal_code = ? WHERE id = ?").toString();

    String READALL_ADDRESS = new StringBuilder().append("SELECT id, street, city, postal_code FROM address_service").toString();

    String READ_ADDRESS = new StringBuilder().append("SELECT id, street, city, postal_code FROM address WHERE id = ?").toString();

    String CREATE_ADDRESS = new StringBuilder().append("insert into address_service(street, city, postal_code) values(?,?,?)").toString();


}
