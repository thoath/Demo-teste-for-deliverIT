package br.com.deliverit.model.repository;

import br.com.deliverit.model.entity.PaymentRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for database operations in payment rules entity
 * @author Lucas Koch
 */
@Repository
public interface PaymentRuleRepository extends JpaRepository<PaymentRule, Long> {}
