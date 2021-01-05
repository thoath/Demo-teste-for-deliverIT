package br.com.deliverit.model.entity;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entity for invoice object
 * @author Lucas Koch
 */
@Getter
@Setter
@Entity
@EqualsAndHashCode
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Name must be provided. ")
    private String name;

    @Column
    @NotNull(message = "Amount must be provided. ")
    private BigDecimal amount;

    @Column
    @NotNull(message = "Due date must be provided. ")
    private LocalDate dueDate;

    @Column
    @NotNull(message = "PaymentDate must be provided. ")
    private LocalDate paymentDate;

    @ManyToOne
    @JoinColumn(name="payment_rule_id")
    private PaymentRule paymentRule;
}
