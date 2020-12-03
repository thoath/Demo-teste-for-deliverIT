package br.com.deliverit.model.repository;

import br.com.deliverit.model.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for database operations in Invoice entity
 * @author Lucas Koch
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {}
