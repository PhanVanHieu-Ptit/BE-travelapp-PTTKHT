package com.thanhsang.travelapp.Service.Food;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Food.TypeFoodModel;
import com.thanhsang.travelapp.repository.Food.TypeFoodRepo;

@Service
public class TypeFoodService {
    @Autowired TypeFoodRepo typeFoodRepo;
    private MessageResponse messageResponse = new MessageResponse();

    public ResponseEntity<ResponseObject> findAll() throws Exception {
        List<TypeFoodModel> foundFoods = typeFoodRepo.findAll();

        return !foundFoods.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundFoods)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, foundFoods)
            );
    }

    public ResponseEntity<ResponseObject> insert(TypeFoodModel typeFood) throws Exception {
        Optional<TypeFoodModel> foundFood = typeFoodRepo.findById(typeFood.getId());

        if(foundFood.isEmpty()) {
            typeFoodRepo.save(typeFood);
            
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.INSERT_SUCCESS, typeFood)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("failed", messageResponse.INSERT_FAILED, new TypeFoodModel())
        );
    }

    public ResponseEntity<ResponseObject> update(String id, String name) throws Exception {
        Optional<TypeFoodModel> foundFood = typeFoodRepo.findById(id);

        if(foundFood.isPresent() && !name.equals("")) {
            foundFood.get().setName(name);

            typeFoodRepo.save(foundFood.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, foundFood.get())
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, new TypeFoodModel())
        );
    }
}
