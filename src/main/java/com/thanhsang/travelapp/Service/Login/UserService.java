package com.thanhsang.travelapp.Service.Login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Login.UserModel;
import com.thanhsang.travelapp.repository.Login.UserRepo;

@Service
public class UserService {
    
    @Autowired UserRepo userRepo;
    private MessageResponse messageResponse = new MessageResponse();

    public ResponseEntity<ResponseObject> insert(UserModel user) throws Exception {
        Optional<UserModel> foundUser = userRepo.findByIdSocial(user.getIdSocial());

        if(user.checkValid() && foundUser.isEmpty() && !foundUser.get().getId().equals(user.getId())) {
            user.setIdSocial(user.getIdSocial().toLowerCase());
            userRepo.save(user);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("success", messageResponse.INSERT_SUCCESS, user)
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.INSERT_FAILED, user)
        );
    }

    public ResponseEntity<ResponseObject> update(String id, UserModel user) throws Exception {
        Optional<UserModel> foundUser = userRepo.findById(id);

        if(user.checkValid() && foundUser.isPresent()) {
            foundUser.get().update(user);
            userRepo.save(foundUser.get());

            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, foundUser)
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, user)
        );
    }
}
