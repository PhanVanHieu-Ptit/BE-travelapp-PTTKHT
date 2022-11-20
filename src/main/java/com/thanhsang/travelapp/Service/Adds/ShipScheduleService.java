package com.thanhsang.travelapp.Service.Adds;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Adds.ShipScheduleModel;
import com.thanhsang.travelapp.repository.Adds.ShipScheduleRepo;

@Service
public class ShipScheduleService {
    
    @Autowired ShipScheduleRepo shipScheduleRepo;
    private MessageResponse messageResponse = new MessageResponse();

    public ResponseEntity<ResponseObject> findAll() throws Exception {
        List<ShipScheduleModel> foundShips = shipScheduleRepo.findAll();
        return !foundShips.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundShips)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, foundShips)
            ); 
    }

    public ResponseEntity<ResponseObject> findById(String id) throws Exception {
        Optional<ShipScheduleModel> foundShips = shipScheduleRepo.findById(id);
        return foundShips.isPresent() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundShips.get())
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ShipScheduleModel())
            ); 
    }

    public ResponseEntity<ResponseObject> insert(ShipScheduleModel model) throws Exception {
        Optional<ShipScheduleModel> foundShip = shipScheduleRepo.findById(model.getId());
        if(foundShip.isEmpty()) {
            shipScheduleRepo.save(model);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("success", messageResponse.INSERT_SUCCESS, model)
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.INSERT_FAILED, new ShipScheduleModel())
        ); 
    }

    public ResponseEntity<ResponseObject> update(String id, ShipScheduleModel model) throws Exception {
        Optional<ShipScheduleModel> foundShip = shipScheduleRepo.findById(id);
        if(foundShip.isPresent() && id.equals(model.getId())) {
            shipScheduleRepo.save(model);
            
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, model)
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, new ShipScheduleModel())
        ); 
    }

    public ResponseEntity<ResponseObject> delete(String id) throws Exception {
        Optional<ShipScheduleModel> foundDiscover = shipScheduleRepo.findById(id);
        if(foundDiscover.isPresent()) {
            
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ResponseObject("success", messageResponse.DELETE_SUCCESS, shipScheduleRepo.deleteById(id))
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("failed", messageResponse.DELETE_FAILED, new ShipScheduleModel())
        ); 
    }
}
