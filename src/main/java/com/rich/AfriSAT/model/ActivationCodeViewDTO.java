package com.rich.AfriSAT.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivationCodeViewDTO {

    private String code;
    private String duration;
    private Double cost;
    private String assignedDecoderId;
    private ActivationCodeStatus status;
}
