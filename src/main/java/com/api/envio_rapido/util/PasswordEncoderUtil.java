package com.api.envio_rapido.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String senhaPura = "admin123";

        String senhaCriptografada = encoder.encode(senhaPura);

        System.out.println("------------------------------------------");
        System.out.println("Senha Pura: " + senhaPura);
        System.out.println("Senha Criptografada (Hash): " + senhaCriptografada);
        System.out.println("------------------------------------------");
    }
}
