package com.thanhsang.travelapp.Controller.Adds;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.Service.Adds.ShipScheduleService;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Adds.ShipScheduleModel;

@RestController
@RequestMapping(path = "/api/v1/scheduleships")
public class ScheduleShipController {
    
    @Autowired ShipScheduleService shipScheduleService;
    private MessageResponse messageResponse = new MessageResponse();

    @GetMapping(path = "")
    public ResponseEntity<ResponseObject> findAll() {
        
            try {
                return shipScheduleService.findAll();
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
                ); 
            }
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable("id") String id) {
        
            try {
                return shipScheduleService.findById(id);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", messageResponse.SELECT_FAILED, new ShipScheduleModel())
                ); 
            }
    }

    @PostMapping(path = "")
    public ResponseEntity<ResponseObject> insert(@RequestBody ShipScheduleModel model) {
        
            try {
                return shipScheduleService.insert(model);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("failed", messageResponse.INSERT_FAILED, new ShipScheduleModel())
                ); 
            }
    }

    @PatchMapping(path = "/id/{id}")
    public ResponseEntity<ResponseObject> update(
        @PathVariable("id") String id, 
        @RequestBody ShipScheduleModel model) {

        try {
            return shipScheduleService.update(id, model);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, new ShipScheduleModel())
            ); 
        }
    }

    @DeleteMapping(path = "/id/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable("id") String id) {

        try {
            return shipScheduleService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.DELETE_FAILED, new ShipScheduleModel())
            ); 
        }
    }


}
