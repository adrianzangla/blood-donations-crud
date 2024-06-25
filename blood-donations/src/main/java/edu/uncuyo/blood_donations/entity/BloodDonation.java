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
public class BloodDonation implements edu.uncuyo.blood_donations.entity.Entity<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    @Column(nullable = false)
    private BloodDonationType type;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Boolean selfExclusion = false;

    private ABOGroup aboGroup;

    private RHDGroup rhdGroup;

    private Boolean hepatitisB;

    private Boolean hepatitisC;

    private Boolean hiv;

    private Boolean chagas;

    private Boolean syphilis;

    private Boolean hltv;

    private Boolean brucellosis;

    @ManyToOne
    @JoinColumn(name = "blooddonorid", nullable = false)
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

    public BloodDonationType getType() {
        return type;
    }

    public void setType(BloodDonationType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount < 0");
        }
        this.amount = amount;
    }

    public Boolean getSelfExclusion() {
        return selfExclusion;
    }

    public void setSelfExclusion(Boolean selfExclusion) {
        this.selfExclusion = selfExclusion;
    }

    public ABOGroup getAboGroup() {
        return aboGroup;
    }

    public void setAboGroup(ABOGroup aboGroup) {
        this.aboGroup = aboGroup;
    }

    public RHDGroup getRhdGroup() {
        return rhdGroup;
    }

    public void setRhdGroup(RHDGroup rhdGroup) {
        this.rhdGroup = rhdGroup;
    }

    public Boolean getHepatitisB() {
        return hepatitisB;
    }

    public void setHepatitisB(Boolean hepatitisB) {
        this.hepatitisB = hepatitisB;
    }

    public Boolean getHepatitisC() {
        return hepatitisC;
    }

    public void setHepatitisC(Boolean hepatitisC) {
        this.hepatitisC = hepatitisC;
    }

    public Boolean getHiv() {
        return hiv;
    }

    public void setHiv(Boolean hiv) {
        this.hiv = hiv;
    }

    public Boolean getChagas() {
        return chagas;
    }

    public void setChagas(Boolean chagas) {
        this.chagas = chagas;
    }

    public Boolean getSyphilis() {
        return syphilis;
    }

    public void setSyphilis(Boolean syphilis) {
        this.syphilis = syphilis;
    }

    public Boolean getHltv() {
        return hltv;
    }

    public void setHltv(Boolean hltv) {
        this.hltv = hltv;
    }

    public Boolean getBrucellosis() {
        return brucellosis;
    }

    public void setBrucellosis(Boolean brucellosis) {
        this.brucellosis = brucellosis;
    }

    public BloodDonor getBloodDonor() {
        return bloodDonor;
    }

    public void setBloodDonor(BloodDonor bloodDonor) {
        this.bloodDonor = bloodDonor;
    }

    public Boolean getApt() {
        if (hepatitisB == null
                || hepatitisC == null
                || hiv == null
                || chagas == null
                || syphilis == null
                || hltv == null
                || brucellosis == null) {
            return null;
        } else {
            return !hepatitisB
                    && !hepatitisC
                    && !hiv
                    && !chagas
                    && !syphilis
                    && !hltv
                    && !brucellosis;
        }
    }

    @Override
    public String toString() {
        return "BloodDonation{" + "id=" + id + ", timestamp=" + timestamp + ", type=" + type + ", amount=" + amount + ", selfExclusion=" + selfExclusion + ", aboGroup=" + aboGroup + ", rhdGroup=" + rhdGroup + ", hepatitisB=" + hepatitisB + ", hepatitisC=" + hepatitisC + ", hiv=" + hiv + ", chagas=" + chagas + ", syphilis=" + syphilis + ", hltv=" + hltv + ", brucellosis=" + brucellosis + ", bloodDonor=" + bloodDonor + '}';
    }
    
}
