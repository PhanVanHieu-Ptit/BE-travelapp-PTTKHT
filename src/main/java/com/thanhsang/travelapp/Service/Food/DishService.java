package com.thanhsang.travelapp.Service.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Food.DishModel;
import com.thanhsang.travelapp.repository.Food.DishRepo;

@Service
public class DishService {
    
    @Autowired DishRepo dishRepo;
    private MessageResponse messageResponse = new MessageResponse();

    public ResponseEntity<ResponseObject> findAllByIdFood(String idFood) throws Exception {
        List<DishModel> foundDish = dishRepo.findAllByIdFood(idFood);
        return !foundDish.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundDish)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("success", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
    }

    public ResponseEntity<ResponseObject> insert(DishModel dish) throws Exception {
        Optional<DishModel> foundDish = dishRepo.findById(dish.getId());
        
        if(foundDish.isEmpty() && dish.checkValid()) {
            dishRepo.save(dish);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("success", messageResponse.INSERT_SUCCESS, dish)
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.INSERT_FAILED, new DishModel())
        );
    }

    public ResponseEntity<ResponseObject> update(String id, @RequestBody DishModel dish) throws Exception {
        Optional<DishModel> foundDish = dishRepo.findById(id);
        if (foundDish.isPresent() && id.equals(dish.getId()) && dish.checkValid()) {
            foundDish.get().update(dish);
            dishRepo.save(foundDish.get());
            
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, dish)
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, new DishModel())
        );
    }
}
