package com.thanhsang.travelapp.Controller.Hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.model.Adds.OrderDetailReponse;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.repository.Hotel.OrderRoomDetailRepo;

@RestController
@RequestMapping(path = "/api/v1/orderroomdetail")
public class OrderRoomDetailController {
    
    @Autowired OrderRoomDetailRepo orderRoomDetailRepo;
    private MessageResponse messageResponse = new MessageResponse();

    @GetMapping(path = "/idorder/{id_order}")
    public ResponseEntity<ResponseObject> findAllByIdOrder(@PathVariable("id_order") int idOrder) {
        try {
            List<Map<String, OrderDetailReponse>> foundOrderRoom = orderRoomDetailRepo.findAllByIdOrder(idOrder);
            if(!foundOrderRoom.isEmpty())
                return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundOrderRoom)
                );
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("failed", messageResponse.SELECT_FAILED, foundOrderRoom)
               );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            )   ;
        }
    }
}
