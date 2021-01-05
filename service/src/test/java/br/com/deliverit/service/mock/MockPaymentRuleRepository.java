package br.com.deliverit.service.mock;

import br.com.deliverit.model.entity.PaymentRule;
import br.com.deliverit.model.repository.PaymentRuleRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockPaymentRuleRepository implements PaymentRuleRepository {

    List<PaymentRule> listPaymentRule = new ArrayList<>();

    @Override
    public List<PaymentRule> findAll() {
        return listPaymentRule;
    }

    @Override
    public List<PaymentRule> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<PaymentRule> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<PaymentRule> findAllById(Iterable<Long> iterable) {
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
    public void delete(PaymentRule paymentRule) {

    }

    @Override
    public void deleteAll(Iterable<? extends PaymentRule> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends PaymentRule> S save(S s) {
        listPaymentRule.add(s);
        return s;
    }

    @Override
    public <S extends PaymentRule> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<PaymentRule> findById(Long aLong) {
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
    public <S extends PaymentRule> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<PaymentRule> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public PaymentRule getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends PaymentRule> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends PaymentRule> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends PaymentRule> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends PaymentRule> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends PaymentRule> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends PaymentRule> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public Optional<PaymentRule> findByMinDaysLessThanEqualAndMaxDaysGreaterThanEqual(long minDays, long maxDays) {

        Optional<PaymentRule> paymentRule = listPaymentRule
                .stream()
                .filter(rule -> rule.getMinDays() <= minDays && rule.getMaxDays() >= maxDays)
                .findAny();

        return paymentRule;
    }
}
