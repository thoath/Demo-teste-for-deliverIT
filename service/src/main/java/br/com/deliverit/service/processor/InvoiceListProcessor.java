package br.com.deliverit.service.processor;

import br.com.deliverit.api.dto.InvoiceDto;
import br.com.deliverit.model.entity.Invoice;
import br.com.deliverit.model.entity.PaymentRule;
import br.com.deliverit.model.repository.InvoiceRepository;
import br.com.deliverit.model.repository.PaymentRuleRepository;
import br.com.deliverit.service.service.InvoiceListService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Compute the invoice data to final user
 * @author Lucas Koch
 */
@AllArgsConstructor
@Service
public class InvoiceListProcessor implements InvoiceListService {

    private InvoiceRepository invoiceRepository;

    private PaymentRuleRepository paymentRuleRepository;

    private final static BigDecimal DIVIDE = new BigDecimal(100);

    @Override
    public List<InvoiceDto> execute() {

        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices
                .stream()
                .map(invoice -> this.buildInvoiceDto(invoice))
                .collect(Collectors.toList());

    }

    private InvoiceDto buildInvoiceDto(Invoice invoice) {

        long daysDiff = calculateDaysDiff(invoice);
        daysDiff = daysDiff > 0 ? daysDiff : 0;

        return InvoiceDto.builder()
                .id(invoice.getId())
                .amount(invoice.getAmount())
                .delayInDays(daysDiff)
                .fixedAmount(calculateFixedAmount(daysDiff, invoice.getAmount(), invoice.getPaymentRule()))
                .name(invoice.getName())
                .paymentDate(invoice.getPaymentDate())
                .build();
    }

    private BigDecimal calculateFixedAmount(long daysDiff, BigDecimal amount, PaymentRule paymentRule) {

        if (daysDiff == 0 || paymentRule == null) {
            return amount;
        }

        BigDecimal fixedAmount = amount;

        fixedAmount = fixedAmount.add(
               fixedAmount.multiply(
                       BigDecimal.valueOf(paymentRule.getPenalty())).divide(DIVIDE, RoundingMode.UP));
        BigDecimal valuePerDay = fixedAmount.multiply(
               BigDecimal.valueOf(paymentRule.getInterestDay())).divide(DIVIDE, RoundingMode.UP);
        fixedAmount = fixedAmount.add(valuePerDay.multiply(BigDecimal.valueOf(daysDiff)));

        return fixedAmount.setScale(2, RoundingMode.UP);
    }

    private long calculateDaysDiff(Invoice invoice) {
        return ChronoUnit.DAYS.between(invoice.getDueDate(), invoice.getPaymentDate());
    }
}
