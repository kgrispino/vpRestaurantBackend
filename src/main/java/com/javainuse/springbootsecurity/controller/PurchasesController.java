package com.javainuse.springbootsecurity.controller;

import com.javainuse.springbootsecurity.model.CartValidation;
import com.javainuse.springbootsecurity.model.Purchases;
import com.javainuse.springbootsecurity.model.ValidationRequest;
import com.javainuse.springbootsecurity.repository.PurchasesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javainuse.springbootsecurity.config.JwtUtil;

import java.util.Objects;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PurchasesController {
    @Autowired
    private PurchasesRepository purchasesRepo;

    @Autowired
    private JwtUtil jwtUtil;

    private static final Logger log = LoggerFactory.getLogger(PurchasesController.class);

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Purchases savePurchase(Date date, String username, String cart){
        Purchases newPurchase = new Purchases();
        newPurchase.setUsername(username);
        newPurchase.setDate(date);
        newPurchase.setCart(cart);
        return purchasesRepo.save(newPurchase);
    }

    @PostMapping("/purchase/sendCart")
    public boolean validateCart(@RequestBody CartValidation request){
        log.info("Sent");
        if (jwtUtil.validateToken(request.getToken())){
            log.info("Send");
            String username = jwtUtil.getUsernameFromToken(request.getToken());
            Date date = new Date();
            savePurchase(date, username, request.getCart());
            return true;

        }
        return false;

    }

}
