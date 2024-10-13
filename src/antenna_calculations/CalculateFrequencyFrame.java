package antenna_calculations;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculateFrequencyFrame extends JInternalFrame {

    private JTextField wavelengthField;  // For user input of wavelength
    private JTextField frequencyField;    // For displaying the calculated frequency
    private JButton calculateButton;       // Button to perform the calculation

    // Constructor takes the speed of light as a parameter
    public CalculateFrequencyFrame(double speedOfLight) {
        setTitle("Calculate Frequency");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        // Create and position components
        JLabel wavelengthLabel = new JLabel("Wavelength (m):");
        wavelengthLabel.setBounds(20, 20, 120, 25);
        add(wavelengthLabel);

        wavelengthField = new JTextField();
        wavelengthField.setBounds(150, 20, 120, 25);
        add(wavelengthField);

        JLabel frequencyLabel = new JLabel("Frequency (MHz):");
        frequencyLabel.setBounds(20, 60, 120, 25);
        add(frequencyLabel);

        frequencyField = new JTextField();
        frequencyField.setBounds(150, 60, 120, 25);
        frequencyField.setEditable(false); // Make this field read-only
        add(frequencyField);

        calculateButton = new JButton("Calculate Frequency");
        calculateButton.setBounds(30, 100, 180, 30);
        add(calculateButton);

        // Action Listener for the calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateFrequency(speedOfLight);
            }
        });

        // Set the internal frame to be closable
        setClosable(true);
        setIconifiable(true); // Optional: Allows the frame to be minimized
        setMaximizable(true); // Optional: Allows the frame to be maximized
        setResizable(true); // Optional: Allows the frame to be resized
    }

    // Method to calculate frequency based on wavelength
    private void calculateFrequency(double speedOfLight) {
        try {
            double wavelength = Double.parseDouble(wavelengthField.getText());
            if (wavelength <= 0) {
                JOptionPane.showMessageDialog(this, "Wavelength must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Calculate frequency using the formula: f = c / λ
            double frequencyHz = speedOfLight / wavelength; // Frequency in Hz
            double frequencyMHz = frequencyHz / 1_000_000; // Convert to MHz

            frequencyField.setText(String.format("%.2f", frequencyMHz)); // Display frequency in MHz
            JOptionPane.showMessageDialog(this, "Frequency: " + String.format("%.2f", frequencyMHz) + " MHz", "Result", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for wavelength.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

