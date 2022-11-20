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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.Service.Hotel.HotelService;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Hotel.HotelModel;
import com.thanhsang.travelapp.repository.Hotel.HotelRepo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {
    
    @Autowired HotelRepo hotelRepo;
    @Autowired HotelService hotelService;
    private MessageResponse messageResponse = new MessageResponse();

    /**
     * @param type: List typeHotel
     * @param sort: ALL, star (descending), priceMin(ascending), priceMax(descending)
     * @param page: start page 0
     * @param size
     * @return
     */
    @ApiOperation(value = "Get all hotels as per _type, _sort, _page, _size", 
    notes = "_type=['ALL' + id of get all types hotel], _sort=['ALL' + 'star':descending + 'priceMax':descending + 'priceMin':ascending], _page&_size=[0, 1, 2,...]")
    @GetMapping("")
    public ResponseEntity<ResponseObject> findAll(
        @RequestParam(name ="_type", required = true) String type, 
        @RequestParam(name = "_sort", required = true) String sort, 
        @RequestParam(name = "_page", required = true) int page,
        @RequestParam(name = "_size", required = true) int size) {

        try {
            return hotelService.findAll(type, sort, page, size);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
        }
    }

    /**
     * @param type: List typeHotel
     * @param sort: ALL, star (descending), priceMin(ascending), priceMax(descending)
     * @param page: start page 0
     * @param size
     * @return
     */
    @ApiOperation(value = "Get all hotels for manager(admin, business) as per _type, _sort, _page, _size", 
    notes = "_type=['ALL' + id of get all types hotel], _sort=['ALL' + 'star':descending + 'priceMax':descending + 'priceMin':ascending], _page&_size=[0, 1, 2,...]")
    @GetMapping("/admin")
    public ResponseEntity<ResponseObject> findAllForManager(
        @RequestParam(name ="_type", required = true) String type, 
        @RequestParam(name = "_sort", required = true) String sort, 
        @RequestParam(name = "_page", required = true) int page,
        @RequestParam(name = "_size", required = true) int size) {

        try {
            return hotelService.findAllForManager(type, sort, page, size);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
        }
    }

    @ApiOperation(value = "Get a Hotel by id", notes = "")
    @GetMapping("{id}")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> findById(@PathVariable("id") String id) throws Exception {
        try {
            return hotelService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.INSERT_FAILED, new HotelModel())
            );
        }
    }

    @ApiOperation(value = "Insert a Hotel", notes = "")
    @PostMapping("")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> insert(@RequestBody HotelModel hotel) {
        try {
            return hotelService.insert(hotel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.INSERT_FAILED, hotel)
            );
        }
    }

    @ApiOperation(value = "Update a Hotel", notes = "")
    @PatchMapping("/{id}")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> update(@PathVariable("id") String id, @RequestBody HotelModel hotel) {
        try {
            return hotelService.update(id, hotel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, hotel)
            );
        }
    }

    @ApiOperation(value = "Update state Hotel by id", notes = "")
    @PatchMapping("/{id}/activity")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> updateActivity(@PathVariable("id") String id) {
        try {
            return hotelService.updateActivity(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, new HotelModel())
            );
        }
    }

    @GetMapping(path = "/top10")
    public ResponseEntity<ResponseObject> findTop10ByStarDesc() {
        try {
            return hotelService.findTop10();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
        }
    }
}
