package br.com.deliverit.service.mock;

import br.com.deliverit.model.entity.Invoice;
import br.com.deliverit.model.repository.InvoiceRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockInvoiceRepository implements InvoiceRepository {

    List<Invoice> invoiceList = new ArrayList<>();

    @Override
    public List<Invoice> findAll() {
        return invoiceList;
    }

    @Override
    public List<Invoice> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Invoice> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Invoice> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Invoice invoice) {

    }

    @Override
    public void deleteAll(Iterable<? extends Invoice> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Invoice> S save(S s) {
        invoiceList.add(s);
        return s;
    }

    @Override
    public <S extends Invoice> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Invoice> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Invoice> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Invoice> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Invoice getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Invoice> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Invoice> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Invoice> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Invoice> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Invoice> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Invoice> boolean exists(Example<S> example) {
        return false;
    }
}
