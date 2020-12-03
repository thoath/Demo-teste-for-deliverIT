package br.com.deliverit.service.controller;

import br.com.deliverit.model.entity.PaymentRule;
import br.com.deliverit.service.service.PaymentRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class PaymentRuleController {

    @Autowired
    private PaymentRuleService service;

    @PostMapping("/rule")
    public ResponseEntity registryRule(@RequestBody @Valid PaymentRule paymentRule) {
        service.execute(paymentRule);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
