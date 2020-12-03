package br.com.deliverit.service.processor;

import br.com.deliverit.model.entity.Invoice;
import br.com.deliverit.model.repository.InvoiceRepository;
import br.com.deliverit.service.service.InvoiceRegistryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Invoice registry operation
 * @author Lucas Koch
 */
@Service
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceRegistry implements InvoiceRegistryService {

    private InvoiceRepository invoiceRepository;

    @Override
    public void execute(Invoice invoice) {
        invoiceRepository.save(invoice);
    }
}
