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
@Table(name="decoder", schema="users")
public class Decoder extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID decoderId;

    private String decoderNumber;

    @Enumerated(EnumType.STRING)
    private DecoderStatus decoderStatus = DecoderStatus.NOT_ACTIVE;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
