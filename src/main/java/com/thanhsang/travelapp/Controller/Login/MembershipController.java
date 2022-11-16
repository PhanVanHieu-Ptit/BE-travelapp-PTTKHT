package com.thanhsang.travelapp.Controller.Login;

import java.util.ArrayList;
import java.util.Optional;

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
import com.thanhsang.travelapp.Service.Login.MembershipService;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Login.MembershipModel;
import com.thanhsang.travelapp.repository.Login.MembershipRepo;
import com.thanhsang.travelapp.repository.Login.RoleRepo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/v1/memberships")
public class MembershipController {
    
    @Autowired MembershipRepo membershipRepo;
    @Autowired MembershipService membershipService;
    @Autowired RoleRepo roleRepo;
    private MessageResponse messageResponse = new MessageResponse();
    
   
    /**
     * @param _role
     * @param _page
     * @return
     */
    @ApiOperation(value = "Get all memerships as per _role, _page, _size", 
                notes = "_type=['ALL' + id of get all roles], _page&_size=[0, 1, 2,...]")
    @GetMapping(path = "")
    public ResponseEntity<ResponseObject> findAll(
        @RequestParam(name = "_role", required = true) String _role, 
        @RequestParam(name = "_page", required = true) int _page,
        @RequestParam(name = "_size", required = true) int _size) {
        try {
            return membershipService.findAllMembership(_role, _page, _size);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new ArrayList<>())
            );
        }
    }
    
    @ApiOperation(value = "Get a membership by id", notes = "")
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable("id") String id) {
        try {
            Optional<MembershipModel> memberships = membershipRepo.findById(id);
            return memberships.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", messageResponse.SELECT_SUCCESS, memberships)
                )
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", messageResponse.SELECT_FAILED, new MembershipModel())
                );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new MembershipModel())
            );
        }
    }

    /**
     * @param membership
     * @return
     */
    @ApiOperation(value = "Insert a membership", notes = "")
    @PostMapping(path = "")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> insert(@RequestBody MembershipModel membership) {
        try {
            return membershipService.insertMembership(membership);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.INSERT_FAILED, membership)
            );
        }
    }

    /**
     * @param member
     * @return
     */
    @ApiOperation(value = "Update a membership", notes = "")
    @PatchMapping(path = "/{id}")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> update(@RequestBody MembershipModel member) {
        try {
            return membershipService.updateMembership(member);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, member)
            );
        }
    }

    @ApiOperation(value = "Update role of a membership by id", notes = "")
    @PatchMapping(path = "{id}/role")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> updateRole(@PathVariable("id") String id, @RequestParam(name = "_role", required = true) String role) {
        try {
            return membershipService.changeRoleMembership(id, role);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, new MembershipModel())
            );
        }
    }
   
    /**
     * @param username
     * @param oldPassword
     * @param newPassword1
     * @param newPassword2
     * @return
     */
    @ApiOperation(value = "Update password a membership", notes = "")
    @PatchMapping(path = "/username/{username}/password")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> updatePassword(@PathVariable("username") String username, 
                                                                    @RequestParam(name = "_oldPassword", required = true) String oldPassword,
                                                                    @RequestParam(name = "_newPassword1", required = true) String newPassword1,
                                                                    @RequestParam(name = "_newPassword2", required = true) String newPassword2) {
        try {
            return membershipService.changePasswordMembership(username, oldPassword, newPassword1, newPassword2);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, false)
            );
        }
    }

    @ApiOperation(value = "Login a membership", notes = "")
    @PostMapping(path = "/login")
    public ResponseEntity<ResponseObject> login(@RequestParam(name = "username", required = true) String username,
                                                @PathVariable(name = "password", required = true) String password) {
        try {
            return membershipService.loginMembership(username, password);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", messageResponse.SELECT_FAILED, new MembershipModel())
            );
        }
    }

    @ApiOperation(value = "Update state a membership by id", notes = "")
    @PatchMapping(path = "/{id}/activity")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> updateActivity(@PathVariable String id) {
        try {
            return membershipService.changeActivity(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, new MembershipModel())
            );
        }
    }

    @ApiOperation(value = "Reset password a membership by id", notes = "")
    @PatchMapping(path = "/{id}/password")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<ResponseObject> resetPassword(@PathVariable String id) {
        try {
            return membershipService.resetPassword(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", messageResponse.UPDATE_FAILED, new MembershipModel())
            );
        }
    }
}
