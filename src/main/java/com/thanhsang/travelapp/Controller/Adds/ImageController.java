package com.thanhsang.travelapp.Controller.Adds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thanhsang.travelapp.Service.Adds.ImagesService;
import com.thanhsang.travelapp.model.Adds.ImagesModel;
import com.thanhsang.travelapp.model.Adds.ResponseObject;
import com.thanhsang.travelapp.repository.Adds.ImagesRepo;

@RestController
@RequestMapping(value = "/api/v1/images")
public class ImageController {
    
    @Autowired ImagesService imagesService;
    @Autowired ImagesRepo imagesRepo;

    @GetMapping(value = "/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) {
        try {
            return imagesService.findImageById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                "".getBytes()
            );
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<ResponseObject> insert(@RequestParam(name = "image", required = true) MultipartFile image) {
        try {
            ImagesModel data = imagesService.insert(image);
            return ResponseEntity
                .status(HttpStatus.CREATED)
                        .body(
                            new ResponseObject("success", "Thêm hình thành công", "/api/v1/images/"+data.getId())
                        );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Thêm hình thất bại", "")
            );
        }
    }

    @DeleteMapping(value = "") 
    public ResponseEntity<ResponseObject> delete(@RequestParam(name = "url", required = true) String url) {
        try {
            String id = url.split("/images/")[1];
            imagesRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ResponseObject("success", "Xoá hình thành công", url)  
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Xoá hình thất bại", "")
            );
        }
    }
}
