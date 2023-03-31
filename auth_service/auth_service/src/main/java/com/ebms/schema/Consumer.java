package com.ebms.schema;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "consumer")
public class Consumer {
    @Id
    @GeneratedValue
    @Column(name = "consumer_no")
    private Integer consumerNumber;
    @Column(name = "meter_no", unique = true)
    private Integer meterNumber;
    private String email;
    private String password;
    @Column(name = "consumer_name")
    private String fullName;
    private String address;
    @Column(name = "mob_no")
    private Integer mobileNumber;
    @Enumerated(EnumType.STRING)
    private EBMSRole role;
}
