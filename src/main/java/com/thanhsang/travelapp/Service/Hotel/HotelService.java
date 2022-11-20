package com.thanhsang.travelapp.Service.Hotel;

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
import com.thanhsang.travelapp.model.Hotel.HotelModel;
import com.thanhsang.travelapp.model.Hotel.RoomModel;
import com.thanhsang.travelapp.repository.Hotel.HotelRepo;
import com.thanhsang.travelapp.repository.Hotel.RoomRepo;
import com.thanhsang.travelapp.repository.Login.MembershipRepo;

@Service
public class HotelService {

    @Autowired HotelRepo hotelRepo;
    @Autowired MembershipRepo membershipRepo;
    @Autowired RoomRepo roomRepo;
    @Autowired RoomService roomService;
    private MessageResponse messageResponse = new MessageResponse();

    /**
     * @param type [ALL, HOTEL, HOMESTAY, VILLA, NGUYENCAN]
     * @param sort [ALL, star, priceMin, priceMax]
     * @param page [0, 1, 2, ...]
     * @return
     */
    public ResponseEntity<ResponseObject> findAll(
        String type, String sort, int page, int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size);
        Page<HotelModel> pageHotel;

        if(type.equals("ALL") && sort.equals("ALL")) {
            pageHotel = hotelRepo.findAllByActivity(true, pageable);
        } else if (type.equals("ALL") && !sort.equals("ALL")){
            if(sort.equals("priceMin")) pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
            else pageable = PageRequest.of(page, size, Sort.by(sort).descending());
            pageHotel = hotelRepo.findAllByActivity(true, pageable);
        } else if (!type.equals("ALL") && sort.equals("ALL")) {
            pageHotel = hotelRepo.findAllByActivityAndIdTypeHotel(true, type, pageable);
        } else {
            if(sort.equals("priceMin")) pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
            else pageable = PageRequest.of(page, size, Sort.by(sort).descending());
            pageHotel = hotelRepo.findAllByActivityAndIdTypeHotel(true, type, pageable);
        }

        return !pageHotel.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, pageHotel)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
    }

    public ResponseEntity<ResponseObject> findAllForManager(
        String type, String sort, int page, int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size);
        Page<HotelModel> pageHotel;

        if(type.equals("ALL") && sort.equals("ALL")) {
            pageHotel = hotelRepo.findAll(pageable);
        } else if (type.equals("ALL") && !sort.equals("ALL")){
            if(sort.equals("priceMin")) pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
            else pageable = PageRequest.of(page, size, Sort.by(sort).descending());
            pageHotel = hotelRepo.findAll(pageable);
        } else if (!type.equals("ALL") && sort.equals("ALL")) {
            pageHotel = hotelRepo.findAllByIdTypeHotel(type, pageable);
        } else {
            if(sort.equals("priceMin")) pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
            else pageable = PageRequest.of(page, size, Sort.by(sort).descending());
            pageHotel = hotelRepo.findAllByIdTypeHotel(type, pageable);
        }

        return !pageHotel.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, pageHotel)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
    }

    public ResponseEntity<ResponseObject> findById(String id) throws Exception{
        Optional<HotelModel> foundHotel = hotelRepo.findById(id);
        return foundHotel.isEmpty() ?
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundHotel)
            )
            :
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new HotelModel())
            );
    }

    public ResponseEntity<ResponseObject> insert(HotelModel hotel) throws Exception{
        Optional<HotelModel> foundHotel = hotelRepo.findById(hotel.getId());
        if(foundHotel.isEmpty() && hotel.checkValid()) {
            hotelRepo.save(hotel);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("success", messageResponse.INSERT_SUCCESS, hotel)
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.INSERT_FAILED, hotel)
        );
    }

    public ResponseEntity<ResponseObject> update(String id, HotelModel hotel) throws Exception {
        Optional<HotelModel> foundHotel = hotelRepo.findById(hotel.getId());
        if(foundHotel.isPresent() && hotel.checkValid() && id.equals(hotel.getId())) {
            foundHotel.get().changeValid(hotel);
            hotelRepo.save(foundHotel.get());

            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, foundHotel)
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, hotel)
        );
    }

    public ResponseEntity<ResponseObject> updateActivity(String id) throws Exception {
        Optional<HotelModel> foundHotel = hotelRepo.findById(id);
        if(foundHotel.isPresent()) {
            foundHotel.get().setActivity(!foundHotel.get().isActivity());
            List<RoomModel> foundRoom = roomRepo.findAllByIdHotel(id);
            hotelRepo.save(foundHotel.get());
            for (RoomModel room : foundRoom) {
                room.setActivity(false);
                roomRepo.save(room);
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, foundHotel)
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, new HotelModel())
        );
    }

    public ResponseEntity<ResponseObject> findTop10() {
        List<HotelModel> foundHotels = hotelRepo.findTop10ByOrderByStarDesc();

        return !foundHotels.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, foundHotels)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, foundHotels)
            );
    }
}
