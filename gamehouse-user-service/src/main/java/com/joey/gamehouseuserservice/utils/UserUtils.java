package com.joey.gamehouseuserservice.utils;

import com.joey.gamehouseuserservice.domain.records.LoginAuthenticationDTO;
import com.joey.gamehouseuserservice.domain.records.RegisterAuthenticationDTO;
import com.joey.gamehouseuserservice.domain.records.UpdateUserRequest;

public class UserUtils {

    public static Boolean isThereAnyFieldsNull (RegisterAuthenticationDTO data) {
        return data.email().isEmpty() || data.password().isEmpty() || data.confirmPassword().isEmpty();
    }

    public static Boolean isThereAnyFieldsNull (LoginAuthenticationDTO data) {
        return data.email().isEmpty() || data.password().isEmpty();
    }

    public static Boolean isThereAnyFieldsNull (UpdateUserRequest data) {
        return data.oldEmail().isEmpty() || data.newEmail().isEmpty();
    }

    public static boolean isEmailsEquals(String oldEmail, String newEmail) {
        return oldEmail.equals(newEmail);
    }
}
