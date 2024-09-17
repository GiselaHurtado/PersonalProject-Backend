package dev.gisela.paddle_tennis_couch_backend.implementations;

public interface IEncryptFacade {

    String encode(String type, String data);

    String decode(String type, String data);

}
