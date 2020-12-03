package br.com.deliverit.api.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for invoice entity
 * @author Lucas Koch
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class InvoiceDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private BigDecimal amount;
    private BigDecimal fixedAmount;
    private LocalDate paymentDate;
    private long delayInDays;
}
