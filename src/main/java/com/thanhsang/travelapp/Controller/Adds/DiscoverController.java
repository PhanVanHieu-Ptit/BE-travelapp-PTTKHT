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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.Service.Adds.DiscoverService;
import com.thanhsang.travelapp.model.Adds.DiscoveryModel;
import com.thanhsang.travelapp.model.Adds.ResponseObject;

@RestController
@RequestMapping("/api/v1/discovers")
public class DiscoverController {

    @Autowired DiscoverService discoverService;
    private MessageResponse messageResponse = new MessageResponse();

    @GetMapping(path = "")
    public ResponseEntity<ResponseObject> findAll(
        @RequestParam(name = "_page", required = true) int page,
        @RequestParam(name = "_size", required = true) int size) {
        
            try {
                return discoverService.findAll(page, size);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
                ); 
            }
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable("id") String id) {
        
            try {
                return discoverService.findById(id);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", messageResponse.SELECT_FAILED, new DiscoveryModel())
                ); 
            }
    }

    @PostMapping(path = "")
    public ResponseEntity<ResponseObject> insert(@RequestBody DiscoveryModel model) {
        
            try {
                return discoverService.insert(model);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("failed", messageResponse.INSERT_FAILED, new DiscoveryModel())
                ); 
            }
    }

    @PatchMapping(path = "/id/{id}")
    public ResponseEntity<ResponseObject> update(
        @PathVariable("id") String id, 
        @RequestBody DiscoveryModel model) {

        try {
            return discoverService.update(id, model);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, new DiscoveryModel())
            ); 
        }
    }

    @DeleteMapping(path = "/id/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable("id") String id) {

        try {
            return discoverService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.DELETE_FAILED, new DiscoveryModel())
            ); 
        }
    }

}
