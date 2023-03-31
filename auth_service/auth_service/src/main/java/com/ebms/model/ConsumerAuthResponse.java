package com.ebms.model;

import com.ebms.schema.Consumer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerAuthResponse {
    Date expireAt;
    ConsumerResponse consumer;

    public void setConsumer(Consumer consumer) {
        this.consumer = ConsumerResponse.builder()
                .email(consumer.getEmail())
                .fullName(consumer.getFullName())
                .consumerNumber(consumer.getConsumerNumber())
                .meterNo(consumer.getMeterNumber())
                .mobile(consumer.getMobileNumber())
                .address(consumer.getAddress())
                .role(consumer.getRole().toString())
                .build();
    }
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class ConsumerResponse {
    private Integer consumerNumber;
    private Integer meterNo;
    private String email;
    private String fullName;
    private String address;
    private Integer mobile;
    private String role;
}

