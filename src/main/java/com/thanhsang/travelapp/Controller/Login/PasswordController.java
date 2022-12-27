package com.thanhsang.travelapp.Controller.Login;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thanhsang.travelapp.Service.Login.ForgotPassService;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.repository.Login.ForgotPassRepo;

@Controller
@RequestMapping(path = "/api/v1/password")
public class PasswordController {

    @Autowired ForgotPassRepo forgotRepo;
    @Autowired ForgotPassService forgotPassService;
    @RequestMapping("/reset")
    public String resetPass() {
        return "resetPass";
    }
    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseObject> sendLink(@RequestBody JSONObject body) {
        ResponseEntity<ResponseObject> result=  forgotPassService.SendMail(body);
        return result ;
    }
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseObject> resetPass(@RequestBody JSONObject body) {
        ResponseEntity<ResponseObject> result=  forgotPassService.ResetPass(body);
        return result ;
    }
}
