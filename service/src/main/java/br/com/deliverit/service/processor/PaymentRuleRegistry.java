package br.com.deliverit.service.processor;

import br.com.deliverit.model.entity.PaymentRule;
import br.com.deliverit.model.repository.PaymentRuleRepository;
import br.com.deliverit.service.service.PaymentRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Payment rule registry operation
 * @author Lucas Koch
 */
@Service
public class PaymentRuleRegistry implements PaymentRuleService {

    @Autowired
    private PaymentRuleRepository repository;

    @Override
    public PaymentRule execute(PaymentRule rule) {
        return repository.save(rule);
    }

    @Override
    public List<PaymentRule> listRules() {
        return repository.findAll();
    }
}
