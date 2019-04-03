package com.objectfrontier.training.jdbc.service;

public interface Statements {

    String CREATE_PERSON = new StringBuilder()
            .append(" INSERT INTO  person_service(first_name    ")
            .append("                           , last_name     ")
            .append("                           , email         ")
            .append("                           , address_id    ")
            .append("                           , birth_date    ")
            .append("                           , created_date) ")
            .append(" VALUES (?, ?, ?, ?, ?,?)                  ")
            .toString();

    String READ_PERSON = new StringBuilder()
            .append(" SELECT id             ")
            .append("      , first_name     ")
            .append("      , last_name      ")
            .append("      , email          ")
            .append("      , address_id     ")
            .append("      , birth_date     ")
            .append("      , created_date   ")
            .append("   FROM person_service ")
            .append("  WHERE id = ?         ")
            .toString();

    String DELETE_PERSON = new StringBuilder()
            .append(" DELETE                ")
            .append("   FROM person_service ")
            .append("  WHERE id = ?         ")
            .toString();

    String UPDATE_PERSON = new StringBuilder()
            .append(" UPDATE person_service ")
            .append("    SET first_name = ? ")
            .append("      , last_name = ?  ")
            .append("      , email = ?      ")
            .append("      , birth_date = ? ")
            .append("  WHERE id = ?         ")
            .toString();

    String READALL_PERSON = new StringBuilder()
            .append(" SELECT id             ")
            .append("      , first_name     ")
            .append("      , last_name      ")
            .append("      , email          ")
            .append("      , address_id     ")
            .append("      , birth_date     ")
            .append("      , created_date   ")
            .append("   FROM person_service ")
            .toString();

    String DELETE_ADDRESS = new StringBuilder()
            .append(" DELETE                 ")
            .append("   FROM address_service ")
            .append("  WHERE id = ?          ")
            .toString();

    String UPDATE_ADDRESS = new StringBuilder()
            .append(" UPDATE address_service ")
            .append("    SET street = ?      ")
            .append("      , city = ?        ")
            .append("      , postal_code = ? ")
            .append("  WHERE id = ?          ")
            .toString();

    String READALL_ADDRESS = new StringBuilder()
            .append(" SELECT id              ")
            .append("      , street          ")
            .append("      , city            ")
            .append("      , postal_code     ")
            .append("   FROM address_service ")
            .toString();

    String READ_ADDRESS = new StringBuilder()
            .append(" SELECT id              ")
            .append("      , street          ")
            .append("      , city            ")
            .append("      , postal_code     ")
            .append("   FROM address_service ")
            .append("  WHERE id = ?          ")
            .toString();

    String CREATE_ADDRESS = new StringBuilder()
            .append(" INSERT INTO address_service(street       ")
            .append("                           , city         ")
            .append("                           , postal_code) ")
            .append(" VALUES(?,?,?)                            ")
            .toString();
}
