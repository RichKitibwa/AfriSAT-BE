package com.rich.AfriSAT;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@JsonIgnoreProperties(
        value = {"created_date, updated_date"},
        allowGetters = true)
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    @Column(name = "created_by", updatable = false)
    @CreatedBy
    protected String createdBy;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    protected LocalDateTime createdDate;

    @Column(name = "updated_by")
    @LastModifiedBy
    protected String updatedBy;

    @Column(name = "updated_date", nullable = false)
    @LastModifiedDate
    protected LocalDateTime updatedDate;
}
