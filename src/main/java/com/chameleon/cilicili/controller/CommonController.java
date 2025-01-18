package com.chameleon.cilicili.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.impl.DefaultKaptcha;

@RestController
public class CommonController {

    @Autowired
    private DefaultKaptcha kaptcha;
   
    @GetMapping("/captcha")
    public ResponseEntity<?> getCaptcha() {
        HashMap<String, String> map = new HashMap<>();
        String text = kaptcha.createText();
        map.put("captcha", text);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(kaptcha.createImage(text), "jpg", outputStream);
            return ResponseEntity.ok(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
    
}
