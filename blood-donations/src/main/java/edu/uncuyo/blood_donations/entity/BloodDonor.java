package edu.uncuyo.blood_donations.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.hibernate.annotations.SoftDelete;

/**
 *
 * @author adrian
 */
@Entity
@SoftDelete
public class BloodDonor implements edu.uncuyo.blood_donations.entity.Entity<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Integer dni;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    private String email;

    @OneToMany(mappedBy = "bloodDonor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BloodDonation> bloodDonations = new HashSet<>();

    @OneToMany(mappedBy = "bloodDonor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ClinicalScreening> clinicalScreenings = new HashSet<>();

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        if (dni < 0) {
            throw new IllegalArgumentException("dni < 0");
        }
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("name is blank");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname.isBlank()) {
            throw new IllegalArgumentException("surname is blank");
        }
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (getAge(dateOfBirth) < 16) {
            throw new IllegalArgumentException("less than 16 years old");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<BloodDonation> getBloodDonations() {
        return bloodDonations;
    }

    public void setBloodDonations(Set<BloodDonation> bloodDonations) {
        this.bloodDonations = bloodDonations;
    }

    public Set<ClinicalScreening> getClinicalScreenings() {
        return clinicalScreenings;
    }

    public void setClinicalScreenings(Set<ClinicalScreening> clinicalScreenings) {
        this.clinicalScreenings = clinicalScreenings;
    }

    private int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
    
    private int getAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
    
    @Override
    public String toString() {
        return "BloodDonor{" + "id=" + id + ", dni=" + dni + ", name=" + name + ", surname=" + surname + ", dateOfBirth=" + dateOfBirth + ", email=" + email + '}';
    }

}
