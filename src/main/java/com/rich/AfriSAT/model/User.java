package com.rich.AfriSAT.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="user", schema="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(nullable = false)
    private String username;

    @Column()
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private Date dateCreated;
    private String role = "CLIENT";

    @PrePersist
    protected void onCreate() {
        dateCreated = new Date();
    }
}
