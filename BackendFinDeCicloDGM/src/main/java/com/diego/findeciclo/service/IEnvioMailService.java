package com.diego.findeciclo.service;

public interface IEnvioMailService {

    void sendEmail(String to, String subject, String content);

}
