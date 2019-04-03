package com.objectfrontier.training.web.service;

public interface Statements {

    String CREATE_PERSON = new StringBuilder()
            .append(" INSERT INTO  person(first_name            ")
            .append("                           , last_name     ")
            .append("                           , email         ")
            .append("                           , address_id    ")
            .append("                           , birth_date    ")
            .append("                           , created_date  ")
            .append("                           , password      ")
            .append("                           , isAdmin)      ")
            .append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?)           ")
            .toString();

    String READ_PERSON = new StringBuilder()
            .append(" SELECT id             ")
            .append("      , first_name     ")
            .append("      , last_name      ")
            .append("      , email          ")
            .append("      , address_id     ")
            .append("      , birth_date     ")
            .append("      , created_date   ")
            .append("      , password       ")
            .append("      , isAdmin        ")
            .append("   FROM person         ")
            .append("  WHERE id = ?         ")
            .toString();

    String DELETE_PERSON = new StringBuilder()
            .append(" DELETE                ")
            .append("   FROM person         ")
            .append("  WHERE id = ?         ")
            .toString();

    String UPDATE_ADMIN = new StringBuilder()
            .append(" UPDATE person         ")
            .append("    SET first_name = ? ")
            .append("      , last_name = ?  ")
            .append("      , email = ?      ")
            .append("      , birth_date = ? ")
            .append("      , password = ?   ")
            .append("      , isAdmin = ?    ")
            .append("  WHERE id = ?         ")
            .toString();

    String UPDATE_PERSON = new StringBuilder()
            .append(" UPDATE person         ")
            .append("    SET first_name = ? ")
            .append("      , last_name = ?  ")
            .append("      , email = ?      ")
            .append("      , birth_date = ? ")
            .append("      , password = ?   ")
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
            .append("      , password       ")
            .append("      , isAdmin        ")
            .append("   FROM person         ")
            .toString();

    String DELETE_ADDRESS = new StringBuilder()
            .append(" DELETE                 ")
            .append("   FROM address         ")
            .append("  WHERE id = ?          ")
            .toString();

    String UPDATE_ADDRESS = new StringBuilder()
            .append(" UPDATE address         ")
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
            .append("   FROM address         ")
            .toString();

    String READ_ADDRESS = new StringBuilder()
            .append(" SELECT id              ")
            .append("      , street          ")
            .append("      , city            ")
            .append("      , postal_code     ")
            .append("   FROM address         ")
            .append("  WHERE id = ?          ")
            .toString();

    String CREATE_ADDRESS = new StringBuilder()
            .append(" INSERT INTO address(street       ")
            .append("                   , city         ")
            .append("                   , postal_code) ")
            .append(" VALUES(?,?,?)                    ")
            .toString();

    String VALIDATE_USER = new StringBuilder()
            .append(" SELECT id           ")
            .append("      , first_name   ")
            .append("      , last_name    ")
            .append("      , email        ")
            .append("      , address_id   ")
            .append("      , birth_date   ")
            .append("      , created_date ")
            .append("      , password     ")
            .append("      , isAdmin      ")
            .append("   FROM person       ")
            .append("  WHERE email    = ? ")
            .append("    AND password = ? ")
            .toString();

    String VALIDATE_NAME_CREATE = new StringBuilder()
            .append(" SELECT first_name     ")
            .append("      , last_name      ")
            .append("   FROM person       ")
            .append("  WHERE first_name = ? ")
            .append("    AND last_name  = ? ")
            .toString();

    String VALIDATE_NAME_UPDATE= new StringBuilder()
            .append(" SELECT first_name     ")
            .append("      , last_name      ")
            .append("   FROM person       ")
            .append("  WHERE first_name = ? ")
            .append("    AND last_name  = ? ")
            .append("    AND id != ?         ")
            .toString();

    String VALIDATE_EMAIL_CREATE = new StringBuilder()
            .append(" SELECT email         ")
            .append("   FROM person       ")
            .append("  WHERE email = ? ")
            .toString();

    String VALIDATE_EMAIL_UPDATE= new StringBuilder()
            .append(" SELECT email         ")
            .append("   FROM person       ")
            .append("  WHERE email = ? ")
            .append("    AND    id != ?      ")
            .toString();

}