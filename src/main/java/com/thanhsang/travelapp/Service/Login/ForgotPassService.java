package com.thanhsang.travelapp.Service.Login;

import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thanhsang.travelapp.Config.MailSender;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.model.Login.ForgotPassModel;
import com.thanhsang.travelapp.model.Login.MembershipModel;
import com.thanhsang.travelapp.repository.Login.ForgotPassRepo;
import com.thanhsang.travelapp.repository.Login.MembershipRepo;
import com.thanhsang.travelapp.util.generateToken;
import java.sql.Timestamp;

@Service
public class ForgotPassService {
    @Autowired
    MembershipRepo membershipRepo;
    @Autowired
    ForgotPassRepo forgotPassRepo;

    public ResponseEntity<ResponseObject> SendMail(JSONObject object) {
        try {
            String email = (String) object.get("email");
            Optional<MembershipModel> memberModel = membershipRepo.findByEmail(email);
            if (memberModel.isPresent()) {
                String token = generateToken.createToken();
                MailSender.sendmail(email, token);
                ForgotPassModel forgotPassModel = new ForgotPassModel(memberModel.get().getId(),
                        new Timestamp(System.currentTimeMillis()+(60 * 60 * 1000)), token, false); //cộng thêm 1 tiếng
                forgotPassRepo.save(forgotPassModel);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("success", "Gửi thành công", ""));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Không tìm thấy tài khoản", ""));
            }

        } catch (Exception e) {
            System.out.print(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("failed", "Có lỗi xảy ra", ""));
        }
    }

    public ResponseEntity<ResponseObject> ResetPass(JSONObject object) {
        try {
            String token = (String) object.get("token");
            Optional<ForgotPassModel> forgotPassModel = forgotPassRepo.findByToken(token);
            ForgotPassModel forgotModel = forgotPassModel.get();
            if (forgotPassModel.isPresent() && forgotModel.isValid()) {
            String pass= (String) object.get("pass");
            Optional<MembershipModel> memberModel = membershipRepo.findById(forgotModel.getId_user());
            MembershipModel membership= memberModel.get();
            membership.setPassword(pass);
            membershipRepo.save(membership);
            forgotModel.setUsed(true);
            forgotPassRepo.save(forgotModel);
           return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Đổi mật khẩu thành công", ""));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("error", "Token lỗi", ""));
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.print(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Có lỗi xảy ra", ""));
            
        }
    }
}
