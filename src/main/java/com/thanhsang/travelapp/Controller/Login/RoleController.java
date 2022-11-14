package com.thanhsang.travelapp.Controller.Login;

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

@RestController
@RequestMapping(path = "/api/v1/roles")
public class RoleController {
    
    @Autowired RoleService roleService;
    private MessageResponse messageResponse = new MessageResponse();

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseObject> findAllRoles(@PathVariable("id") String id) {
        
        try {
            return roleService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new RoleModel())
            );
        }
    }
}
