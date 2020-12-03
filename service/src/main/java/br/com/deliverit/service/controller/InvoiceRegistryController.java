package br.com.deliverit.service.controller;

import br.com.deliverit.model.entity.Invoice;
import br.com.deliverit.service.service.InvoiceRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public final class InvoiceRegistryController {

    @Autowired
    private InvoiceRegistryService service;

    @PostMapping("/registry")
    public ResponseEntity<Invoice> registryInvoice(@RequestBody @Valid Invoice invoice) {
        service.execute(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
