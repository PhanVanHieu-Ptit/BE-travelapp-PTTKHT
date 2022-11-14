package com.thanhsang.travelapp.Controller.Service;

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
import com.thanhsang.travelapp.Service.Service.ScheduleServiceService;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Service.ScheduleModel;

@RestController
@RequestMapping(path = "/api/v1/scheduleservice")
public class ScheduleServiceController {
    
    @Autowired ScheduleServiceService scheduleServiceService;
    private MessageResponse messageResponse = new MessageResponse();

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable("id") String id) {
        try {
            return scheduleServiceService.findById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ScheduleModel())
            );
        }
    }

    @GetMapping(path = "/idservice/{idService}/business")
    public ResponseEntity<ResponseObject> findAllByIdServiceForBusiness(@PathVariable("idService") String idService) {
        try {
            return scheduleServiceService.findAllByIdServiceForBusiness(idService);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ScheduleModel())
            );
        }
    }

    @GetMapping(path = "/idservice/{idService}")
    public ResponseEntity<ResponseObject> findAllByIdService(@PathVariable("idService") String idService) {
        try {
            return scheduleServiceService.findAllByIdService(idService);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ScheduleModel())
            );
        }
    }

    @PostMapping(path = "")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> insert(@RequestBody ScheduleModel schedule) {
        try {
            return scheduleServiceService.insert(schedule);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.INSERT_FAILED, new ScheduleModel())
            );
        }
    }

    @PatchMapping(path = "/{id}")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> update(@PathVariable("id") String id, @RequestBody ScheduleModel schedule) {
        try {
            return scheduleServiceService.update(id, schedule);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, new ScheduleModel())
            );
        }
    }
}
