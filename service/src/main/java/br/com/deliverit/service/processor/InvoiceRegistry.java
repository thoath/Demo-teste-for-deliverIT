package br.com.deliverit.service.processor;

import br.com.deliverit.model.entity.Invoice;
import br.com.deliverit.model.repository.InvoiceRepository;
import br.com.deliverit.service.service.InvoiceRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Invoice registry operation
 * @author Lucas Koch
 */
@Service
public class InvoiceRegistry implements InvoiceRegistryService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public void execute(Invoice invoice) {
        invoiceRepository.save(invoice);
    }
}
