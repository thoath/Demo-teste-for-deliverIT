package br.com.deliverit.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

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

    @Column
    @Min(value = 1, message = "DelayInDays must be bigger than one ")
    private int delayInDays;

    @Column
    private double penalty;

    @Column
    private double interestDay;

}
