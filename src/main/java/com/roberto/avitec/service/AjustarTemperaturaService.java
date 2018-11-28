package com.roberto.avitec.service;

import com.roberto.avitec.domain.enums.ETipoEmail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.GregorianCalendar;

@Service
@Transactional
public class AjustarTemperaturaService {

    public static Integer temperatura = null;

    private static void notificarUsuario(Integer temperatura) throws Exception {
        GregorianCalendar calendar = new GregorianCalendar();
        Integer diaHoje = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        Boolean deveEnviarNotificacao = false;
        if (diaHoje <= 4 && (temperatura < 30 || temperatura > 33)) {
            deveEnviarNotificacao = true;
        } else if (diaHoje > 4 && diaHoje <= 8 && (temperatura < 29 || temperatura > 32)) {
            deveEnviarNotificacao = true;
        } else if (diaHoje > 8 && diaHoje <= 12 && (temperatura < 27 || temperatura > 31)) {
            deveEnviarNotificacao = true;
        } else if (diaHoje > 12 && diaHoje <= 16 && (temperatura < 25 || temperatura > 31)) {
            deveEnviarNotificacao = true;
        } else if (diaHoje > 16 && diaHoje <= 20 && (temperatura < 23 || temperatura > 30)) {
            deveEnviarNotificacao = true;
        } else if (diaHoje > 20 && diaHoje <= 24 && (temperatura < 21 || temperatura > 29)) {
            deveEnviarNotificacao = true;
        } else if (diaHoje > 24 && diaHoje <= 28 && (temperatura < 20 || temperatura > 28)) {
            deveEnviarNotificacao = true;
        } else if (diaHoje > 28 && diaHoje <= 32 && (temperatura < 19 || temperatura > 28)) {
            deveEnviarNotificacao = true;
        } else if (diaHoje > 32 && diaHoje <= 34 && (temperatura < 19 || temperatura > 28)) {
            deveEnviarNotificacao = true;
        } else if (diaHoje > 34 && diaHoje <= 38 && (temperatura < 19 || temperatura > 28)) {
            deveEnviarNotificacao = true;
        } else if (diaHoje > 38 && diaHoje <= 42 && (temperatura < 19 || temperatura > 28)) {
            deveEnviarNotificacao = true;
        } else if (diaHoje > 42 && diaHoje <= 44 && (temperatura < 19 || temperatura > 28)) {
            deveEnviarNotificacao = true;
        } else if (diaHoje > 44 && diaHoje <= 46 && (temperatura < 19 || temperatura > 28)) {
            deveEnviarNotificacao = true;
        } else if (diaHoje > 46 && diaHoje <= 48 && (temperatura < 19 || temperatura > 28)) {
            deveEnviarNotificacao = true;
        } else if (diaHoje > 48 && diaHoje <= 52 && (temperatura < 19 || temperatura > 28)) {
            deveEnviarNotificacao = true;
        } else if (diaHoje > 52 && diaHoje <= 56 && (temperatura < 19 || temperatura > 28)) {
            deveEnviarNotificacao = true;
        } else if (temperatura > 50) {
            deveEnviarNotificacao = true;
        }

        if (deveEnviarNotificacao) {
            new EmailService("Anomalia na temperatura", "Existe uma anomalia na temperatura do aviário", ETipoEmail.ANOMALIA).enviarEmail();
        }
    }

    public Integer getTemperatura() {
        return this.temperatura;
    }

    public Integer setTemperatura(Long alojamento, Integer temperatura) throws Exception {
        notificarUsuario(temperatura);
        this.temperatura = temperatura;
        return  temperatura;
    }
}
