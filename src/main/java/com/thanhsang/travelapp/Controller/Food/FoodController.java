package com.thanhsang.travelapp.Controller.Food;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.Service.Food.FoodService;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Food.FoodModel;
import com.thanhsang.travelapp.repository.Food.FoodRepo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/v1/foods")
public class FoodController {
    
    @Autowired FoodRepo foodRepo;
    @Autowired FoodService foodService;
    private MessageResponse messageResponse = new MessageResponse();

    /**
     * @param type: List typeFood
     * @param sort: ALL, star (descending)
     * @param page: start page 0
     * @param size
     * @return
     */
    @ApiOperation(value = "Get all foods for customer as per _type, _sort, _page, _size", 
    notes = "_type=['ALL' + id of get all types food], _sort=['ALL' + 'star':descending], _page&_size=[0, 1, 2,...]")
    @GetMapping("")
    public ResponseEntity<ResponseObject> findAll(
        @RequestParam(name ="_type", required = true) String type, 
        @RequestParam(name = "_sort", required = true) String sort, 
        @RequestParam(name = "_page", required = true) int page,
        @RequestParam(name = "_size", required = true) int size) {

        try {
            return foodService.findAll(type, sort, page, size);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
        }
    }

    /**
     * @param type: List typeHotel
     * @param sort: ALL, star (descending)
     * @param page: start page 0
     * @param size
     * @return
     */
    @ApiOperation(value = "Get all foods for manager(admin, business) as per _type, _sort, _page, _size", 
    notes = "_type=['ALL' + id of get all types food], _sort=['ALL' + 'star':descending], _page&_size=[0, 1, 2,...]")
    @GetMapping("/admin")
    public ResponseEntity<ResponseObject> findAllForManager(
        @RequestParam(name ="_type", required = true) String type, 
        @RequestParam(name = "_sort", required = true) String sort, 
        @RequestParam(name = "_page", required = true) int page,
        @RequestParam(name = "_size", required = true) int size) {

        try {
            return foodService.findAllForManager(type, sort, page, size);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
        }
    }

    @GetMapping("{id}")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> findById(@PathVariable("id") String id) {
        try {
            return foodService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new FoodModel())
            );
        }
    }

    @PostMapping("")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> insert(@RequestBody FoodModel food) {
        try {
            return foodService.insert(food);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.INSERT_FAILED, new FoodModel())
            );
        }
    }

    @PatchMapping("/{id}")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> update(@PathVariable("id") String id, @RequestBody FoodModel food) {
        try {
            return foodService.update(id, food);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, new FoodModel())
            );
        }
    }

    @PatchMapping("/{id}/activity")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> updateActivity(@PathVariable("id") String id) {
        try {
            return foodService.updateActivity(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, new FoodModel())
            );
        }
    }

    @GetMapping(path = "/top10")
    public ResponseEntity<ResponseObject> findTop10ByStarDesc() {
        try {
            return foodService.findTop10();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
        }
    }
}