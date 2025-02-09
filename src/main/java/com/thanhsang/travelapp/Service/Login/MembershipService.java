package com.thanhsang.travelapp.Service.Login;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thanhsang.travelapp.Define.MessageResponse;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Login.MembershipModel;
import com.thanhsang.travelapp.repository.Login.MembershipRepo;
import com.thanhsang.travelapp.util.encodePasswork;
import com.thanhsang.travelapp.util.generateUUID;

@Service
public class MembershipService {
    
    private encodePasswork encode = new encodePasswork();
    @Autowired MembershipRepo membershipRepo;
    private MessageResponse messageResponse = new MessageResponse();


    public ResponseEntity<ResponseObject> findAllMembership(String role, int page, int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size);
        Page<MembershipModel> pageMemership = membershipRepo.findAll(pageable);

        if(!role.equals("ALL")) {
            pageMemership = membershipRepo.findAllByRole(role, pageable);
        }
        return !pageMemership.isEmpty() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, pageMemership)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
    }

    public ResponseEntity<ResponseObject> insertMembership(MembershipModel membership) throws Exception {
        if(membership.checkValid() && membershipRepo.findByUsername(membership.getUsername()).isEmpty()) {
            membership.setUsername(membership.getUsername().toLowerCase());
            membership.setId(generateUUID.generateUuid());
            membership.setPassword(encode.encode(membership.getPassword()));
            membership.setActivity(true);
            membership.setLinkFacebook("");
            membership.setAvatar("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS87Gr4eFO7Pt2pE8oym4dxXnxGZYL2Pl_N5A&usqp=CAU");
            membershipRepo.save(membership);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("success", messageResponse.INSERT_SUCCESS, membership)
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.INSERT_FAILED, membership)
        );
    }

    public ResponseEntity<ResponseObject> updateMembership(MembershipModel member) throws Exception {
        Optional<MembershipModel> membership = membershipRepo.findByUsername(member.getUsername());
        if(member.checkValid() && membership.isPresent()) {
            membership.get().changeValid(member);
            membershipRepo.saveAndFlush(membership.get());

            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, membership.get())
            );
        }

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, member)
        );
    }

    public  ResponseEntity<ResponseObject> changePasswordMembership(String username, String oldPassword, String newPassword1, String newPassword2) throws Exception {
        Optional<MembershipModel> membership = membershipRepo.findByUsername(username.trim());
        String encodePwOld = encode.encode(oldPassword);
        String encodePwNew = encode.encode(newPassword1);

        if(membership.isPresent() && membership.get().getPassword().equals(encodePwOld) && newPassword1.equals(newPassword2)) {
            membership.get().setPassword(encodePwNew);
            membershipRepo.save(membership.get());

            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, true)
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, false)
        );
    }

    public ResponseEntity<ResponseObject> changeRoleMembership(String id, String role) throws Exception {
        Optional<MembershipModel> membership = membershipRepo.findById(id);
        
        if(membership.isPresent()&& !role.equals("ADMIN")) {
            
            membership.get().changeRole(role);
            membershipRepo.save(membership.get());

            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, membership.get())
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, new MembershipModel())
        );
    }

    public ResponseEntity<ResponseObject> loginMembership(String username, String password) throws Exception {
        Optional<MembershipModel> membership = membershipRepo.findByUsername(username.toLowerCase());
        String encodePW = encode.encode(password);
        return membership.isPresent() && membership.get().getPassword().equals(encodePW) && membership.get().getActivity() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.SELECT_SUCCESS, membership)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new MembershipModel())
            );
    }

    public ResponseEntity<ResponseObject> changeActivity(String id_membership) throws Exception {
        Optional<MembershipModel> membership = membershipRepo.findById(id_membership);
        if(membership.isPresent()) {
            membership.get().changeActivity();
            membershipRepo.save(membership.get());

            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, membership.get())
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, new MembershipModel())
        );
    
    }

    public ResponseEntity<ResponseObject> resetPassword(String id_membership) throws Exception {
        Optional<MembershipModel> membership = membershipRepo.findById(id_membership);
        if(membership.isPresent()) {
            Long passwrod = System.currentTimeMillis()%999999;
            String encodePw = encode.encode(String.valueOf(passwrod));
            membership.get().setPassword(String.valueOf(encodePw));
            membershipRepo.save(membership.get());

            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", messageResponse.UPDATE_SUCCESS, membership.get())
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseObject("failed", messageResponse.UPDATE_FAILED, new MembershipModel())
        );
    }
}
