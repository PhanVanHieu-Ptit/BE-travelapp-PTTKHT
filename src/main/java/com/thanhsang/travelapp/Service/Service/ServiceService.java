package com.thanhsang.travelapp.Service.Service;

import java.util.ArrayList;
import java.util.List;
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
import com.thanhsang.travelapp.model.Service.ServiceModel;
import com.thanhsang.travelapp.repository.Service.ServiceRepo;

@Service
public class ServiceService {

    @Autowired ServiceRepo serviceRepo;
    private MessageResponse messageResponse = new MessageResponse();
    
    public ResponseEntity<ResponseObject> findAll(
        String type, String sort, int page, int size) throws Exception {

        Pageable pageable = PageRequest.of(page, size);
        Page<ServiceModel> pageService;

        if(type.equals("ALL") && sort.equals("ALL")) {
            pageService = serviceRepo.findAllByActivity(true, pageable);
        } else if (type.equals("ALL") && !sort.equals("ALL")){
            if(sort.equals("price")) pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
            else pageable = PageRequest.of(page, size, Sort.by(sort).descending());
            pageService = serviceRepo.findAllByActivity(true, pageable);
        } else if (!type.equals("ALL") && sort.equals("ALL")) {
            pageService = serviceRepo.findAllByActivityAndIdTypeService(true, type, pageable);
        } else {
            if(sort.equals("price")) pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
            else pageable = PageRequest.of(page, size, Sort.by(sort).descending());
            pageService = serviceRepo.findAllByActivityAndIdTypeService(true, type, pageable);
        }

        return !pageService.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, pageService)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
    }

    public ResponseEntity<ResponseObject> findAllForManager(
        String type, String sort, int page, int size) throws Exception {

        Pageable pageable = PageRequest.of(page, size);
        Page<ServiceModel> pageService;

        if(type.equals("ALL") && sort.equals("ALL")) {
            pageService = serviceRepo.findAll(pageable);
        } else if (type.equals("ALL") && !sort.equals("ALL")){
            if(sort.equals("price")) pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
            else pageable = PageRequest.of(page, size, Sort.by(sort).descending());
            pageService = serviceRepo.findAll(pageable);
        } else if (!type.equals("ALL") && sort.equals("ALL")) {
            pageService = serviceRepo.findAllByActivityAndIdTypeService(true, type, pageable);
        } else {
            if(sort.equals("price")) pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
            else pageable = PageRequest.of(page, size, Sort.by(sort).descending());
            pageService = serviceRepo.findAllByIdTypeService(type, pageable);
        }

        return !pageService.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, pageService)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
    }

    public ResponseEntity<ResponseObject> insert(ServiceModel service) throws Exception {
        if(service.checkValid()) {
            Optional<ServiceModel> foundService = serviceRepo.findByIdMembership(service.getIdMembership());
            if(foundService.isEmpty() && service.checkValid()) {
                serviceRepo.save(service);

                return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponseObject("success", messageResponse.INSERT_SUCCESS, service)
                );
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.INSERT_FAILED, service)
        );
    }
    
    public ResponseEntity<ResponseObject> update(String id, ServiceModel service) throws Exception{
        if(service.checkValid() && id.equals(service.getId())) {
            Optional<ServiceModel> foundService = serviceRepo.findById(id);
            if(foundService.isPresent()) {
                foundService.get().changeValid(service);
                serviceRepo.save(foundService.get());

                return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", messageResponse.UPDATE_SUCCESS, foundService)
                );
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, service)
        );
    }

    public ResponseEntity<ResponseObject> updateActivity(String id) throws Exception {
        Optional<ServiceModel> foundService = serviceRepo.findById(id);
        if(foundService.isPresent()) {
            foundService.get().changeActivity();
            serviceRepo.save(foundService.get());

            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, foundService)
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, new ServiceModel())
        );
    }

    public ResponseEntity<ResponseObject> findTop10() {
        List<ServiceModel> foundService = serviceRepo.findTop10ByOrderByStarDesc();

        return !foundService.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundService)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, foundService)
            );
    }

    

    // public ResponseEntity<ResponseObject> findAllGroupBy() {
    //     List<ServiceModel> foundService = serviceRepo.findTop3Services();

    //     return !foundService.isEmpty() ?
    //         ResponseEntity.status(HttpStatus.OK).body(
    //             new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundService)
    //         )
    //         :
    //         ResponseEntity.status(HttpStatus.NOT_FOUND).body(
    //             new ResponseObject("failed", messageResponse.SELECT_FAILED, foundService)
    //         );
    // }

    public ResponseEntity<ResponseObject> findById(String id) {
        Optional<ServiceModel> foundService = serviceRepo.findById(id);

        return foundService.isPresent() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundService)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, foundService)
            );
    }
}
