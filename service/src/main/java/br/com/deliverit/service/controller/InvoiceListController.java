package br.com.deliverit.service.controller;

import br.com.deliverit.api.dto.InvoiceDto;
import br.com.deliverit.service.service.InvoiceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public final class InvoiceListController {

    @Autowired
    private InvoiceListService service;

    @GetMapping("/invoice")
    public ResponseEntity<List<InvoiceDto>> listInvoice() {
        return ResponseEntity.ok(service.execute());
    }

}
