package br.com.deliverit.service.service;

import br.com.deliverit.model.entity.PaymentRule;

import java.util.List;

public interface PaymentRuleService {

    PaymentRule execute(PaymentRule rule);
    List<PaymentRule> listRules();
}
