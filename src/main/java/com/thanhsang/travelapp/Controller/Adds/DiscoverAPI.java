package com.thanhsang.travelapp.Controller.Adds;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thanhsang.travelapp.repository.Adds.DiscoverRepo;

@RestController
@RequestMapping("/api/v1/discovers")
public class DiscoverAPI {

    @Autowired DiscoverRepo discoveryRepo;
}
