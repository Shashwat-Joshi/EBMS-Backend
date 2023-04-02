package com.ebms.controller;

import com.ebms.model.*;
import com.ebms.repository.IAdminRepository;
import com.ebms.repository.IConsumerRepository;
import com.ebms.schema.Admin;
import com.ebms.schema.Consumer;
import com.ebms.schema.EBMSRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
public class AuthController {
    private final IConsumerRepository consumerRepository;
    private final IAdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/consumer/register")
    public ResponseEntity<Object> registerConsumer(@RequestBody ConsumerRegisterRequest request) {
        if(consumerRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.status(409).body("Email already in use");
        }
        final var consumer = Consumer.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .address(request.getAddress())
                .mobileNumber(request.getMobile())
                .role(EBMSRole.CONSUMER)
                .meterNumber(request.getMeterNo())
                .build();
        consumerRepository.save(consumer);
        var response = ConsumerAuthResponse.builder()
                .expireAt(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000)))
                .build();
        response.setConsumer(consumer);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/consumer/login")
    public ResponseEntity<Object> consumerLogin(@RequestBody LoginRequest request) {
        Optional<Consumer> consumer = consumerRepository.findByEmail(request.getEmail());
        if(consumer.isEmpty()) {
            return ResponseEntity.status(404).body("Email not found");
        }
        if(passwordEncoder.matches(request.getPassword(), consumer.get().getPassword())) {
            var response = ConsumerAuthResponse.builder()
                    .expireAt(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000)))
                    .build();
            response.setConsumer(consumer.get());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body("Unauthorized: Wrong Credentials");
    }

    @PostMapping("/admin/register")
    public ResponseEntity<Object> registerAdmin(@RequestBody AdminRegisterRequest request) {
        if(adminRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.status(409).body("Email already in use");
        }
        final var admin = Admin.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .mobileNo(request.getMobileNo())
                .role(EBMSRole.ADMIN)
                .build();
        adminRepository.save(admin);
        var response = AdminAuthResponse.builder()
                .expireAt(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000)))
                .build();
        response.setAdmin(admin);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<Object> adminLogin(@RequestBody LoginRequest request) {
        Optional<Admin> admin = adminRepository.findByEmail(request.getEmail());
        if(admin.isEmpty()) {
            return ResponseEntity.status(404).body("Email not found");
        }
        if(passwordEncoder.matches(request.getPassword(), admin.get().getPassword())) {
            var response = AdminAuthResponse.builder()
                    .expireAt(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000)))
                    .build();
            response.setAdmin(admin.get());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body("Unauthorized: Wrong Credentials");
    }
}
