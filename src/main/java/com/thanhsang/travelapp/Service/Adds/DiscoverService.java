package com.thanhsang.travelapp.Service.Adds;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.model.Adds.DiscoveryModel;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.repository.Adds.DiscoverRepo;

@Service
public class DiscoverService {
    
    @Autowired DiscoverRepo discoverRepo;
    private MessageResponse messageResponse = new MessageResponse();

    public ResponseEntity<ResponseObject> findAll(int page, int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size);
        Page<DiscoveryModel> foundDiscovers = discoverRepo.findAll(pageable);
        return !foundDiscovers.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundDiscovers)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, foundDiscovers)
            ); 
    }

    public ResponseEntity<ResponseObject> findById(String id) throws Exception {
        Optional<DiscoveryModel> foundDiscover = discoverRepo.findById(id);
        return foundDiscover.isPresent() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundDiscover.get())
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new DiscoveryModel())
            ); 
    }

    public ResponseEntity<ResponseObject> insert(DiscoveryModel model) throws Exception {
        Optional<DiscoveryModel> foundDiscover = discoverRepo.findById(model.getId());
        if(foundDiscover.isEmpty()) {
            discoverRepo.save(model);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("success", messageResponse.INSERT_SUCCESS, model)
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.INSERT_FAILED, new DiscoveryModel())
        ); 
    }

    public ResponseEntity<ResponseObject> update(String id, DiscoveryModel model) throws Exception {
        Optional<DiscoveryModel> foundDiscover = discoverRepo.findById(id);
        if(foundDiscover.isPresent() && id.equals(model.getId())) {
            discoverRepo.save(model);
            
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, model)
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, new DiscoveryModel())
        ); 
    }

    public ResponseEntity<ResponseObject> delete(String id) throws Exception {
        Optional<DiscoveryModel> foundDiscover = discoverRepo.findById(id);
        if(foundDiscover.isPresent()) {
            
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ResponseObject("success", messageResponse.DELETE_SUCCESS, discoverRepo.deleteById(id))
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("failed", messageResponse.DELETE_FAILED, new DiscoveryModel())
        ); 
    }

}
