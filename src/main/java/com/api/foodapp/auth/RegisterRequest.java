package com.api.foodapp.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.naming.factory.SendMailFactory;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
