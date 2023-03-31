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
@Entity(name = "admin")
public class Admin {
    @Id
    @GeneratedValue
    @Column(name = "admin_id")
    private Integer adminId;
    private String password;
    @Column(name = "admin_name")
    private String fullName;
    private String email;
    @Column(name = "mob_no")
    private Integer mobileNo;

    @Enumerated(EnumType.STRING)
    private EBMSRole role;
}
