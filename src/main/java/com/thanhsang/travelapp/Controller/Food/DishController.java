package com.thanhsang.travelapp.Controller.Food;

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
import org.springframework.web.bind.annotation.RestController;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.Service.Food.DishService;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Food.DishModel;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/v1/dishs")
public class DishController {
    
    @Autowired DishService dishService;
    private MessageResponse messageResponse = new MessageResponse();

    @ApiOperation(value = "Get all dishs by id_food", notes = "")
    @GetMapping(path = "/idfood/{id_food}")
    public ResponseEntity<ResponseObject> findAllByIdFood(@PathVariable("id_food") String id) {
        try {
            return dishService.findAllByIdFood(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
        }
    }

    @ApiOperation(value = "Get a dish by id", notes = "")
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable("id") String id) {
        try {
            return dishService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new DishModel())
            );
        }
    }

    @ApiOperation(value = "Insert a dish", notes = "")
    @PostMapping(path = "")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> insert(@RequestBody DishModel dish) {
        try {
            return dishService.insert(dish);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, dish)
            );
        }
    } 

    @ApiOperation(value = "Update a dish by id", notes = "")
    @PatchMapping(path = "/{id}")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> update(@PathVariable("id") String id, @RequestBody DishModel dish) {
        try {
            return dishService.update(id, dish);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, dish)
            );
        }
    } 

}
