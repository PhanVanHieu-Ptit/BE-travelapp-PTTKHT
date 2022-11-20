package com.thanhsang.travelapp.Controller.Hotel;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.Service.Hotel.OrderRoomService;
import com.thanhsang.travelapp.model.Adds.PageRatingReponse;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Hotel.OrderRoomRequestModel;
import com.thanhsang.travelapp.model.Hotel.OrderRoomModel;
import com.thanhsang.travelapp.repository.Hotel.OrderRoomDetailRepo;
import com.thanhsang.travelapp.repository.Hotel.OrderRoomRepo;

@RestController
@RequestMapping(path = "/api/v1/orderroom")
public class OrderRoomController {
    
    @Autowired OrderRoomService orderRoomService;
    @Autowired OrderRoomRepo orderRoomRepo;
    @Autowired OrderRoomDetailRepo orderRoomDetailRepo;
    private MessageResponse messageResponse = new MessageResponse();

    @GetMapping(path = "/iduser/{id_user}")
    public ResponseEntity<ResponseObject> findAllByIdUserAndState(
        @PathVariable("id_user") String idUser,
        @RequestParam(name = "_state", required = true) String type,
        @RequestParam(name = "_page", required = true) int page,
        @RequestParam(name = "_size", required = true) int size) {
        try {
            return orderRoomService.findOrderRoomByIdUser(idUser, type, page, size);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );    
        }
    }

    @GetMapping(path = "/idhotel/{id_hotel}")
    public ResponseEntity<ResponseObject> findAllByIdHotelAndState(
        @PathVariable("id_hotel") String idHotel,
        @RequestParam(name = "_state", required = true) String type,
        @RequestParam(name = "_page", required = true) int page,
        @RequestParam(name = "_size", required = true) int size) {
        try {
            return orderRoomService.findOrderRoomByIdHotel(idHotel, type, page, size);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
        }
    }

    @PostMapping(path = "")
    public ResponseEntity<ResponseObject> insert(@RequestBody OrderRoomRequestModel order) {
        try {
            return orderRoomService.insert(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.INSERT_FAILED, order)
            ); 
        }
    }

    @PatchMapping(path = "/idorder/{id_order}/state")
    public ResponseEntity<ResponseObject> updateState(@PathVariable("id_order") int idOrder, @RequestParam(name = "_idState", required = true) String idState) {
        try {
            return orderRoomService.updateState(idOrder, idState);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.INSERT_FAILED, new OrderRoomModel())
            ); 
        }
    }

    @GetMapping(path = "/idhotel/{id_hotel}/rating")
    public ResponseEntity<ResponseObject> selectRatingByIdHotelAndStar(
        @PathVariable("id_hotel") String idHotel,
        @RequestParam(name = "_star", required = true) int star,
        @RequestParam(name = "_page", required = true) int page,
        @RequestParam(name = "_size", required = true) int size) {
        try {
            return orderRoomService.selectRatingByIdHotelAndStar(idHotel, star, page, size);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new PageRatingReponse())
            );
        }
    }

    @PatchMapping(path = "/idorder/{id_order}/rating") 
    public ResponseEntity<ResponseObject> updateRating(
        @PathVariable("id_order") int idOrder,
        @RequestParam("star") int star,
        @RequestParam("comment") String comment) {
            try {
                return orderRoomService.updateRating(idOrder, star, comment);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("failed", messageResponse.UPDATE_FAILED, new OrderRoomModel())
                );
            }
    }
   
    // @GetMapping(path = "/idhotel/{id_hotel}/rating")
    // public ResponseEntity<ResponseObject> selectRatingByIdHotel(
    //     @PathVariable("id_hotel") String idHotel,
    //     @RequestParam(name = "_page", required = true) int page,
    //     @RequestParam(name = "_size", required = true) int size) {
    //     try {
    //         return orderRoomService.selectRatingByIdHotel(idHotel, page, size);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
    //             new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
    //         );
    //     }
    // }

}
