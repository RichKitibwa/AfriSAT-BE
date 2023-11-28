package com.rich.AfriSAT.model;

import com.rich.AfriSAT.Auditable;
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
public class ActivationCode extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID code_id;
    private String code;
    private String duration;
    private Double cost;
    @Enumerated(EnumType.STRING)
    private ActivationCodeStatus status = ActivationCodeStatus.NOT_ACTIVE;
    private String assignedDecoderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
