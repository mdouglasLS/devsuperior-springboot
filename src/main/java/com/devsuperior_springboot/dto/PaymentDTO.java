package com.devsuperior_springboot.dto;

import com.devsuperior_springboot.entities.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long id;
    private Instant moment;

    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.moment = payment.getMoment();
    }
}
