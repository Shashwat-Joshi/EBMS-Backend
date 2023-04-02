package com.ebms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
