package com.thanhsang.travelapp.Controller.Login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.Service.Login.UserService;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Login.UserModel;
import com.thanhsang.travelapp.repository.Login.UserRepo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    @Autowired UserRepo userRepo;
    @Autowired UserService userService;
    private MessageResponse messageResponse = new MessageResponse();
    
    @ApiOperation(value = "Get a User by id social", notes = "")
    @GetMapping(path = "/{idSocial}")
    public ResponseEntity<ResponseObject> findByIdSocial(@PathVariable String idSocial) {
        try {
            Optional<UserModel> user = userRepo.findByIdSocial(idSocial);
            return user.isPresent() ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("success", messageResponse.SELECT_SUCCESS, user)
                )
                :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("failed", messageResponse.SELECT_FAILED, new UserModel())
                );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new UserModel())
            );
        }
    }

    @ApiOperation(value = "Get a User by id user", notes = "")
    @GetMapping(path = "/{idUser}")
    public ResponseEntity<ResponseObject> findById(@PathVariable String idUser) {
        try {
            Optional<UserModel> user = userRepo.findById(idUser);
            return user.isPresent() ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("success", messageResponse.SELECT_SUCCESS, user)
                )
                :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("failed", messageResponse.SELECT_FAILED, new UserModel())
                );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new UserModel())
            );
        }
    }

    @ApiOperation(value = "Insert a User", notes = "")
    @PostMapping(path = "")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> insert(@RequestBody UserModel user) {
        try {
            return userService.insert(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.INSERT_FAILED, user)
            );
        }
    }

    @ApiOperation(value = "update a User by id", notes = "")
    @PatchMapping(path = "/{id}")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> update(@PathVariable String id, @RequestBody UserModel user) {
        try {
            return userService.update(id, user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, user)
            );
        }
    }
}
