package edu.uncuyo.blood_donations.view;

import edu.uncuyo.blood_donations.dao.BloodDonationDAOImpl;
import edu.uncuyo.blood_donations.entity.ABOGroup;
import edu.uncuyo.blood_donations.entity.BloodDonation;
import edu.uncuyo.blood_donations.entity.BloodDonationType;
import edu.uncuyo.blood_donations.entity.RHDGroup;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author adrian
 */
public class UpdateBloodDonationForm extends BloodDonationForm {

    private final BloodDonationDAOImpl bloodDonationDAO;
    private final BloodDonation bloodDonation;

    public UpdateBloodDonationForm(Frame parent, boolean modal, BloodDonationDAOImpl bloodDonationDAO, BloodDonation bloodDonation) {
        super(parent, modal);
        this.bloodDonationDAO = bloodDonationDAO;
        this.bloodDonation = bloodDonation;
        setTitle("Modificar Donación");
        jButton1.setText("Modificar");
        jButton1.addActionListener(this::handleButtonClick);
        fill();
        setVisible(true);
    }

    private void handleButtonClick(ActionEvent evt) {
        try {
            bloodDonation.setType(BloodDonationType.values()[bloodDonationTypeComboBox.getSelectedIndex()]);
            bloodDonation.setAmount(Double.valueOf(amountTextField.getText().trim()));
            var aboGroup = aboGroupComboBox.getSelectedIndex();
            var aboGroups = ABOGroup.values();
            bloodDonation.setAboGroup(aboGroup == aboGroups.length - 1 ? null : aboGroups[aboGroup]);
            var rhdGroup = rhdGroupComboBox.getSelectedIndex();
            var rhdGroups = RHDGroup.values();
            bloodDonation.setRhdGroup(rhdGroup == rhdGroups.length - 1 ? null : rhdGroups[rhdGroup]);
            bloodDonation.setHepatitisB(hepatitisBCheckBox.isSelected());
            bloodDonation.setHepatitisC(hepatitisCCheckBox.isSelected());
            bloodDonation.setHiv(hivCheckBox.isSelected());
            bloodDonation.setChagas(chagasCheckBox.isSelected());
            bloodDonation.setSyphilis(syphilisCheckBox.isSelected());
            bloodDonation.setHltv(hltvCheckBox.isSelected());
            bloodDonation.setBrucellosis(brucellosisCheckBox.isSelected());
            bloodDonationDAO.update(bloodDonation);
            var option = JOptionPane.showConfirmDialog(this,
                    "Está seguro que desea realizar la modificación",
                    "Confirmar Modificación",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                bloodDonationDAO.update(bloodDonation);
                var parent = (MainView) super.getParent();
                parent.updateBloodDonations();
            }
            dispose();
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "Cantidad inválida");
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage());
        }
    }

    private void fill() {
        dateTimePicker1.setDateTimeStrict(bloodDonation.getTimestamp());
        bloodDonationTypeComboBox.setSelectedIndex(bloodDonation.getType().ordinal());
        amountTextField.setText(bloodDonation.getAmount().toString());
        var aboGroup = bloodDonation.getAboGroup();
        aboGroupComboBox.setSelectedIndex(aboGroup == null ? aboGroupComboBox.getItemCount() - 1 : aboGroup.ordinal());
        var rhdGroup = bloodDonation.getRhdGroup();
        rhdGroupComboBox.setSelectedIndex(rhdGroup == null ? rhdGroupComboBox.getItemCount() - 1 : rhdGroup.ordinal());
        var hepatitisB = bloodDonation.getHepatitisB();
        hepatitisBCheckBox.setSelected(hepatitisB != null && hepatitisB);
        var hepatitisC = bloodDonation.getHepatitisC();
        hepatitisCCheckBox.setSelected(hepatitisC != null && hepatitisC);
        var hiv = bloodDonation.getHiv();
        hivCheckBox.setSelected(hiv != null && hiv);
        var chagas = bloodDonation.getChagas();
        chagasCheckBox.setSelected(chagas != null && chagas);
        var syphilis = bloodDonation.getSyphilis();
        syphilisCheckBox.setSelected(syphilis != null && syphilis);
        var hltv = bloodDonation.getHltv();
        hltvCheckBox.setSelected(hltv != null && hltv);
        var brucellosis = bloodDonation.getBrucellosis();
        brucellosisCheckBox.setSelected(brucellosis != null && brucellosis);
    }

}
