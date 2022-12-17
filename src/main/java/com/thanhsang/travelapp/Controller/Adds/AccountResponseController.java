package com.thanhsang.travelapp.Controller.Adds;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhsang.travelapp.model.Adds.AccountResponse;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.repository.Adds.AccountResponseRepo;

@RestController
@RequestMapping(value = "/api/v1/account")
public class AccountResponseController {

    @Autowired AccountResponseRepo accountResponseRepo;
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable("id") String id) {

        try {
            Optional<AccountResponse> foundAccount = accountResponseRepo.findAccountById(id);
            return foundAccount.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Tìm kiếm thành công", foundAccount.get())   
                )
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Tìm kiếm thất bại", "")   
                );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Lỗi truy cập", "")   
            );
        }
    }

}
