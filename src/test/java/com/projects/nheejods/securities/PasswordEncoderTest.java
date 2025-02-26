package com.projects.nheejods.securities;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTest {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void testEncryptPassword() {
        String password = "12345";

        String passwordEncoded = this.passwordEncoder.encode(password);

        boolean isMatch = this.passwordEncoder.matches(password, passwordEncoded);

        assertTrue(isMatch);
    }

}
