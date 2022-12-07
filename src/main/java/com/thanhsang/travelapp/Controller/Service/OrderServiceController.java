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
import com.thanhsang.travelapp.Service.Service.OrderServiceService;
import com.thanhsang.travelapp.model.Adds.PageRatingReponse;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Service.OrderServiceModel;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/v1/orderservice")
public class OrderServiceController {
    
    @Autowired OrderServiceService orderServiceS;
    private MessageResponse messageResponse = new MessageResponse();

    @GetMapping(path = "/iduser/{id}")
    public ResponseEntity<ResponseObject> findAllByIdUser(
        @PathVariable("id") String id,
        @RequestParam(name = "_state", required = true) String type,
        @RequestParam(name = "_page", required = true) int page,
        @RequestParam(name = "_size", required = true) int size) {
        try {
            return orderServiceS.findAllByIdUser(id, type, page, size);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
        }
    }

    @GetMapping(path = "/idservice/{id}")
    public ResponseEntity<ResponseObject> findAllByIdService(
        @PathVariable("id") String id,
        @RequestParam(name = "_state", required = true) String type,
        @RequestParam(name = "_page", required = true) int page,
        @RequestParam(name = "_size", required = true) int size) {

        try {
            return orderServiceS.findAllByIdService(id, type, page, size);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
        }
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<ResponseObject> updateState(
        @PathVariable("id") int idOrder,
        @RequestParam(name = "_idState", required = true) String idState) {

        try {
            return orderServiceS.updateState(idOrder, idState);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, new OrderServiceModel())
            );
        }
    }

    @ApiOperation(value = "Update a order by id", notes = "")
    @PatchMapping("/idOrderService/{id}")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> update(@PathVariable("id") int id, @RequestBody OrderServiceModel orderService) {

        try {
            return orderServiceS.update(id, orderService);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, orderService)
            );
        }
    }


    @PatchMapping(path = "/{id}/rating")
    public ResponseEntity<ResponseObject> updateRating(
        @PathVariable("id") int idOrder,
        @RequestParam(name = "_star", required = true) int idState,
        @RequestParam(name = "_comment", required = true) String comment){
        
        try {
            return orderServiceS.updateRating(idOrder, idState, comment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, new OrderServiceModel())
            );
        }
    }

    @GetMapping(path = "/idservice/{id}/rating")
    public ResponseEntity<ResponseObject> selectRatingByIdServiceAndStar(
        @PathVariable("id") String idService,
        @RequestParam(name = "_star", required = true) int star,
        @RequestParam(name = "_page", required = true) int page,
        @RequestParam(name = "_size", required = true) int size) {

        try {
            return orderServiceS.selectRatingByIdServiceAndStar(idService, star, page, size);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new PageRatingReponse())
            );
        }
    }

    @PostMapping(path = "")
    public ResponseEntity<ResponseObject> insert(@RequestBody OrderServiceModel orderService) {
        try {
            return orderServiceS.insert(orderService);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new OrderServiceModel())
            );
        }
    }

}
