package com.thanhsang.travelapp.Controller.Hotel;

import java.util.ArrayList;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.Service.Hotel.typeHotelService;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Hotel.TypeHotelModel;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/v1/typehotels")
public class TypeHotelController {
    
    @Autowired typeHotelService typeHotelService;
    private MessageResponse messageResponse = new MessageResponse();


    @ApiOperation(value = "Get all types_hotel", notes = "")
    @GetMapping(path = "")
    public ResponseEntity<ResponseObject> findAll() {
        try {
            return typeHotelService.findAll();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
        }
    }

    @ApiOperation(value = "Insert a types_hotel", notes = "")
    @PostMapping(path = "")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> insert(@RequestBody TypeHotelModel typeHotel) {
        try {
            return typeHotelService.insert(typeHotel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, typeHotel)
            );
        }
    } 

    @ApiOperation(value = "Update a type_hotel", notes = "")
    @PatchMapping(path = "/{id}")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> update(@PathVariable("id") String id, @RequestParam(name = "_name", required = true) String name) {
        try {
            return typeHotelService.update(id, name);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, new TypeHotelModel())
            );
        }
    } 

}
