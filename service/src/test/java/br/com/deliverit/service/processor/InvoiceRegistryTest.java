package br.com.deliverit.service.processor;

import br.com.deliverit.model.entity.Invoice;
import br.com.deliverit.model.entity.PaymentRule;
import br.com.deliverit.service.mock.MockInvoiceRepository;
import br.com.deliverit.service.mock.MockPaymentRuleRepository;
import br.com.deliverit.service.service.InvoiceRegistryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceRegistryTest {

    private MockPaymentRuleRepository mockPaymentRuleRepository;
    private MockInvoiceRepository mockInvoiceRepository;

    private InvoiceRegistryService invoiceRegistryService;

    @BeforeEach
    public void setup() {

        this.mockPaymentRuleRepository = new MockPaymentRuleRepository();
        this.insertValidPaymentRules();
        this.mockInvoiceRepository = new MockInvoiceRepository();
        this.invoiceRegistryService =
                new InvoiceRegistry(mockInvoiceRepository, mockPaymentRuleRepository);
    }

    @Test
    public void testIfInvoiceRegistryIsPersistedWithSuccessAndValidRule() {
        Invoice localInvoice = generateValidInvoice(1);
        Invoice invoice = invoiceRegistryService.execute(localInvoice);

        Assertions.assertEquals(localInvoice, invoice);
        Assertions.assertNotNull(invoice.getPaymentRule());
        Assertions.assertEquals(1L, invoice.getPaymentRule().getId());
    }

    @Test
    public void testIfInvoiceRegistryIsPersistedWithSuccessAndNoRule() {

        Invoice invoice = invoiceRegistryService.execute(generateValidInvoice(0));
        Assertions.assertNull(invoice.getPaymentRule());
    }

    private Invoice generateValidInvoice(int days) {

        Invoice invoice = new Invoice();
        invoice.setAmount(BigDecimal.valueOf(100.00));
        invoice.setDueDate(LocalDate.now());
        invoice.setPaymentDate(LocalDate.now().plusDays(days));
        invoice.setId(1L);
        invoice.setName("Invoice");

        return invoice;
    }

    private void insertValidPaymentRules() {

        PaymentRule firstRule = new PaymentRule();
        firstRule.setMaxDays(3);
        firstRule.setMinDays(1);
        firstRule.setId(1L);
        firstRule.setInterestDay(0.1);
        firstRule.setPenalty(2);

        this.mockPaymentRuleRepository.save(firstRule);

        PaymentRule secondRule = new PaymentRule();
        secondRule.setMaxDays(5);
        secondRule.setMinDays(4);
        secondRule.setId(2L);
        secondRule.setInterestDay(0.2);
        secondRule.setPenalty(3);

        this.mockPaymentRuleRepository.save(secondRule);

        PaymentRule thirdRule = new PaymentRule();
        thirdRule.setMaxDays(99999);
        thirdRule.setMinDays(6);
        thirdRule.setId(3L);
        thirdRule.setInterestDay(0.3);
        thirdRule.setPenalty(5);

        this.mockPaymentRuleRepository.save(thirdRule);
    }
}
