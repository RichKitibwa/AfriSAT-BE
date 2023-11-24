package com.rich.AfriSAT.controller;

import com.rich.AfriSAT.service.ActivationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activation-codes/")
public class ActivationCodeController {

    @Autowired
    private ActivationCodeService activationCodeService;


}
