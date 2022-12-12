package com.thanhsang.travelapp.Service.Food;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.model.Adds.OrderHistoryModel;
import com.thanhsang.travelapp.model.Adds.PageOrderHistoryReponse;
import com.thanhsang.travelapp.model.Adds.PageRatingReponse;
import com.thanhsang.travelapp.model.Adds.RatingReponse;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Food.FoodModel;
import com.thanhsang.travelapp.model.Food.OrderFoodDetailModel;
import com.thanhsang.travelapp.model.Food.OrderFoodModel;
import com.thanhsang.travelapp.model.Food.OrderFoodRequestModel;
import com.thanhsang.travelapp.repository.Food.FoodRepo;
import com.thanhsang.travelapp.repository.Food.OrderFoodDetailRepo;
import com.thanhsang.travelapp.repository.Food.OrderFoodRepo;

@Service
public class OrderFoodService {
    
    @Autowired FoodRepo foodRepo;
    @Autowired OrderFoodRepo orderFoodRepo;
    @Autowired OrderFoodDetailRepo orderFoodDetailRepo;
    private MessageResponse messageResponse = new MessageResponse();

    public ResponseEntity<ResponseObject> findOrderFoodByIdUserAndState(String idUser, String type, int page, int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size, Sort.by("date_now").descending());
        List<Map<Integer, OrderHistoryModel>> foundOrderFood = orderFoodRepo.findAllByIdUserAndIdState(idUser, type, pageable);
        int count = orderFoodRepo.selectNumberOrderByIdUserAndIdState(idUser, type);
        PageOrderHistoryReponse pageReponse = new PageOrderHistoryReponse();
        pageReponse.setSize(size);
        pageReponse.setTotalElements(count);
        pageReponse.setElementsOfPage(foundOrderFood.size());
        pageReponse.setCurrentPage(page);
        pageReponse.setOrders(foundOrderFood);

        if(count % size == 0) pageReponse.setTotalPages(count / size);
        else pageReponse.setTotalPages(count / size + 1);

        return !foundOrderFood.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, pageReponse)
            ):
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, pageReponse)
            );
    }

    public ResponseEntity<ResponseObject> findOrderFoodByIdFoodAndState(String idFood, String type, int page, int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size, Sort.by("date_now").descending());
        List<Map<Integer, OrderHistoryModel>> foundOrderFood = orderFoodRepo.findAllByIdFoodAndIdState(idFood, type, pageable);
        int count = orderFoodRepo.selectNumberOrderByIdFoodAndIdState(idFood, type);
        PageOrderHistoryReponse pageReponse = new PageOrderHistoryReponse();
        pageReponse.setSize(size);
        pageReponse.setTotalElements(count);
        pageReponse.setElementsOfPage(foundOrderFood.size());
        pageReponse.setCurrentPage(page);
        pageReponse.setOrders(foundOrderFood);

        if(count % size == 0) pageReponse.setTotalPages(count / size);
        else pageReponse.setTotalPages(count / size + 1);

        return !foundOrderFood.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, pageReponse)
            ):
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, pageReponse)
            );
    }

    public ResponseEntity<ResponseObject> insert(OrderFoodRequestModel order) throws Exception {
        int idOrder = 0;
        try {
            order.getOrder().setStar(0);
            order.getOrder().setComment(null);
            order.getOrder().setIdState("XACNHAN");

            OrderFoodModel saveOrder = orderFoodRepo.save(order.getOrder());
            idOrder = saveOrder.getId();
            for (OrderFoodDetailModel ord : order.getOrderDetail()) ord.setIdOrder(saveOrder.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("success", messageResponse.INSERT_SUCCESS, order)
            );
        } catch (Exception e) {
            orderFoodRepo.deleteById(idOrder);
            orderFoodDetailRepo.deleteAllByIdOrder(idOrder);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("failed", messageResponse.INSERT_FAILED, order)
            );
        }
    }

    public ResponseEntity<ResponseObject> updateState(int idOrder, String idState) throws Exception {
        Optional<OrderFoodModel> foundOrderRoom = orderFoodRepo.findById(idOrder);
        if(foundOrderRoom.isPresent()) {
            foundOrderRoom.get().setIdState(idState);
            orderFoodRepo.save(foundOrderRoom.get());

            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, foundOrderRoom)
            ); 
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, foundOrderRoom)
            ); 
    }

    public ResponseEntity<ResponseObject> selectRatingByIdFoodAndStar(String idFood, int star, int page, int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size, Sort.by("date_start").descending());
        List<Map<String, RatingReponse>> foundRating = orderFoodRepo.selectRatingByIdFoodAndStar(idFood, star, pageable);
        Integer count = orderFoodRepo.selectNumberRatingByIdFoodAndStar(idFood, star);
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

    public ResponseEntity<ResponseObject> updateRating(int idOrder, int star, String comment) throws Exception {
        Optional<OrderFoodModel> foundOrderFood = orderFoodRepo.findById(idOrder);
        Optional<FoodModel> foundFood = foodRepo.findById(foundOrderFood.get().getIdFood());

        if(foundOrderFood.isPresent() && star > 0 && star < 6 & foundOrderFood.get().getIdState().equals("HOANTHANH")) {
            foundOrderFood.get().setStar(star);
            foundOrderFood.get().setComment(comment);
            double starService = foundFood.get().getStar();
            int numberRating = foundFood.get().getNumberRating();
            try {
                orderFoodRepo.save(foundOrderFood.get());

                double totalStar = foundFood.get().getStar();
                int totalRating = foundFood.get().getNumberRating();
                totalStar = (totalStar*totalRating + star)/(totalRating+1);
                totalRating += 1;
                foundFood.get().setStar(totalStar);
                foundFood.get().setNumberRating(totalRating);
                foodRepo.save(foundFood.get());

                return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", messageResponse.UPDATE_SUCCESS, foundOrderFood)
                ); 
            } catch (Exception e) {
                foundOrderFood.get().setComment(null);
                foundOrderFood.get().setStar(0);
                foundFood.get().setStar(starService);
                foundFood.get().setNumberRating(numberRating);
                
                orderFoodRepo.save(foundOrderFood.get());
                foodRepo.save(foundFood.get());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", messageResponse.UPDATE_FAILED, foundOrderFood)
                ); 
            }

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, foundOrderFood)
        );
    }

}
