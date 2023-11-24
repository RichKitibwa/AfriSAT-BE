package com.rich.AfriSAT.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="activation_code", schema="users")
public class ActivationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID code_id;
    private String code;
    private Integer duration;
    private Double cost;
    @Enumerated(EnumType.STRING)
    private ActivationCodeStatus status;
    private String assignedDecoderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
