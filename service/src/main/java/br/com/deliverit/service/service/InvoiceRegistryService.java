package br.com.deliverit.service.service;

import br.com.deliverit.model.entity.Invoice;

public interface InvoiceRegistryService {
    void execute(Invoice invoice);
}
