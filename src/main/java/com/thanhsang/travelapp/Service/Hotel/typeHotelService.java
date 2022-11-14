package com.thanhsang.travelapp.Service.Hotel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Hotel.TypeHotelModel;
import com.thanhsang.travelapp.repository.Hotel.TypeHotelRepo;

@Service
public class typeHotelService {
    
    @Autowired TypeHotelRepo typeHotelRepo;
    private MessageResponse messageResponse = new MessageResponse();

    public ResponseEntity<ResponseObject> findAll() throws Exception {
        List<TypeHotelModel> foundHotels = typeHotelRepo.findAll();

        return !foundHotels.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundHotels)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, foundHotels)
            );
    }

    public ResponseEntity<ResponseObject> insert(TypeHotelModel typeHotel) throws Exception {
        Optional<TypeHotelModel> foundTypeHotel = typeHotelRepo.findById(typeHotel.getID());

        if(foundTypeHotel.isEmpty() && !typeHotel.getName().equals("")) {
            typeHotelRepo.save(typeHotel);

            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.INSERT_SUCCESS, typeHotel)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("failed", messageResponse.INSERT_FAILED, new TypeHotelModel())
        );
    }

    public ResponseEntity<ResponseObject> update(String id, String name) throws Exception {
        Optional<TypeHotelModel> foundTypeHotel = typeHotelRepo.findById(id);

        if(foundTypeHotel.isPresent() && !name.equals("")) {
            foundTypeHotel.get().setName(name);

            typeHotelRepo.save(foundTypeHotel.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, foundTypeHotel.get())
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, new TypeHotelModel())
        );
    }
}
