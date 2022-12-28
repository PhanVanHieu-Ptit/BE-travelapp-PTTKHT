package com.thanhsang.travelapp.Config;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MailService  {
   @Autowired
   ThymeleafService thymeleafService;
   private static final String CONTENT_TYPE_TEXT_HTML = "text/html;charset=\"utf-8\"";
    public void sendmail(String email, String token) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.mime.charset", "utf-8");
        props.put("mail.smtp.allow8bitmime", "true");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication("ducmanhlai22@gmail.com", "ztgxziumflpxqidx");
           }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("ducmanhlai22@gmail.com", false));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        msg.setSubject("Reset Password");
        msg.setContent(thymeleafService.getContent("http://192.168.1.3:8080/api/v1/password/reset?token="+token), CONTENT_TYPE_TEXT_HTML);
        Transport.send(msg);   
     }
}
