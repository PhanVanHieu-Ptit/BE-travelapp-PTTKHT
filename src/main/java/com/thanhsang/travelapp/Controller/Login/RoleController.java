package com.thanhsang.travelapp.Controller.Login;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.Service.Login.RoleService;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Login.RoleModel;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/v1/roles")
public class RoleController {
    
    @Autowired RoleService roleService;
    private MessageResponse messageResponse = new MessageResponse();

    @ApiOperation(value = "Get all roles", notes = "")
    @GetMapping(path = "")
    public ResponseEntity<ResponseObject> findAll() {
        
        try {
            return roleService.findAll();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
        }
    }

    @ApiOperation(value = "Get a role by id", notes = "")
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseObject> findRoleById(@PathVariable("id") String id) {
        
        try {
            return roleService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new RoleModel())
            );
        }
    }
}
