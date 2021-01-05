package br.com.deliverit.model.repository;

import br.com.deliverit.model.entity.PaymentRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for database operations in payment rules entity
 * @author Lucas Koch
 */
@Repository
public interface PaymentRuleRepository extends JpaRepository<PaymentRule, Long> {
    Optional<PaymentRule> findByMinDaysLessThanEqualAndMaxDaysGreaterThanEqual(long minValue, long maxValue);
}
