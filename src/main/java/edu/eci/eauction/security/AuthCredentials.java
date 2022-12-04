package edu.eci.eauction.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String userName;
    private String password;
}
