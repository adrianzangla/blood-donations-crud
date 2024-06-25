package edu.uncuyo.blood_donations.dao;

import edu.uncuyo.blood_donations.entity.BloodDonation;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author adrian
 */
public interface BloodDonationDAO extends DAO<BloodDonation, UUID> {
    public Set<BloodDonation> findByBloodDonorId(UUID bloodDonorId);
}
