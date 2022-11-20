package com.thanhsang.travelapp.Service.Service;

import java.util.List;
import java.util.Map;
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
import com.thanhsang.travelapp.model.Adds.PageRatingReponse;
import com.thanhsang.travelapp.model.Adds.RatingReponse;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Service.OrderServiceModel;
import com.thanhsang.travelapp.model.Service.ServiceModel;
import com.thanhsang.travelapp.repository.Service.OrderServiceRepo;
import com.thanhsang.travelapp.repository.Service.ServiceRepo;

@Service
public class OrderServiceService {
    
    @Autowired OrderServiceRepo orderServiceRepo;
    @Autowired ServiceRepo serviceRepo;
    private MessageResponse messageResponse = new MessageResponse();

    public ResponseEntity<ResponseObject> findAllByIdUser(String idUser, String type, int page, int size) throws Exception {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("dateStart").descending());
        Page<OrderServiceModel> foundOrder = orderServiceRepo.findAllByIdUserAndIdState(idUser, type, pageable);
        return !foundOrder.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundOrder)
            ):
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, foundOrder)
            );
    }

    public ResponseEntity<ResponseObject> findAllByIdService(String idService, String type, int page, int size) throws Exception {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("dateStart").descending());
        Page<OrderServiceModel> foundOrder = orderServiceRepo.findAllByIdServiceAndIdState(idService, type, pageable);
        return !foundOrder.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundOrder)
            ):
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, foundOrder)
            );
    }
    
    public ResponseEntity<ResponseObject> updateState(int idOrder, String state) throws Exception {
        Optional<OrderServiceModel> foundService = orderServiceRepo.findById(idOrder);
        if(foundService.isPresent()) {
            foundService.get().setIdState(state);
            orderServiceRepo.save(foundService.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, foundService.get())
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, new OrderServiceModel())
        );
    }
    
    public ResponseEntity<ResponseObject> updateRating(int idOrder, int star, String comment) throws Exception {
        Optional<OrderServiceModel> foundOrderService = orderServiceRepo.findById(idOrder);
        Optional<ServiceModel> foundService = serviceRepo.findById(foundOrderService.get().getIdService());
        
        if(foundOrderService.isPresent() && foundService.isPresent() && star > 0 && star < 6 & foundOrderService.get().getIdState().equals("HOANTHANH")) {
            foundOrderService.get().setStar(star);
            foundOrderService.get().setComment(comment);
            double starService = foundService.get().getStar();
            int numberRating = foundService.get().getNumberRating();

            try {
                orderServiceRepo.save(foundOrderService.get());

                double totalStar = foundService.get().getStar();
                int totalRating = foundService.get().getNumberRating();
                totalStar = (totalStar*totalRating + star)/(totalRating+1);
                totalRating += 1;
                foundService.get().setStar(totalStar);
                foundService.get().setNumberRating(totalRating);
                serviceRepo.save(foundService.get());

                return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", messageResponse.UPDATE_SUCCESS, foundOrderService)
                ); 
            } catch (Exception e) {
                foundOrderService.get().setComment(null);
                foundOrderService.get().setStar(0);
                foundService.get().setStar(starService);
                foundService.get().setNumberRating(numberRating);
                
                orderServiceRepo.save(foundOrderService.get());
                serviceRepo.save(foundService.get());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", messageResponse.UPDATE_FAILED, foundOrderService)
                ); 
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, foundOrderService)
        ); 
    }
    
    public ResponseEntity<ResponseObject> selectRatingByIdServiceAndStar(String idService, int star, int page, int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size, Sort.by("date_end").descending());
        List<Map<String, RatingReponse>> foundRating = orderServiceRepo.selectRatingByIdServiceAndStar(idService, star, pageable);
        Integer count = orderServiceRepo.selectNumerRatingByIdServiceAndStar(idService, star);
        PageRatingReponse pageReponse = new PageRatingReponse();
        pageReponse.setSize(size);
        pageReponse.setTotalElements(count);
        pageReponse.setElementsOfPage(foundRating.size());
        pageReponse.setCurrentPage(page);
        pageReponse.setRating(foundRating);
        if(count % size == 0) pageReponse.setTotalPages(count / size);
        else pageReponse.setTotalPages(count / size + 1);
        
        return !foundRating.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, pageReponse)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, pageReponse)
            ); 
    }

    public ResponseEntity<ResponseObject> insert(OrderServiceModel orderService) throws Exception {
        Optional<OrderServiceModel> foundOrder = orderServiceRepo.findById(orderService.getId());
        Integer count = orderServiceRepo.findQuantityInStock(orderService.getIdService(), orderService.getIdSchedule(), orderService.getDateStart());

        if(foundOrder.isEmpty() && orderService.getNumber()-count >= 0) {
            orderServiceRepo.save(orderService);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("success", messageResponse.INSERT_SUCCESS, orderService)
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.INSERT_FAILED, orderService)
        );
    }
}
