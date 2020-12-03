package br.com.deliverit.service.controller;

import br.com.deliverit.model.entity.PaymentRule;
import br.com.deliverit.model.repository.InvoiceRepository;
import br.com.deliverit.model.repository.PaymentRuleRepository;
import br.com.deliverit.service.controller.PaymentRuleController;
import br.com.deliverit.service.service.PaymentRuleService;
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

@WebMvcTest(controllers = PaymentRuleController.class)
public class PaymentRuleRegistryTest {

    @MockBean
    private InvoiceRepository invoiceRepository;

    @MockBean
    private PaymentRuleRepository paymentRuleRepository;

    @MockBean
    private PaymentRuleService paymentRuleService;


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testIfEmptyFieldsGeneratesBadRequest() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        this.mockMvc.perform(MockMvcRequestBuilders.post(
                "/api/v1/rule")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(generateInvalidPaymentRule())))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void testIfEmptyFieldsGeneratesOk() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        mapper.registerModule(new JavaTimeModule());

        ObjectWriter ow = mapper.writer();

        this.mockMvc.perform(MockMvcRequestBuilders.post(
                "/api/v1/rule")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ow.writeValueAsString(generateValidPaymentRule())))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    private PaymentRule generateValidPaymentRule() {

        PaymentRule firstRule = new PaymentRule();
        firstRule.setDelayInDays(3);
        firstRule.setId(1l);
        firstRule.setInterestDay(0.1);
        firstRule.setPenalty(2);

        return firstRule;
    }

    private PaymentRule generateInvalidPaymentRule() {

        PaymentRule firstRule = new PaymentRule();
        firstRule.setDelayInDays(0);
        firstRule.setId(1l);
        firstRule.setInterestDay(0.1);
        firstRule.setPenalty(2);

        return firstRule;
    }



}
