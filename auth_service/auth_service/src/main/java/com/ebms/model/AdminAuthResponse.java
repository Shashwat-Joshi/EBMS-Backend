package com.ebms.model;

import com.ebms.schema.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminAuthResponse {
    Date expireAt;
    AdminResponse admin;

    public void setAdmin(Admin admin) {
        this.admin = AdminResponse.builder()
                .adminId(admin.getAdminId())
                .email(admin.getEmail())
                .fullName(admin.getFullName())
                .mobileNo(admin.getMobileNo())
                .role(admin.getRole().toString())
                .build();
    }
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class AdminResponse {
    private Integer adminId;
    private String email;
    private String fullName;
    private Integer mobileNo;
    private String role;
}