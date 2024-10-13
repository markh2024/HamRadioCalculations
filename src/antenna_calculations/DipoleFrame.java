package antenna_calculations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DipoleFrame extends JInternalFrame {

    private final double speedOfLight;

    private JRadioButton rbtnCalculateFrequency;
    private JRadioButton rbtnCalculateLength;
    private JTextField txtFrequency;
    private JTextField txtWavelength;
    private JTextField txtLength;
    private JButton btnCalculate;

    public DipoleFrame(double speedOfLight) {
        this.speedOfLight = speedOfLight;

        setTitle("Dipole HalfWave Calculations");
        setSize(650, 220); // Set the frame size to 700x300
        setClosable(true);
        setIconifiable(true);
        setMaximizable(false);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        // Create radio buttons
        rbtnCalculateFrequency = new JRadioButton("Calc freq & wavelength via total length of dipole");
        rbtnCalculateLength = new JRadioButton("Calc total length of dipole via freq or wavelength");

        // Grouping radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(rbtnCalculateFrequency);
        group.add(rbtnCalculateLength);

        // Create text fields
        txtFrequency = new JTextField(10);
        txtWavelength = new JTextField(10);
        txtLength = new JTextField(10);

        // Create calculate button
        btnCalculate = new JButton("Calculate");
        btnCalculate.addActionListener(this::performCalculation);

        // Set layout and add components
        setLayout(new GridLayout(5, 2, 10, 10));
        add(rbtnCalculateFrequency);
        add(rbtnCalculateLength);
        add(new JLabel("Total Length of Dipole (meters):"));
        add(txtLength);
        add(new JLabel("Frequency (MHz):"));
        add(txtFrequency);
        add(new JLabel("Wavelength (meters):"));
        add(txtWavelength);
        add(btnCalculate);

        // Add action listeners to clear fields on selection
        rbtnCalculateFrequency.addActionListener(e -> clearFields());
        rbtnCalculateLength.addActionListener(e -> clearFields());
    }

    private void clearFields() {
        // Clear the text fields
        txtFrequency.setText("");
        txtWavelength.setText("");
        txtLength.setText("");
    }

    private void performCalculation(ActionEvent event) {
        if (rbtnCalculateFrequency.isSelected()) {
            // Calculate frequency and wavelength using the total length of the dipole
            String lengthStr = txtLength.getText();
            if (!lengthStr.isEmpty()) {
                try {
                    double length = Double.parseDouble(lengthStr);
                    double frequency = speedOfLight / (length * 2); // Total length of dipole is twice the length
                    double wavelength = speedOfLight / frequency; // Wavelength in meters
                    txtFrequency.setText(String.format("%.2f", frequency / 1_000_000)); // Convert to MHz
                    txtWavelength.setText(String.format("%.2f", wavelength));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid length.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Length of dipole cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (rbtnCalculateLength.isSelected()) {
            // Calculate total length of dipole using frequency or wavelength
            String frequencyStr = txtFrequency.getText();
            String wavelengthStr = txtWavelength.getText();

            // If frequency is entered
            if (!frequencyStr.isEmpty()) {
                try {
                    double frequency = Double.parseDouble(frequencyStr) * 1_000_000; // Convert MHz to Hz
                    double length = speedOfLight / (frequency * 2); // Length of dipole
                    double wavelength = speedOfLight / frequency; // Wavelength in meters
                    txtLength.setText(String.format("%.2f", length));
                    txtWavelength.setText(String.format("%.2f", wavelength)); // Return wavelength
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid frequency.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            } 
            // If wavelength is entered
            else if (!wavelengthStr.isEmpty()) {
                try {
                    double wavelength = Double.parseDouble(wavelengthStr);
                    double frequency = speedOfLight / (wavelength * 2); // Frequency in Hz
                    double length = wavelength / 2; // Length of dipole
                    txtFrequency.setText(String.format("%.2f", frequency / 1_000_000)); // Convert to MHz
                    txtLength.setText(String.format("%.2f", length)); // Return dipole length
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid wavelength.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter either frequency or wavelength.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
