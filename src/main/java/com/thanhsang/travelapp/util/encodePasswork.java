package com.thanhsang.travelapp.util;

import org.apache.commons.codec.digest.DigestUtils;

// import org.springframework.util.DigestUtils;

public class encodePasswork {

    public String encode(String password) {
        // DigestUtils.md5Hex("123456");
        return DigestUtils.sha256Hex(password).toUpperCase();
    }

}
