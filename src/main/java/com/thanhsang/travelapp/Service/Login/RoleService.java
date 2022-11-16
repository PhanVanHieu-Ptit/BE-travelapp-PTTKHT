package com.thanhsang.travelapp.Service.Login;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Login.RoleModel;
import com.thanhsang.travelapp.repository.Login.RoleRepo;

@Service
public class RoleService {

    @Autowired RoleRepo roleRepo;
    private MessageResponse messageResponse = new MessageResponse();
    
    public ResponseEntity<ResponseObject> findById(String id) throws Exception {
        Optional<RoleModel> foundRole = roleRepo.findById(id);

        return foundRole.isPresent() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundRole)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new RoleModel())
            );
    }

    public ResponseEntity<ResponseObject> findAll() throws Exception {
        List<RoleModel> roles = roleRepo.findAll();

        return !roles.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, roles)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
    }
}
