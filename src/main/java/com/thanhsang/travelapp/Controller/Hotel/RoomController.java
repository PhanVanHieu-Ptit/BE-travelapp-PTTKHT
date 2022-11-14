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
import org.springframework.web.bind.annotation.RestController;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.Service.Hotel.RoomService;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Hotel.RoomModel;

@RestController
@RequestMapping(path = "/api/v1/rooms")
public class RoomController {
    
    @Autowired RoomService roomService;
    private MessageResponse messageResponse = new MessageResponse();

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable(name = "id") String id) {
        try {
            return roomService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new RoomModel())
            );
        }
    }

    @GetMapping(path = "/hotel/{idHotel}")
    public ResponseEntity<ResponseObject> findAllByIdHotel(@PathVariable(name = "idHotel") String idHotel) {
        try {
            return roomService.findAllByIdHotel(idHotel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
        }
    }

    @GetMapping(path = "/hotel/{idHotel}/bussiness")
    public ResponseEntity<ResponseObject> findAllByIdHotelForBusiness(@PathVariable(name = "idHotel") String idHotel) {
        try {
            return roomService.findAllByIdHotelForBusiness(idHotel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
        }
    }

    @PostMapping(path = "")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> insert(@RequestBody RoomModel room) {
        try {
            return roomService.insert(room);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.INSERT_FAILED, new RoomModel())
            );
        }
    }

    @PatchMapping(path = "/{id}")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> update(@PathVariable(name = "id") String id, @RequestBody RoomModel room) {
        try {
            return roomService.update(id, room);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, new RoomModel())
            );
        }
    }

    // @PatchMapping(path = "/{id}/activity")
    // @Transactional(rollbackFor = {Exception.class, Throwable.class})
    // public ResponseEntity<ResponseObject> updateActivity(@PathVariable(name = "id") String id) {
    //     try {
    //         return roomService.updateActivity(id);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
    //             new ResponseObject("failed", messageResponse.UPDATE_FAILED, new RoomModel())
    //         );
    //     }
    // }
}
