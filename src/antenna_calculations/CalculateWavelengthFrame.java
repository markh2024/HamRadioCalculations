package antenna_calculations;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculateWavelengthFrame extends JInternalFrame {

    private JTextField frequencyField;  // For user input of frequency
    private JTextField wavelengthField; // For displaying the calculated wavelength
    private JButton calculateButton;    // Button to perform the calculation

    // Constructor takes the speed of light as a parameter
    public CalculateWavelengthFrame(double speedOfLight) {
        setTitle("Calculate Wavelength");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        // Create and position components
        JLabel frequencyLabel = new JLabel("Frequency (MHz):");
        frequencyLabel.setBounds(20, 20, 120, 25);
        add(frequencyLabel);

        frequencyField = new JTextField();
        frequencyField.setBounds(150, 20, 120, 25);
        add(frequencyField);

        JLabel wavelengthLabel = new JLabel("Wavelength (m):");
        wavelengthLabel.setBounds(20, 60, 120, 25);
        add(wavelengthLabel);

        wavelengthField = new JTextField();
        wavelengthField.setBounds(150, 60, 120, 25);
        wavelengthField.setEditable(false); // Make this field read-only
        add(wavelengthField);

        calculateButton = new JButton("Calculate Wavelength");
        calculateButton.setBounds(30, 100, 180, 30);
        add(calculateButton);

        // Action Listener for the calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateWavelength(new BigDecimal(speedOfLight));
            }
        });

        // Set the internal frame to be closable
        setClosable(true);
        setIconifiable(true); // Optional: Allows the frame to be minimized
        setMaximizable(true); // Optional: Allows the frame to be maximized
        setResizable(true);   // Optional: Allows the frame to be resized
    }

    // Method to calculate wavelength based on frequency using BigDecimal for more accuracy
    private void calculateWavelength(BigDecimal speedOfLight) {
        try {
            double frequencyMHz = Double.parseDouble(frequencyField.getText());
            if (frequencyMHz <= 0) {
                JOptionPane.showMessageDialog(this, "Frequency must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Convert frequency to Hz and perform the calculation with BigDecimal for higher precision
            BigDecimal frequencyHz = new BigDecimal(frequencyMHz).multiply(new BigDecimal(1_000_000)); // MHz to Hz
            BigDecimal wavelength = speedOfLight.divide(frequencyHz, 5, RoundingMode.HALF_UP); // Wavelength in meters

            // Display the rounded wavelength
            wavelengthField.setText(wavelength.toString());
            JOptionPane.showMessageDialog(this, "Wavelength: " + wavelength + " meters", "Result", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for frequency.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


