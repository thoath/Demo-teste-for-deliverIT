package br.com.deliverit.service.processor;

import br.com.deliverit.api.dto.InvoiceDto;
import br.com.deliverit.model.entity.Invoice;
import br.com.deliverit.model.entity.PaymentRule;
import br.com.deliverit.model.repository.InvoiceRepository;
import br.com.deliverit.model.repository.PaymentRuleRepository;
import br.com.deliverit.service.service.InvoiceListService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Compute the invoice data to final user
 * @author Lucas Koch
 */
@AllArgsConstructor
@NoArgsConstructor
@Service
public class InvoiceListProcessor implements InvoiceListService {

    private InvoiceRepository invoiceRepository;

    private PaymentRuleRepository paymentRuleRepository;

    private final static BigDecimal DIVIDE = new BigDecimal(100);

    @Override
    public List<InvoiceDto> execute() {

        List<Invoice> invoices = invoiceRepository.findAll();
        final List<PaymentRule> rules = paymentRuleRepository.findAll();
        return invoices
                .stream()
                .map(invoice -> this.buildInvoiceDto(invoice, rules))
                .collect(Collectors.toList());

    }

    private InvoiceDto buildInvoiceDto(Invoice invoice, List<PaymentRule> rules) {

        long daysDiff = calculateDaysDiff(invoice);
        daysDiff = daysDiff > 0 ? daysDiff : 0;

        return InvoiceDto.builder()
                .amount(invoice.getAmount())
                .delayInDays(daysDiff)
                .fixedAmount(calculateFixedAmount(daysDiff, invoice.getAmount(), rules))
                .name(invoice.getName())
                .paymentDate(invoice.getPaymentDate())
                .build();
    }

    private BigDecimal calculateFixedAmount(long daysDiff, BigDecimal amount, List<PaymentRule> rules) {

        if (daysDiff == 0) {
            return amount;
        }

        rules.sort(Comparator.comparing(PaymentRule::getDelayInDays));
        BigDecimal fixedAmount = amount;

       for (PaymentRule rule : rules) {
           if (daysDiff <= rule.getDelayInDays()) {

               fixedAmount = fixedAmount.add(
                       fixedAmount.multiply(
                               BigDecimal.valueOf(rule.getPenalty())).divide(DIVIDE, RoundingMode.UP));

               BigDecimal valuePerDay = fixedAmount.multiply(
                       BigDecimal.valueOf(rule.getInterestDay())).divide(DIVIDE, RoundingMode.UP);

               fixedAmount = fixedAmount.add(valuePerDay.multiply(BigDecimal.valueOf(daysDiff)));

               break;
           }
       }

        return fixedAmount.setScale(2, RoundingMode.UP);
    }

    private long calculateDaysDiff(Invoice invoice) {
        return ChronoUnit.DAYS.between(invoice.getDueDate(), invoice.getPaymentDate());
    }
}
