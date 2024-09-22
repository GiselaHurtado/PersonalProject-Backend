package dev.gisela.paddle_tennis_couch_backend.facades;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.gisela.paddle_tennis_couch_backend.facades.encryptations.Base64Encoder;
import dev.gisela.paddle_tennis_couch_backend.implementations.IEncryptFacade;

@Component
public class EncoderFacade implements IEncryptFacade {

    private final PasswordEncoder bCryptPasswordEncoder;
    private final Base64Encoder base64Encoder;

    public EncoderFacade(PasswordEncoder bCryptPasswordEncoder, Base64Encoder base64Encoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.base64Encoder = base64Encoder;
    }

    @Override
    public String encode(String type, String data) {
        if ("bcrypt".equalsIgnoreCase(type)) {
            return bCryptPasswordEncoder.encode(data);
        } else if ("base64".equalsIgnoreCase(type)) {
            return base64Encoder.encode(data);
        }
        throw new IllegalArgumentException("Tipo de codificación no soportado: " + type);
    }

    @Override
    public String decode(String type, String data) {
        if ("base64".equalsIgnoreCase(type)) {
            return base64Encoder.decode(data);
        }
        throw new IllegalArgumentException("Tipo de decodificación no soportado: " + type);
    }
}
