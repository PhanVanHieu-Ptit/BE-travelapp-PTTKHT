package com.thanhsang.travelapp.Service.Hotel;

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
import com.thanhsang.travelapp.model.Hotel.OrderRoomRequestModel;
import com.thanhsang.travelapp.model.Hotel.HotelModel;
import com.thanhsang.travelapp.model.Hotel.OrderRoomDetailModel;
import com.thanhsang.travelapp.model.Hotel.OrderRoomModel;
import com.thanhsang.travelapp.repository.Hotel.HotelRepo;
import com.thanhsang.travelapp.repository.Hotel.OrderRoomDetailRepo;
import com.thanhsang.travelapp.repository.Hotel.OrderRoomRepo;

@Service
public class OrderRoomService {
    
    @Autowired HotelRepo hotelRepo;
    @Autowired OrderRoomRepo orderRoomRepo;
    @Autowired OrderRoomDetailRepo orderRoomDetailRepo;
    private MessageResponse messageResponse = new MessageResponse();
    
    public ResponseEntity<ResponseObject> findOrderRoomByIdUser(String idUser, String type, int page, int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size, Sort.by("date_now").descending());
        List<Map<Integer, OrderHistoryModel>> foundOrderRoom = orderRoomRepo.findAllByIdUserAndIdState(idUser, type, pageable);
        int count = orderRoomRepo.selectNumberOrderByIdUserAndIdState(idUser, type);
        PageOrderHistoryReponse pageReponse = new PageOrderHistoryReponse();
        pageReponse.setSize(size);
        pageReponse.setTotalElements(count);
        pageReponse.setElementsOfPage(foundOrderRoom.size());
        pageReponse.setCurrentPage(page);
        pageReponse.setOrders(foundOrderRoom);

        if(count % size == 0) pageReponse.setTotalPages(count / size);
        else pageReponse.setTotalPages(count / size + 1);

        return !foundOrderRoom.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, pageReponse)
            ):
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, pageReponse)
            );
    }

    public ResponseEntity<ResponseObject> findOrderRoomByIdHotel(String idHotel, String type, int page, int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size, Sort.by("date_now").descending());
        if(type.equals("XACNHAN")) {
            pageable = PageRequest.of(page, size, Sort.by("date_now").ascending());
        } else if(type.equals("THANHCONG")) {
            pageable = PageRequest.of(page, size, Sort.by("date_end").ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by("date_now").descending());
        }
        List<Map<Integer, OrderHistoryModel>> foundOrderRoom = orderRoomRepo.findAllByIdHotelAndIdState(idHotel, type, pageable);
        int count = orderRoomRepo.selectNumberOrderByIdHotelAndIdState(idHotel, type);
        PageOrderHistoryReponse pageReponse = new PageOrderHistoryReponse();
        pageReponse.setSize(size);
        pageReponse.setTotalElements(count);
        pageReponse.setElementsOfPage(foundOrderRoom.size());
        pageReponse.setCurrentPage(page);
        pageReponse.setOrders(foundOrderRoom);

        if(count % size == 0) pageReponse.setTotalPages(count / size);
        else pageReponse.setTotalPages(count / size + 1);

        return !foundOrderRoom.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, pageReponse)
            ):
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, pageReponse)
            );
    }

    public ResponseEntity<ResponseObject> insert(OrderRoomRequestModel order) throws Exception {
        int idOrder = 0;
        try {
            order.getOrder().setStar(0);
            order.getOrder().setComment(null);
            order.getOrder().setIdState("XACNHAN");
            OrderRoomModel saveOrder = orderRoomRepo.save(order.getOrder());
            idOrder = saveOrder.getId();
            for (OrderRoomDetailModel ord : order.getOrderDetail()) {
                ord.setIdOrder(saveOrder.getId());
                int quantityOnStock = orderRoomRepo.findQuantityInStock(saveOrder.getIdHotel(), saveOrder.getDateStart(), saveOrder.getDateEnd(), ord.getIdRoom());
                if(quantityOnStock - ord.getNumber() >= 0) orderRoomDetailRepo.save(ord);
                else {
                    orderRoomRepo.deleteById(saveOrder.getId());
                    orderRoomDetailRepo.deleteAllByIdOrder(saveOrder.getId());
                    return ResponseEntity.status(HttpStatus.CREATED).body(
                        new ResponseObject("failed", messageResponse.INSERT_FAILED, order)
                    );
                }
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("success", messageResponse.INSERT_SUCCESS, order)
            );
        } catch (Exception e) {
            orderRoomRepo.deleteById(idOrder);
            orderRoomDetailRepo.deleteAllByIdOrder(idOrder);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("failed", messageResponse.INSERT_FAILED, order)
            );
        }
    }

    public ResponseEntity<ResponseObject> updateState(int idOrder, String idState) throws Exception {
        Optional<OrderRoomModel> foundOrderRoom = orderRoomRepo.findById(idOrder);
        if(foundOrderRoom.isPresent()) {
            foundOrderRoom.get().setIdState(idState);
            orderRoomRepo.save(foundOrderRoom.get());

            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, foundOrderRoom)
            ); 
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, foundOrderRoom)
            ); 
    }

    public ResponseEntity<ResponseObject> selectRatingByIdHotelAndStar(String idHotel, int star, int page, int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size, Sort.by("date_end").descending());
        List<Map<String, RatingReponse>> foundRating = orderRoomRepo.selectRatingByIdHotelAndStar(idHotel, star, pageable);
        Integer count = orderRoomRepo.selectNumberRatingByIdHotelAndStar(idHotel, star);
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
        Optional<OrderRoomModel> foundOrderRoom = orderRoomRepo.findById(idOrder);
        Optional<HotelModel> foundHotel = hotelRepo.findById(foundOrderRoom.get().getIdHotel());

        if(foundOrderRoom.isPresent() && star > 0 && star < 6 & foundOrderRoom.get().getIdState().equals("HOANTHANH")) {
            foundOrderRoom.get().setStar(star);
            foundOrderRoom.get().setComment(comment);
            double starHotel = foundHotel.get().getStar();
            int numberRating = foundHotel.get().getNumberRating();

            try {
                orderRoomRepo.save(foundOrderRoom.get());
                double totalStar = foundHotel.get().getStar();
                int totalRating = foundHotel.get().getNumberRating();
                totalStar = (totalStar*totalRating + star)/(totalRating+1);
                totalRating += 1;
                foundHotel.get().setStar(totalStar);
                foundHotel.get().setNumberRating(totalRating);
                hotelRepo.save(foundHotel.get());

                return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", messageResponse.UPDATE_SUCCESS, foundOrderRoom)
                ); 
            } catch (Exception e) {
                foundOrderRoom.get().setComment(null);
                foundOrderRoom.get().setStar(0);
                foundHotel.get().setStar(starHotel);
                foundHotel.get().setNumberRating(numberRating);
                
                orderRoomRepo.save(foundOrderRoom.get());
                hotelRepo.save(foundHotel.get());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", messageResponse.UPDATE_FAILED, foundOrderRoom)
                ); 
            }
            
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, foundOrderRoom)
        );
    }
    
    // public ResponseEntity<ResponseObject> selectRatingByIdHotel(String idHotel, int page, int size) throws Exception {
    //     Pageable pageable = PageRequest.of(page, size, Sort.by("date_end").descending());
    //     List<Map<String, RatingReponse>> foundRating = orderRoomRepo.selectRatingByIdHotel(idHotel, pageable);
    //     Integer count = orderRoomRepo.selectNumerRatingByIdHotel(idHotel);
    //     PageRatingReponse pageReponse = new PageRatingReponse();
    //     pageReponse.setSize(size);
    //     pageReponse.setTotalElements(count);
    //     pageReponse.setElementsOfPage(foundRating.size());
    //     pageReponse.setCurrentPage(page);
    //     pageReponse.setRating(foundRating);
    //     if(count % size == 0) pageReponse.setTotalPages(count / size);
    //     else pageReponse.setTotalPages(count / size + 1);

    //     return !foundRating.isEmpty() ?
    //         ResponseEntity.status(HttpStatus.OK).body(
    //             new ResponseObject("success", messageResponse.SELECT_SUCCESS, pageReponse)
    //         )
    //         :
    //         ResponseEntity.status(HttpStatus.NOT_FOUND).body(
    //             new ResponseObject("failed", messageResponse.SELECT_FAILED, pageReponse)
    //         ); 
    // }
}
