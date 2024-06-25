package edu.uncuyo.blood_donations.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.SoftDelete;

/**
 *
 * @author adrian
 */
@Entity
@SoftDelete
public class ClinicalScreening implements edu.uncuyo.blood_donations.entity.Entity<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean apt;

    @Column(nullable = false)
    private Double temperature;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Double systolicPressure;

    @Column(nullable = false)
    private Double diastolicPressure;

    private String observations;
    
    @ManyToOne
    @JoinColumn(name = "blooddonorid", nullable = false, updatable = false)
    private BloodDonor bloodDonor;

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getApt() {
        return apt;
    }

    public void setApt(Boolean apt) {
        this.apt = apt;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(Double systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public Double getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(Double diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public BloodDonor getBloodDonor() {
        return bloodDonor;
    }

    public void setBloodDonor(BloodDonor bloodDonor) {
        this.bloodDonor = bloodDonor;
    }
    
}
