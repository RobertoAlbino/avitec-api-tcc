package com.roberto.avitec.service;

import com.roberto.avitec.domain.entities.Firebase;
import com.roberto.avitec.domain.enums.TipoEnvioPush;
import com.roberto.avitec.domain.models.TokenModel;
import com.roberto.avitec.repository.FirebaseRepository;
import com.roberto.avitec.utils.DateUtils;
import com.roberto.avitec.utils.HeaderRequestInterceptor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public class FirebaseService {

    private FirebaseRepository firebaseRepository;

    @Autowired
    public FirebaseService(FirebaseRepository firebaseRepository) {
        this.firebaseRepository = firebaseRepository;
    }

    private static final String FIREBASE_SERVER_KEY = "AAAA8vG-Ss0:APA91bFDQ-q0mhf0XnW4chbP5k1ZahVevZ37X2dB_YwjhjRuTEtbkKbyrCoKg38exlb2SLP17UnBaDUkv_wuxyiERPCM0acar9u5G2Yktw6XbmV_xZFuwdB9oP0I2U18OGdyP5RvfP3r";
    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

    public List<Firebase> findAll()  {
        return firebaseRepository.findAll();
    }

    public Firebase findByTipoEnvio(TipoEnvioPush tipoEnvio) {
        return firebaseRepository.findByTipoEnvio(tipoEnvio);
    }

    public String getToken() {
        return findAll().get(0).getToken();
    }

    public Date getUltimaDataEnvioPush(TipoEnvioPush tipoEnvio) {
        Firebase firebase = findByTipoEnvio(tipoEnvio);
        if (firebase != null) {
            return firebase.getUltimoEnvioPush();
        }
        return null;
    }

    public Integer getIntervaloEnvioPush() {
        List<Firebase> firebase = findAll();
        if (firebase.get(0).getIntervaloEnvio() != null) {
            return firebase.get(0).getIntervaloEnvio();
        } else {
            for (Firebase firebaseRow : firebase) {
                firebaseRow.setIntervaloEnvio(5);
                firebaseRepository.save(firebase);
            }
            return firebase.get(0).getIntervaloEnvio();
        }
    }

    public List<Firebase> setToken(TokenModel model) {
        List<Firebase> tokens = findAll();
        if (tokens.size() == 0) {
            Firebase firebase = new Firebase();
            firebase.setToken(model.getToken());
            firebaseRepository.save(firebase);
            return tokens;
        } else {
            for (Firebase token: tokens) {
                token.setToken(model.getToken());
                firebaseRepository.save(token);
            }
            return tokens;
        }
    }

    public void saveDateEnvioPush(TipoEnvioPush tipoEnvio) {
        Firebase firebase = findByTipoEnvio(tipoEnvio);
        if (firebase == null) {
            firebase = new Firebase();
            firebase.setToken(getToken());
            firebase.setUltimoEnvioPush(DateUtils.now());
            firebase.setTipoEnvio(tipoEnvio);
            firebaseRepository.save(firebase);
        } else {
            firebase.setUltimoEnvioPush(DateUtils.now());
            firebaseRepository.save(firebase);
        }
    }

    public List<Firebase> setIntervaloEnvio(Integer intervalo) {
        List<Firebase> firebaseRows = findAll();
        for (Firebase firebase : firebaseRows) {
            firebase.setIntervaloEnvio(intervalo);
            firebaseRepository.save(firebase);
        }
        return firebaseRows;
    }

    public void validateEnvioPermitidoAndSendPush(String header, String message, TipoEnvioPush tipoEnvio) {
        Date ultimoEnvioPush = getUltimaDataEnvioPush(tipoEnvio);
        if (ultimoEnvioPush == null) {
            send(header, message, tipoEnvio);
            return;
        }
        Long diff = DateUtils.now().getTime() - ultimoEnvioPush.getTime();
        Integer diferencaMinutos = (int)(diff / (60 * 1000));
        if (getIntervaloEnvioPush() == 0 || diferencaMinutos > getIntervaloEnvioPush()) {
            send(header, message, tipoEnvio);
        }
    }

    @Async
    protected void send(String header, String message, TipoEnvioPush tipoEnvio) {
        try {
            JSONObject body = new JSONObject();
            body.put("to", getToken());
            body.put("priority", "high");

            JSONObject notification = new JSONObject();
            notification.put("title", header);
            notification.put("body", message);

            JSONObject data = new JSONObject();
            data.put("Key-1", "JSA Data 1");
            data.put("Key-2", "JSA Data 2");

            body.put("notification", notification);
            body.put("data", data);

            HttpEntity<String> request = new HttpEntity<>(body.toString());

            RestTemplate restTemplate = new RestTemplate();
            ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
            interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
            interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
            restTemplate.setInterceptors(interceptors);

            String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, request, String.class);
            CompletableFuture.completedFuture(firebaseResponse);
            saveDateEnvioPush(tipoEnvio);
        } catch (JSONException | RuntimeException ex) {
            throw new RuntimeException(ex);
        }
    }

}
