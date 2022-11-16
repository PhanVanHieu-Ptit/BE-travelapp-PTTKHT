package com.thanhsang.travelapp.Service.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Service.ScheduleModel;
import com.thanhsang.travelapp.repository.Service.ScheduleRepo;

@Service
public class ScheduleServiceService {
    
    @Autowired ScheduleRepo scheduleRepo;
    private MessageResponse messageResponse = new MessageResponse();

    public ResponseEntity<ResponseObject> findById(String id) throws Exception {
        Optional<ScheduleModel> foundSchedule = scheduleRepo.findById(id);
        
        return foundSchedule.isPresent() ? 
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundSchedule)
            ):
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ScheduleModel())
            );
    }

    public ResponseEntity<ResponseObject> findAllByIdServiceForBusiness(String idService) throws Exception {
        List<ScheduleModel> foundSchedule = scheduleRepo.findAllByIdService(idService);
        
        return !foundSchedule.isEmpty() ? 
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundSchedule)
            ):
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, foundSchedule)
            );
    }

    public ResponseEntity<ResponseObject> findAllByIdService(String idService) throws Exception {
        List<ScheduleModel> foundSchedule = scheduleRepo.findAllByIdServiceAndActivity(idService, true);
        
        return !foundSchedule.isEmpty() ? 
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundSchedule)
            ):
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, foundSchedule)
            );
    }

    public ResponseEntity<ResponseObject> insert(@RequestBody ScheduleModel schedule) throws Exception {
        Optional<ScheduleModel> foundSchedule = scheduleRepo.findById(schedule.getId());
        if(foundSchedule.isEmpty()) {
            scheduleRepo.save(schedule);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("success", messageResponse.INSERT_SUCCESS, schedule)
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.INSERT_FAILED, schedule)
        );
    }

    public ResponseEntity<ResponseObject> update(String id, @RequestBody ScheduleModel schedule) throws Exception {
        Optional<ScheduleModel> foundSchedule = scheduleRepo.findById(id);
        if (foundSchedule.isPresent() && id.equals(schedule.getId())) {
            foundSchedule.get().update(schedule);
            scheduleRepo.save(foundSchedule.get());
            
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, schedule)
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, schedule)
        );
    }
}
