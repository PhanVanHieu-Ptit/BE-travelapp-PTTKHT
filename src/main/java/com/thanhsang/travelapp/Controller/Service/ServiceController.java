package com.thanhsang.travelapp.Controller.Service;

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
import com.thanhsang.travelapp.Service.Service.ServiceService;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Service.ServiceModel;

@RestController
@RequestMapping(path = "/api/v1/services")
public class ServiceController {
    
    @Autowired ServiceService serviceService;
    private MessageResponse messageResponse = new MessageResponse();

    /**
     * @param type: List typeService
     * @param sort: ALL, star (descending), price (ascending, descending)
     * @param page: start page 0
     * @param size
     * @return
     */
    @GetMapping("")
    public ResponseEntity<ResponseObject> findAll(
        @RequestParam(name ="_type", required = true) String type, 
        @RequestParam(name = "_sort", required = true) String sort, 
        @RequestParam(name = "_page", required = true) int page,
        @RequestParam(name = "_size", required = true) int size) {

        try {
            return serviceService.findAll(type, sort, page, size);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
        }
    }

    /**
     * @param type: List typeService
     * @param sort: ALL, star (descending), price (ascending, descending)
     * @param page: start page 0
     * @param size
     * @return
     */
    @GetMapping("/manager")
    public ResponseEntity<ResponseObject> findAllForManager(
        @RequestParam(name ="_type", required = true) String type, 
        @RequestParam(name = "_sort", required = true) String sort, 
        @RequestParam(name = "_page", required = true) int page,
        @RequestParam(name = "_size", required = true) int size) {

        try {
            return serviceService.findAllForManager(type, sort, page, size);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
        }
    }

    @PostMapping("")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> insert(@RequestBody ServiceModel service) {

        try {
            return serviceService.insert(service);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.INSERT_FAILED, new ServiceModel())
            );
        }
    }

    @PatchMapping("/{id}")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> update(@PathVariable("id") String id, @RequestBody ServiceModel service) {

        try {
            return serviceService.update(id, service);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, new ServiceModel())
            );
        }
    }

    @PatchMapping("/{id}/activity")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> updateActivity(@PathVariable("id") String id) {

        try {
            return serviceService.updateActivity(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, new ServiceModel())
            );
        }
    }
}
