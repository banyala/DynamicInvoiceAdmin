package com.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "institution_letters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstitutionLetter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "template_id", nullable = false)
    private Integer templateId;

    @Column(name = "bank_id", nullable = false)
    private Integer bankId;

    @Column(name = "assessment_period_year", length = 255, nullable = false)
    private String assessmentPeriodYear;

    @Column(name = "assessment_period_quarter", length = 255, nullable = false)
    private String assessmentPeriodQuarter;

    @Column(name = "certificate_number", length = 255, nullable = false)
    private String certificateNumber;

    @Column(name = "data", columnDefinition = "text")
    private String data;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

