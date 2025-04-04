package com.example.entity;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "invoices")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;
    
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "template_id", nullable = false)
    private Long templateId;

    @Column(name = "bank_id", nullable = false)
    private Long bankId;

    @Column(name = "assessment_period_year", nullable = false)
    private Integer assessmentPeriodYear;

    @Column(name = "assessment_period_quarter", nullable = false, length = 10)
    private String assessmentPeriodQuarter;

    @Column(name = "invoice_number", nullable = false)
    private String invoiceNumber;

    @Column(name = "certificate_number", nullable = false)
    private String certificateNumber;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "payment_date", nullable = false)
    private String paymentDate;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "institution_data", columnDefinition = "json", nullable = false)
    private List<Map<String, String>> institutionData;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "assessment_payment_computation", columnDefinition = "json", nullable = false)
    private Map<String, List<Map<String, String>>> assessmentPaymentComputation;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "financial_data", columnDefinition = "text", nullable = false)
    private List<Map<String, String>> financialData;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "risk_data", columnDefinition = "text", nullable = false)
    private List<Map<String, String>> riskData;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "other_calculated_amounts", columnDefinition = "text", nullable = false)
    private List<Map<String, String>> otherCalculatedAmounts;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "other_non_calculated_amounts", columnDefinition = "text", nullable = false)
    private List<Map<String, String>> otherNonCalculatedAmounts;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "payment_information", columnDefinition = "text", nullable = false)
    private Map<String, String> paymentInformation;

    @Column(name = "amount_due", nullable = false)
    private String amountDue;
    
 // Accept a double and format it before storing
    public void setAmountDue(Double amount) {
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        this.amountDue = formatter.format(amount);
    }

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "UTC")
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime updatedAt;

}
