package com.thanhsang.travelapp.Service.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Service.TypeServiceModel;
import com.thanhsang.travelapp.repository.Service.TypeServiceRepo;

@Service
public class TypeServiceService {
    
    @Autowired TypeServiceRepo typeServiceRepo;
    private MessageResponse messageResponse = new MessageResponse();

    public ResponseEntity<ResponseObject> findAll() throws Exception {
        List<TypeServiceModel> foundTypeServices = typeServiceRepo.findAll();

        return !foundTypeServices.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundTypeServices)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, foundTypeServices)
            );
    }

    // public ResponseEntity<ResponseObject> findTop3() {
    //     List<TypeServiceModel> foundTypeServices = typeServiceRepo.findTop10ByOrderByStarDesc();

    //     return !foundTypeServices.isEmpty() ?
    //         ResponseEntity.status(HttpStatus.OK).body(
    //             new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundTypeServices)
    //         )
    //         :
    //         ResponseEntity.status(HttpStatus.NOT_FOUND).body(
    //             new ResponseObject("failed", messageResponse.SELECT_FAILED, foundTypeServices)
    //         );
    // }

    public ResponseEntity<ResponseObject> insert(TypeServiceModel typeService) throws Exception {
        Optional<TypeServiceModel> foundTypeService = typeServiceRepo.findById(typeService.getId());

        if(foundTypeService.isEmpty() && !typeService.getName().equals("")) {
            typeServiceRepo.save(typeService);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("success", messageResponse.INSERT_SUCCESS, typeService)
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, typeService)
        );
    }

    public ResponseEntity<ResponseObject> update(String id, String name) throws Exception {
        Optional<TypeServiceModel> foundTypeServices = typeServiceRepo.findById(id);

        if(foundTypeServices.isPresent() && !name.equals("")) {
            foundTypeServices.get().setName(name);

            typeServiceRepo.save(foundTypeServices.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, foundTypeServices.get())
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, new TypeServiceModel())
        );
    }

}
