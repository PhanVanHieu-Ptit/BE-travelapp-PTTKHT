package com.thanhsang.travelapp.Service.Food;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Food.FoodModel;
import com.thanhsang.travelapp.repository.Food.FoodRepo;

@Service
public class FoodService {
    
    @Autowired FoodRepo foodRepo;
    private MessageResponse messageResponse = new MessageResponse();

    public ResponseEntity<ResponseObject> findAll(
        String type, String sort, int page, int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size);
        Page<FoodModel> pageFood;

        if(type.equals("ALL")) {
            if(sort.equals("ALL")) {
                pageFood = foodRepo.findAllByActivity(true, pageable);
            } else {
                pageable = PageRequest.of(page, size, Sort.by(sort).descending());
                pageFood = foodRepo.findAllByActivity(true, pageable);
            }
        } else {
            if(sort.equals("ALL")) {
                pageFood = foodRepo.findAllByActivityAndIdTypeFood(true, type, pageable);
            } else {
                pageable = PageRequest.of(page, size, Sort.by(sort).descending());
                pageFood = foodRepo.findAllByActivityAndIdTypeFood(true, type, pageable);
            }
        }
        return !pageFood.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, pageFood)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
    }

    public ResponseEntity<ResponseObject> findAllForManager(String type, String sort, int page, int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size);
        Page<FoodModel> pageFood;

        if(type.equals("ALL")) {
            if(sort.equals("ALL")) {
                pageFood = foodRepo.findAll(pageable);
            } else {
                pageable = PageRequest.of(page, size, Sort.by(sort).descending());
                pageFood = foodRepo.findAll(pageable);
            }
        } else {
            if(sort.equals("ALL")) {
                pageFood = foodRepo.findAllByIdTypeFood(type, pageable);
            } else {
                pageable = PageRequest.of(page, size, Sort.by(sort).descending());
                pageFood = foodRepo.findAllByIdTypeFood(type, pageable);
            }
        }
        return !pageFood.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, pageFood)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
    }

    public ResponseEntity<ResponseObject> findById(String id) throws Exception {
        Optional<FoodModel> foundFood = foodRepo.findById(id);
        return foundFood.isPresent() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundFood)
            )
            :
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new FoodModel())
            );
    }

    public ResponseEntity<ResponseObject> insert(FoodModel food) throws Exception {
        Optional<FoodModel> foundFood = foodRepo.findById(food.getId());
        if(foundFood.isEmpty() && food.checkValid()) {
            foodRepo.save(food);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("success", messageResponse.INSERT_SUCCESS, food)
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.INSERT_FAILED, food)
        );
    }

    public ResponseEntity<ResponseObject> update(String id, FoodModel food) {
        Optional<FoodModel> foundFood = foodRepo.findById(food.getId());
        if(foundFood.isPresent() && food.checkValid() && id.equals(food.getId())) {
            foundFood.get().changeValid(food);
            foodRepo.save(foundFood.get());

            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, foundFood)
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, food)
        );
    }

    public ResponseEntity<ResponseObject> updateActivity(String id) throws Exception {
        Optional<FoodModel> foundFood = foodRepo.findById(id);
        if(foundFood.isPresent()) {
            foundFood.get().setActivity(!foundFood.get().isActivity());
            foodRepo.save(foundFood.get());

            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, foundFood)
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, new FoodModel())
        );
    }
}
