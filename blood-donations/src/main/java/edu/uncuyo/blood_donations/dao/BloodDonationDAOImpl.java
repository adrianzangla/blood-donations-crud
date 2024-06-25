package edu.uncuyo.blood_donations.dao;

import edu.uncuyo.blood_donations.entity.BloodDonation;
import jakarta.persistence.EntityManagerFactory;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author adrian
 */
public class BloodDonationDAOImpl extends DAOImpl<BloodDonation, UUID> implements BloodDonationDAO {

    public BloodDonationDAOImpl(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, BloodDonation.class);
    }

    @Override
    public Set<BloodDonation> findByBloodDonorId(UUID bloodDonorId) {
        try (var entityManager = entityManagerFactory.createEntityManager()) {
            return new HashSet<>(entityManager.createQuery(
                    "SELECT bd FROM BloodDonation bd WHERE bd.bloodDonor.id = :bloodDonorId", BloodDonation.class)
                    .setParameter("bloodDonorId", bloodDonorId)
                    .getResultList());
        }
    }

}
