package br.com.deliverit.service.controller;

import br.com.deliverit.model.entity.Invoice;
import br.com.deliverit.model.repository.InvoiceRepository;
import br.com.deliverit.model.repository.PaymentRuleRepository;
import br.com.deliverit.service.service.InvoiceRegistryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;

@WebMvcTest(controllers = InvoiceRegistryController.class)
public class InvoiceRegistryTest {

    @MockBean
    private InvoiceRepository invoiceRepository;

    @MockBean
    private PaymentRuleRepository paymentRuleRepository;

    @MockBean
    private InvoiceRegistryService invoiceRegistryService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testIfEmptyFieldsGeneratesBadRequest() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

       this.mockMvc.perform(MockMvcRequestBuilders.post(
               "/api/v1/registry")
               .contentType(MediaType.APPLICATION_JSON)
               .content(ow.writeValueAsString(generateInvalidInvoice())))
               .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void testIfValidFieldsGeneratesOk() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        mapper.registerModule(new JavaTimeModule());

        ObjectWriter ow = mapper.writer();

        this.mockMvc.perform(MockMvcRequestBuilders.post(
                "/api/v1/registry")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(generateValidInvoice())))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    private Invoice generateInvalidInvoice() {

        Invoice invoice = new Invoice();
        invoice.setAmount(BigDecimal.valueOf(100.00));
        invoice.setId(1l);

        return invoice;
    }

    private Invoice generateValidInvoice() {

        Invoice invoice = new Invoice();
        invoice.setAmount(BigDecimal.valueOf(100.00));
        invoice.setDueDate(LocalDate.now());
        invoice.setPaymentDate(LocalDate.now().plusDays(1));
        invoice.setId(1l);
        invoice.setName("Invoice");

        return invoice;
    }
}
