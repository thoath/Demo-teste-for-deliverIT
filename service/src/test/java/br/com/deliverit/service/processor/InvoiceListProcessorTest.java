package br.com.deliverit.service.processor;

import br.com.deliverit.model.entity.Invoice;
import br.com.deliverit.model.entity.PaymentRule;
import br.com.deliverit.service.mock.MockInvoiceRepository;
import br.com.deliverit.service.mock.MockPaymentRuleRepository;
import br.com.deliverit.service.service.InvoiceListService;
import br.com.deliverit.service.service.InvoiceRegistryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceListProcessorTest {

    private MockPaymentRuleRepository mockPaymentRuleRepository;
    private MockInvoiceRepository mockInvoiceRepository;
    private InvoiceListService invoiceListService;
    private InvoiceRegistryService invoiceRegistryService;

    @BeforeEach
    public void setup() {
        this.mockPaymentRuleRepository = new MockPaymentRuleRepository();
        this.insertValidPaymentRules();
        this.mockInvoiceRepository = new MockInvoiceRepository();
        this.invoiceRegistryService =
                new InvoiceRegistry(mockInvoiceRepository, mockPaymentRuleRepository);
        this.invoiceListService  =
                new InvoiceListProcessor(this.mockInvoiceRepository, this.mockPaymentRuleRepository);

    }

    @Test
    public void testIfProcessorCalculatePenaltyAndInterestByDayWithFirstRule() {

        invoiceRegistryService.execute(generateValidInvoice(1));
        Assertions.assertEquals(102.11, invoiceListService.execute().get(0).getFixedAmount().doubleValue());
    }

    @Test
    public void testIfProcessorCalculatePenaltyAndInterestByDayWithSecondRule() {

        invoiceRegistryService.execute(generateValidInvoice(4));
        Assertions.assertEquals(103.83, invoiceListService.execute().get(0).getFixedAmount().doubleValue());
    }

    @Test
    public void testIfProcessorCalculatePenaltyAndInterestByDayWithThirdRule() {

        invoiceRegistryService.execute(generateValidInvoice(19));
        Assertions.assertEquals (
                110.99,
                invoiceListService
                        .execute()
                        .get(0)
                        .getFixedAmount()
                        .doubleValue());
    }

    @Test
    public void testIfProcessorCalculatePenaltyAndInterestByDayWithNoRule() {

        invoiceRegistryService.execute(generateValidInvoice(0));
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
        firstRule.setMaxDays(3);
        firstRule.setMinDays(1);
        firstRule.setId(1l);
        firstRule.setInterestDay(0.1);
        firstRule.setPenalty(2);

        this.mockPaymentRuleRepository.save(firstRule);

        PaymentRule secondRule = new PaymentRule();
        secondRule.setMaxDays(5);
        secondRule.setMinDays(4);
        secondRule.setId(2l);
        secondRule.setInterestDay(0.2);
        secondRule.setPenalty(3);

        this.mockPaymentRuleRepository.save(secondRule);

        PaymentRule thirdRule = new PaymentRule();
        thirdRule.setMaxDays(99999);
        thirdRule.setMinDays(6);
        thirdRule.setId(3l);
        thirdRule.setInterestDay(0.3);
        thirdRule.setPenalty(5);

        this.mockPaymentRuleRepository.save(thirdRule);

    }

}
