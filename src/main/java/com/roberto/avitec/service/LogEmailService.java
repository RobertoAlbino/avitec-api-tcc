package com.roberto.avitec.service;

import com.roberto.avitec.domain.entities.LogEmail;
import com.roberto.avitec.domain.enums.ETipoEmail;
import com.roberto.avitec.repository.LogEmailRepository;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogEmailService {
    private final Logger log = LoggerFactory.getLogger(EmailService.class);
    private LogEmailRepository logEmailRepository;

    @Autowired
    public LogEmailService(LogEmailRepository logEmailRepository) {
        this.logEmailRepository = logEmailRepository;
    }

    public void gravarLogEnvioEmail(String assunto, String corpo, String destinatarios, ETipoEmail tipo) {
        LogEmail logEmail = new LogEmail();
        logEmail.setAssunto(assunto);
        logEmail.setCorpo(corpo);
        logEmail.setDataEnvio(DateTime.now().toDate());
        logEmail.setDestino(destinatarios);
        logEmail.setTipoEmail(tipo);
        logEmailRepository.save(logEmail);
    }
}
