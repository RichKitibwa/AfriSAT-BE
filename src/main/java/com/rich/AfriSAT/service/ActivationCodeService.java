package com.rich.AfriSAT.service;

import com.rich.AfriSAT.Repository.ActivationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivationCodeService {

    @Autowired
    private ActivationCodeRepository activationCodeRepository;
}
