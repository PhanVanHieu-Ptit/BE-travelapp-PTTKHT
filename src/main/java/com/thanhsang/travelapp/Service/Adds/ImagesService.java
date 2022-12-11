package com.thanhsang.travelapp.Service.Adds;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.thanhsang.travelapp.model.Adds.ImagesModel;
import com.thanhsang.travelapp.repository.Adds.ImagesRepo;
import com.thanhsang.travelapp.util.generateUUID;

@Service
public class ImagesService {

    @Autowired ImagesRepo imagesRepo;

    public ResponseEntity<byte[]> findImageById(String id) {
        Optional<ImagesModel> foundImage = imagesRepo.findById(id);

        return foundImage.isPresent() ?
            ResponseEntity.status(HttpStatus.OK)
            .contentType(MediaType.IMAGE_JPEG)
            .body(
                foundImage.get().getData()
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.IMAGE_JPEG)
            .body(
                imagesRepo.findById("1").get().getData()
            );
    }

    public ImagesModel insert(MultipartFile image) throws Exception {

        String imageName = StringUtils.cleanPath(image.getOriginalFilename());
        ImagesModel imageModel = new ImagesModel(generateUUID.generateUuid(), imageName, image.getBytes());

        return imagesRepo.save(imageModel);
    }

}
