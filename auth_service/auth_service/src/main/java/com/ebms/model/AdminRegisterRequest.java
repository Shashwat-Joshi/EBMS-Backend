package com.ebms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminRegisterRequest {
    private String email;
    private String password;
    private String fullName;
    private Integer mobileNo;
}
