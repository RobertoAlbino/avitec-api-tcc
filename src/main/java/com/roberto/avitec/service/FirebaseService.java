package com.roberto.avitec.service;

import com.roberto.avitec.domain.entities.Firebase;
import com.roberto.avitec.domain.models.TokenModel;
import com.roberto.avitec.repository.FirebaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class FirebaseService {

    private FirebaseRepository firebaseRepository;

    @Autowired
    public FirebaseService(FirebaseRepository firebaseRepository) {
        this.firebaseRepository = firebaseRepository;
    }

    public List<Firebase> findAll()  {
        return firebaseRepository.findAll();
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

}
