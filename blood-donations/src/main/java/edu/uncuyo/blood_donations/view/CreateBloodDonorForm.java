package edu.uncuyo.blood_donations.view;

import edu.uncuyo.blood_donations.dao.DAOImpl;
import edu.uncuyo.blood_donations.entity.BloodDonor;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.UUID;
import javax.swing.JOptionPane;
import org.hibernate.PropertyValueException;

/**
 *
 * @author adrian
 */
public class CreateBloodDonorForm extends BloodDonorForm {

    protected DAOImpl<BloodDonor, UUID> bloodDonorDAO;

    public CreateBloodDonorForm(Frame parent, boolean modal, DAOImpl<BloodDonor, UUID> bloodDonorDAO) {
        super(parent, modal);
        this.bloodDonorDAO = bloodDonorDAO;
        setTitle("Alta Donante");
        jButton1.setText("Alta");
        jButton1.addActionListener(this::handleButtonClick);
        setVisible(true);
    }

    private void handleButtonClick(ActionEvent evt) {
        try {
            var bloodDonor = new BloodDonor();
            bloodDonor.setDni(Integer.valueOf(dniTextField.getText().trim()));
            bloodDonor.setName(nameTextField.getText().trim());
            bloodDonor.setSurname(surnameTextField.getText().trim());
            var email = emailTextField.getText();
            bloodDonor.setEmail(email.isBlank() ? null : email.trim());
            bloodDonor.setDateOfBirth(datePicker1.getDate());
            bloodDonorDAO.create(bloodDonor);
            var parent = (MainView) super.getParent();
            parent.updateBloodDonors();
            dispose();
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "DNI inválido");
        } catch (IllegalArgumentException exception) {
            if (exception.getMessage().contains("16")) {
                JOptionPane.showMessageDialog(this, "Sólo se aceptan donantes mayores de 16 años");
            } else {
                JOptionPane.showMessageDialog(this, "Nombre y Apellido requeridos");
            }
        } catch (PropertyValueException exception) {
            JOptionPane.showMessageDialog(this, "Fecha de nacimiento requerida");
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage());
        }
    }

}
