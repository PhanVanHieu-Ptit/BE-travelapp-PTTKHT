package com.thanhsang.travelapp.util;

import java.util.UUID;

public class generateUUID {
    
    public static String generateUuid() {

        UUID id = UUID.randomUUID();
        return id.toString();
    }
}
