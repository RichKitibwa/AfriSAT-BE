package com.rich.AfriSAT.controller;

import com.rich.AfriSAT.Repository.ActivationCodeRepository;
import com.rich.AfriSAT.Repository.DecoderRepository;
import com.rich.AfriSAT.Repository.UserRepository;
import com.rich.AfriSAT.model.ActivationCodeStatus;
import com.rich.AfriSAT.model.DecoderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats/")
public class StatisticsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivationCodeRepository activationCodeRepository;

    @Autowired
    private DecoderRepository decoderRepository;

    @GetMapping("/active-codes")
    public ResponseEntity<Long> getActiveCodesCount() {
        long count = activationCodeRepository.countByStatus(ActivationCodeStatus.ACTIVE);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/registered-users")
    public ResponseEntity<Long> getRegisteredUsersCount() {
        long count = userRepository.countByRole("CLIENT");
        return ResponseEntity.ok(count);
    }

    @GetMapping("/active-subscriptions")
    public ResponseEntity<Long> getActiveSubscriptionsCount() {
        long count = decoderRepository.countByDecoderStatus(DecoderStatus.ACTIVE);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/total-sales")
    public ResponseEntity<Double> getTotalSales() {
        Double totalSales = activationCodeRepository.sumCostByStatus(ActivationCodeStatus.ACTIVE);
        return ResponseEntity.ok(totalSales != null ? totalSales : 0.0);
    }
}
