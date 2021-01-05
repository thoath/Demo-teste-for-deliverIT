package br.com.deliverit.service.processor;

import br.com.deliverit.model.entity.Invoice;
import br.com.deliverit.model.repository.InvoiceRepository;
import br.com.deliverit.model.repository.PaymentRuleRepository;
import br.com.deliverit.service.service.InvoiceRegistryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

/**
 * Invoice registry operation
 * @author Lucas Koch
 */
@Service
@AllArgsConstructor
public class InvoiceRegistry implements InvoiceRegistryService {

    private InvoiceRepository invoiceRepository;

    private PaymentRuleRepository paymentRuleRepository;

    @Override
    public Invoice execute(Invoice invoice) {

        getPaymentRule(invoice);

        return invoiceRepository.save(invoice);
    }

    private void getPaymentRule(Invoice invoice) {

       long days =  calculateDaysDiff(invoice);

       paymentRuleRepository
               .findByMinDaysLessThanEqualAndMaxDaysGreaterThanEqual(days, days)
               .ifPresent(invoice::setPaymentRule);
    }

    private long calculateDaysDiff(Invoice invoice) {
        return ChronoUnit.DAYS.between(invoice.getDueDate(), invoice.getPaymentDate());
    }

}
