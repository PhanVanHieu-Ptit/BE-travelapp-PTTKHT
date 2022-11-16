package com.thanhsang.travelapp.Service.Hotel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Hotel.RoomModel;
import com.thanhsang.travelapp.repository.Hotel.RoomRepo;

@Service
public class RoomService {
    
    @Autowired RoomRepo roomRepo;
    private MessageResponse messageResponse = new MessageResponse();

    public ResponseEntity<ResponseObject> findAllByIdHotel(String id) throws Exception {
        List<RoomModel> foundRoom = roomRepo.findAllByIdHotelAndActivity(id, true);
        return !foundRoom.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundRoom)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, foundRoom)
            );
    }

    public ResponseEntity<ResponseObject> findAllByIdHotelForBusiness(String id) throws Exception {
        List<RoomModel> foundRoom = roomRepo.findAllByIdHotel(id);
        return !foundRoom.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundRoom)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, foundRoom)
            );
    }

    public ResponseEntity<ResponseObject> findById(String id) throws Exception {
        Optional<RoomModel> foundRoom = roomRepo.findById(id);
        return foundRoom.isPresent() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundRoom.get())
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new RoomModel())
            );
    }

    public ResponseEntity<ResponseObject> insert(RoomModel room) throws Exception {
        Optional<RoomModel> foundRoom = roomRepo.findById(room.getId());

        if(foundRoom.isEmpty() && room.checkValid()) {
            roomRepo.save(room);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("success", messageResponse.INSERT_SUCCESS, room)
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.INSERT_FAILED, room)
        );
    }

    public ResponseEntity<ResponseObject> update(String id, RoomModel room) throws Exception {
        Optional<RoomModel> foundRoom = roomRepo.findById(id);
        if (foundRoom.isPresent() && room.getId().equals(id) && room.checkValid()) {
            foundRoom.get().changeValid(room);
            roomRepo.save(foundRoom.get());

            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, foundRoom.get())
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, room)
        );
    }

    public ResponseEntity<ResponseObject> updateActivity(String id) throws Exception {
        Optional<RoomModel> foundRoom = roomRepo.findById(id);
        if (foundRoom.isPresent()) {
            foundRoom.get().setActivity(!foundRoom.get().isActivity());
            roomRepo.save(foundRoom.get());

            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, foundRoom.get())
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, new RoomModel())
        );
    }
}
