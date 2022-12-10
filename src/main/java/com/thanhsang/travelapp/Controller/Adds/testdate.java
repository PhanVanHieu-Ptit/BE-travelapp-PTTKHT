package com.thanhsang.travelapp.Controller.Adds;


import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhsang.travelapp.model.Adds.ResponseObject;

@RestController
@RequestMapping("/test_date")
public class testdate {
    
    @GetMapping("")
    public ResponseEntity<ResponseObject> test() {
        Date date = new Date();
        date.setTime(1600000000);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        try {
            Socket s = new Socket("127.0.0.1", 2222);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF("My name is Sang");
            dos.flush();
            dos.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("success", "success", cal.get(Calendar.DAY_OF_WEEK) + "\n")
        );
            
    }
}
