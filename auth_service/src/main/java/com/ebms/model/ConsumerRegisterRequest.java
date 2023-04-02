package com.ebms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerRegisterRequest {
    private Integer meterNo;
    private String email;
    private String pwd;
    private String fullName;
    private String address;
    private Integer mobile;

    public void display() {
        System.out.println(
                "MeterNo: " + this.meterNo +
                        "\nEmail: " + this.email +
                        "\nPWD: " + this.pwd
        );
    }
}
