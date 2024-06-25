package edu.uncuyo.blood_donations.view;

import edu.uncuyo.blood_donations.dao.DAOImpl;
import edu.uncuyo.blood_donations.entity.BloodDonor;
import java.util.UUID;
import javax.swing.JOptionPane;

/**
 *
 * @author adrian
 */
public class UpdateBloodDonorForm extends BloodDonorForm {

    private final DAOImpl<BloodDonor, UUID> bloodDonorDAO;
    private final BloodDonor bloodDonor;

    public UpdateBloodDonorForm(java.awt.Frame parent, boolean modal, DAOImpl<BloodDonor, UUID> bloodDonorDAO, BloodDonor bloodDonor) {
        super(parent, modal);
        this.bloodDonorDAO = bloodDonorDAO;
        this.bloodDonor = bloodDonor;
        setTitle("Modificar Donante");
        jButton1.setText("Modificar");
        jButton1.addActionListener(this::handleButtonClick);
        fill();
        setVisible(true);
    }

    private void fill() {
        dniTextField.setText(bloodDonor.getDni().toString());
        nameTextField.setText(bloodDonor.getName());
        surnameTextField.setText(bloodDonor.getSurname());
        emailTextField.setText(bloodDonor.getEmail());
        datePicker1.setDate(bloodDonor.getDateOfBirth());
    }

    private void handleButtonClick(java.awt.event.ActionEvent evt) {
        try {
            bloodDonor.setDni(Integer.valueOf(dniTextField.getText().trim()));
            bloodDonor.setName(nameTextField.getText().trim());
            bloodDonor.setSurname(surnameTextField.getText().trim());
            var email = emailTextField.getText();
            bloodDonor.setEmail(email.isBlank() ? null : email.trim());
            bloodDonor.setDateOfBirth(datePicker1.getDate());
            var option = JOptionPane.showConfirmDialog(this,
                    "Está seguro que desea realizar la modificación",
                    "Confirmar Modificación",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                bloodDonorDAO.update(bloodDonor);
                var parent = (MainView) super.getParent();
                parent.updateBloodDonors();
            }
            dispose();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage());
        }
    }
}
