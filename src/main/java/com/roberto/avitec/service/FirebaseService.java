package com.roberto.avitec.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.roberto.avitec.domain.entities.Firebase;
import com.roberto.avitec.domain.models.TokenModel;
import com.roberto.avitec.repository.FirebaseRepository;
import com.roberto.avitec.utils.HeaderRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
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

    public void init() {
        try {
            File json = ResourceUtils.getFile("classpath:avitec-app.json");
            FileInputStream serviceAccount = new FileInputStream(json);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://avitec-app.firebaseio.com")
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Firebase> findAll()  {
        return firebaseRepository.findAll();
    }

    public String getToken() {
        return findAll().get(0).getToken();
    }

    public Firebase setToken(TokenModel model) {
        List<Firebase> tokens = findAll();
        if (tokens.size() == 0) {
            Firebase firebase = new Firebase();
            firebase.setToken(model.getToken());
            return firebaseRepository.save(firebase);
        } else {
            Firebase firebase = tokens.get(0);
            firebase.setToken(model.getToken());
            return firebaseRepository.save(firebase);
        }
    }

    @Async
    public CompletableFuture<String> send(HttpEntity<String> entity) {

        RestTemplate restTemplate = new RestTemplate();
        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);

        String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }

}
