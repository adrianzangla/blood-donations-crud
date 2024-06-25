package edu.uncuyo.blood_donations.view;

import edu.uncuyo.blood_donations.dao.BloodDonationDAOImpl;
import edu.uncuyo.blood_donations.entity.ABOGroup;
import edu.uncuyo.blood_donations.entity.BloodDonation;
import edu.uncuyo.blood_donations.entity.BloodDonationType;
import edu.uncuyo.blood_donations.entity.BloodDonor;
import edu.uncuyo.blood_donations.entity.RHDGroup;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author adrian
 */
public class CreateBloodDonationForm extends BloodDonationForm {

    private final BloodDonationDAOImpl bloodDonationDAO;
    private final BloodDonor bloodDonor;

    public CreateBloodDonationForm(Frame parent, boolean modal, BloodDonationDAOImpl bloodDonationDAO, BloodDonor bloodDonor) {
        super(parent, modal);
        this.bloodDonationDAO = bloodDonationDAO;
        this.bloodDonor = bloodDonor;
        setTitle("Alta Donación");
        jButton1.setText("Alta");
        jButton1.addActionListener(this::handleButtonClick);
        aboGroupComboBox.setSelectedIndex(aboGroupComboBox.getItemCount() - 1);
        rhdGroupComboBox.setSelectedIndex(rhdGroupComboBox.getItemCount() - 1);
        setVisible(true);
    }

    private void handleButtonClick(ActionEvent evt) {
        try {
            var bloodDonation = new BloodDonation();
            var bloodDonationType = bloodDonationTypeComboBox.getSelectedIndex();
            var bloodDonationTypes = BloodDonationType.values();
            bloodDonation.setType(bloodDonationType == bloodDonationTypes.length ? null : bloodDonationTypes[bloodDonationType]);
            bloodDonation.setAmount(Double.valueOf(amountTextField.getText().trim()));
            var aboGroup = aboGroupComboBox.getSelectedIndex();
            var aboGroups = ABOGroup.values();
            bloodDonation.setAboGroup(aboGroup == aboGroups.length ? null : aboGroups[aboGroup]);
            var rhdGroup = rhdGroupComboBox.getSelectedIndex();
            var rhdGroups = RHDGroup.values();
            bloodDonation.setRhdGroup(rhdGroup == rhdGroups.length ? null : rhdGroups[rhdGroup]);
            bloodDonation.setHepatitisB(hepatitisBCheckBox.isSelected());
            bloodDonation.setHepatitisC(hepatitisCCheckBox.isSelected());
            bloodDonation.setHiv(hivCheckBox.isSelected());
            bloodDonation.setChagas(chagasCheckBox.isSelected());
            bloodDonation.setSyphilis(syphilisCheckBox.isSelected());
            bloodDonation.setHltv(hltvCheckBox.isSelected());
            bloodDonation.setBrucellosis(brucellosisCheckBox.isSelected());
            bloodDonation.setBloodDonor(bloodDonor);
            System.out.println("Creating Blood Donation");
            System.out.println(bloodDonation);
            bloodDonationDAO.create(bloodDonation);
            var parent = (MainView) super.getParent();
            parent.updateBloodDonations();
            dispose();
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "Cantidad inválida");
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage());
        }
    }

}
