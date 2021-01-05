package br.com.deliverit.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;

/**
 * Entity for the payment rules
 * @author Lucas Koch
 */
@Getter
@Setter
@Entity
public class PaymentRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "paymentRule")
    private List<Invoice> invoices;

    @Column
    @Min(value = 1, message = "DelayInDays must be bigger than one ")
    private long minDays;

    @Column
    private long maxDays;

    @Column
    private double penalty;

    @Column
    private double interestDay;

}
