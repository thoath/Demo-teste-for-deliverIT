package br.com.deliverit.service.processor;

import br.com.deliverit.model.entity.Invoice;
import br.com.deliverit.model.entity.PaymentRule;
import br.com.deliverit.service.mock.MockInvoiceRepository;
import br.com.deliverit.service.mock.MockPaymentRuleRepository;
import br.com.deliverit.service.service.InvoiceListService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceListProcessorTest {

    private MockPaymentRuleRepository mockPaymentRuleRepository;
    private MockInvoiceRepository mockInvoiceRepository;

    private InvoiceListService invoiceListService;


    @BeforeEach
    public void setup() {
        this.mockPaymentRuleRepository = new MockPaymentRuleRepository();
        this.insertValidPaymentRules();
        this.mockInvoiceRepository = new MockInvoiceRepository();

    }


    @Test
    public void testIfProcessorCalculatePenaltyAndInterestByDayWithFirstRule() {

        this.mockInvoiceRepository.save(generateValidInvoice(1));

        invoiceListService  =
            new InvoiceListProcessor(this.mockInvoiceRepository, this.mockPaymentRuleRepository);

        Assertions.assertEquals(102.11, invoiceListService.execute().get(0).getFixedAmount().doubleValue());


    }

    @Test
    public void testIfProcessorCalculatePenaltyAndInterestByDayWithSecondRule() {

        this.mockInvoiceRepository.save(generateValidInvoice(4));

        invoiceListService  =
                new InvoiceListProcessor(this.mockInvoiceRepository, this.mockPaymentRuleRepository);

        Assertions.assertEquals(103.83, invoiceListService.execute().get(0).getFixedAmount().doubleValue());


    }

    @Test
    public void testIfProcessorCalculatePenaltyAndInterestByDayWithThirdRule() {

        this.mockInvoiceRepository.save(generateValidInvoice(19));

        invoiceListService  =
                new InvoiceListProcessor(this.mockInvoiceRepository, this.mockPaymentRuleRepository);

        Assertions.assertEquals(110.99, invoiceListService.execute().get(0).getFixedAmount().doubleValue());

    }

    @Test
    public void testIfProcessorCalculatePenaltyAndInterestByDayWithNoRule() {

        this.mockInvoiceRepository.save(generateValidInvoice(0));

        invoiceListService  =
                new InvoiceListProcessor(this.mockInvoiceRepository, this.mockPaymentRuleRepository);

        Assertions.assertEquals(100.00, invoiceListService.execute().get(0).getFixedAmount().doubleValue());

    }

    private Invoice generateValidInvoice(int plusDays) {

        Invoice invoice = new Invoice();
        invoice.setAmount(BigDecimal.valueOf(100.00));
        invoice.setDueDate(LocalDate.now());
        invoice.setPaymentDate(LocalDate.now().plusDays(plusDays));
        invoice.setId(1l);
        invoice.setName("Invoice");

        return invoice;
    }


    private void insertValidPaymentRules() {
        PaymentRule firstRule = new PaymentRule();
        firstRule.setDelayInDays(3);
        firstRule.setId(1l);
        firstRule.setInterestDay(0.1);
        firstRule.setPenalty(2);

        this.mockPaymentRuleRepository.save(firstRule);

        PaymentRule secondRule = new PaymentRule();
        secondRule.setDelayInDays(5);
        secondRule.setId(2l);
        secondRule.setInterestDay(0.2);
        secondRule.setPenalty(3);

        this.mockPaymentRuleRepository.save(secondRule);

        PaymentRule thirdRule = new PaymentRule();
        thirdRule.setDelayInDays(99999);
        thirdRule.setId(3l);
        thirdRule.setInterestDay(0.3);
        thirdRule.setPenalty(5);

        this.mockPaymentRuleRepository.save(thirdRule);

    }

}
