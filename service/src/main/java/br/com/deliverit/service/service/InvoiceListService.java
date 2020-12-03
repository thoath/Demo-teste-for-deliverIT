package br.com.deliverit.service.service;

import br.com.deliverit.api.dto.InvoiceDto;

import java.util.List;

public interface InvoiceListService {
    List<InvoiceDto> execute();
}
