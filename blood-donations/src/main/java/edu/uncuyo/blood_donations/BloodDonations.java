package edu.uncuyo.blood_donations;

import edu.uncuyo.blood_donations.dao.BloodDonationDAOImpl;
import edu.uncuyo.blood_donations.dao.DAOImpl;
import edu.uncuyo.blood_donations.entity.BloodDonor;
import edu.uncuyo.blood_donations.view.MainView;
import jakarta.persistence.Persistence;

/**
 *
 * @author adrian
 */
public class BloodDonations {

    public static void main(String[] args) {
        var entityManagerFactory = Persistence.createEntityManagerFactory("edu.uncuyo_blood-donations");
        new MainView(new DAOImpl(entityManagerFactory, BloodDonor.class), new BloodDonationDAOImpl(entityManagerFactory));
    }
}
