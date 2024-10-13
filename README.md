# HamRadioCalculations


Showing some of the functionality 

## Graphs 

![graphs](https://github.com/user-attachments/assets/07d296ab-aa22-4460-9de5-6ba4e8e86971)

## Dipole calculations
![dipoleCalculations](https://github.com/user-attachments/assets/a0a1e4f1-e093-4eed-9b9e-6e40cbc95de1)

## Main Screen
![mainscreen](https://github.com/user-attachments/assets/015b917e-1432-4269-a5f7-71f0af3ccf08)

# MainFrame Class Overview

The `MainFrame` class is part of the `antenna_calculations` package and serves as the main window of the application. 
It utilizes Java Swing components to provide a graphical user interface (GUI) for performing calculations related to ham radio . 

This document outlines the key features and functionalities of the `MainFrame` class.

## Key Components

### Constants

```java
public static final double SPEED_OF_LIGHT = 300000000.0; // Approximate speed of light in meters per second
GUI Components

    JDesktopPane: A container for internal frames (sub-windows) within the main frame.
    JMenuBar: Contains the application's menus.
    JMenu: Represents a dropdown menu (e.g., Operations, Graphs).
    JMenuItem: Represents individual menu items within a menu (e.g., Calculate Frequency, Calculate Wavelength).

Constructor

java

public MainFrame() {
    initComponents();
}

    The constructor initializes the main frame by calling the initComponents() method.

GUI Initialization

java

private void initComponents() {
    // Create desktop pane to hold internal frames
    desktopPane = new JDesktopPane();
    getContentPane().add(desktopPane);
    // Menu bar and items setup...
    // Frame settings
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setSize(800, 600);
}

    Creates a JDesktopPane for holding internal frames.
    Sets up the menu bar with options for various calculations and graphs.
    Configures the frame settings, including the default close operation and size.

Menu Item Action Listeners

The following action listeners handle user interactions with the menu items:

    Calculate Frequency

    java

mnuItemFrequency.addActionListener(evt -> openCalculateFrequencyFrame());

    Opens the frame for calculating frequency when selected.

Calculate Wavelength

java

mnuItemWavelength.addActionListener(evt -> openCalculateWavelengthFrame());

    Opens the frame for calculating wavelength.

Dipole HalfWave

java

    mnuItemDipole.addActionListener(evt -> openDipoleFrame());

        Opens the frame for calculating the dipole length.

Graphs Menu Item Action Listeners

These action listeners open the respective graph frames for different frequency ranges.

java

mnuItemHFGraph.addActionListener(evt -> openGraphFrame("Graph of 3 MHz to 30 MHz (HF)", 3, 30));
mnuItemVHFGraph.addActionListener(evt -> openGraphFrame("Graph of 30 MHz to 300 MHz (VHF)", 30, 300));
mnuItemUHFGraph.addActionListener(evt -> openGraphFrame("Graph of 300 MHz to 3000 MHz (UHF)", 300, 3000));

Frame Opening Methods

Each of these methods is responsible for creating and displaying the respective calculation frames.

java

private void openCalculateFrequencyFrame() {
    CalculateFrequencyFrame frequencyFrame = new CalculateFrequencyFrame(SPEED_OF_LIGHT);
    desktopPane.add(frequencyFrame);
    frequencyFrame.setVisible(true);
}

    Opens the frequency calculation frame.
    Similar methods exist for wavelength and dipole frames.

Main Method

java

public static void main(String args[]) {
    try {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (Exception e) {
        e.printStackTrace();
    }
    java.awt.EventQueue.invokeLater(() -> {
        MainFrame mf = new MainFrame();
        mf.setLocationRelativeTo(null);
        mf.setVisible(true);
    });
}

    The main method sets the look and feel of the application to Nimbus.
    It initializes and displays the main frame, centering it on the screen.

# CalculateFrequencyFrame Class Overview

The `CalculateFrequencyFrame` class is part of the `antenna_calculations` package and extends `JInternalFrame`, providing a user interface for calculating the frequency of electromagnetic waves based on their wavelength. This document outlines the key features and functionalities of the `CalculateFrequencyFrame` class.

## Key Components

### Instance Variables

- **JTextField wavelengthField**: A text field for user input of the wavelength in meters.
- **JTextField frequencyField**: A read-only text field that displays the calculated frequency in megahertz (MHz).
- **JButton calculateButton**: A button that triggers the frequency calculation when clicked.

### Constructor

```java
public CalculateFrequencyFrame(double speedOfLight) {
    setTitle("Calculate Frequency");
    setSize(300, 200);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(null);
    // Component creation and positioning...
}


    Parameters: The constructor takes a double parameter speedOfLight, representing the speed of light in meters per second.
    Frame Title: Sets the title of the internal frame to "Calculate Frequency."
    Frame Size: Configures the size of the frame to 300 pixels wide and 200 pixels high.
    Close Operation: Sets the default close operation to dispose of the frame when closed.
    Layout: Uses a null layout, allowing for absolute positioning of components.

Component Creation and Layout

Components are created and positioned within the frame:

java

JLabel wavelengthLabel = new JLabel("Wavelength (m):");
wavelengthLabel.setBounds(20, 20, 120, 25);
add(wavelengthLabel);

    Wavelength Label: Displays the text "Wavelength (m):" and is positioned at coordinates (20, 20).
    Wavelength Field: A text field where users enter the wavelength value, positioned next to the label.
    Frequency Label: Displays the text "Frequency (MHz):" and is positioned below the wavelength label.
    Frequency Field: A read-only text field that displays the calculated frequency.
    Calculate Button: A button labeled "Calculate Frequency" that triggers the calculation when clicked.

Action Listener for Calculate Button

java

calculateButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        calculateFrequency(speedOfLight);
    }
});

    This code adds an action listener to the calculateButton, which calls the calculateFrequency method when the button is clicked.

Method to Calculate Frequency

java

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

    Input Validation: The method attempts to parse the wavelength from the input field. If the input is not a valid number or is less than or equal to zero, it shows an error message.
    Frequency Calculation: The frequency is calculated using the formula:
    f=cλ
    f=λc​ where cc is the speed of light and λλ is the wavelength.
    Conversion to MHz: The calculated frequency in hertz (Hz) is converted to megahertz (MHz) by dividing by 1,000,000.
    Display Results: The calculated frequency is formatted to two decimal places and displayed in the frequency field. Additionally, a message dialog shows the result.

Frame Properties

    Closable: The frame can be closed by the user.
    Iconifiable: The frame can be minimized.
    Maximizable: The frame can be maximized.
    Resizable: The frame can be resized by the user.

Summary

The CalculateFrequencyFrame class provides a straightforward interface for calculating the frequency of electromagnetic waves based on user-provided wavelength input. It incorporates input validation, error handling, and visual feedback to enhance user experience. This class is a crucial part of the ham radio course , facilitating frequency calculations necessary for antenna and feeders .


# CalculateWavelengthFrame Class Overview

The `CalculateWavelengthFrame` class is part of the `antenna_calculations` package and extends `JInternalFrame`. It provides a graphical user interface (GUI) for calculating the wavelength of electromagnetic waves based on a user-supplied frequency. Below is a detailed explanation of its components and functionalities.

## Key Components

### Instance Variables

- **JTextField frequencyField**: A text field for user input of frequency in megahertz (MHz).
- **JTextField wavelengthField**: A read-only text field that displays the calculated wavelength in meters.
- **JButton calculateButton**: A button that triggers the calculation of wavelength when clicked.

### Constructor

```java
public CalculateWavelengthFrame(double speedOfLight) {
    setTitle("Calculate Wavelength");
    setSize(300, 200);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(null);
    // Component creation and positioning...
}


    Parameters: The constructor takes a double parameter speedOfLight, which represents the speed of light in meters per second.
    Frame Title: Sets the title of the internal frame to "Calculate Wavelength."
    Frame Size: Configures the size of the frame to 300 pixels wide and 200 pixels high.
    Close Operation: Sets the default close operation to dispose of the frame when it is closed.
    Layout: Uses a null layout for absolute positioning of components.

Component Creation and Layout

Components are created and positioned within the frame:

java

JLabel frequencyLabel = new JLabel("Frequency (MHz):");
frequencyLabel.setBounds(20, 20, 120, 25);
add(frequencyLabel);

    Frequency Label: Displays "Frequency (MHz):" and is positioned at (20, 20).
    Frequency Field: A text field for user input of frequency, positioned next to the label.
    Wavelength Label: Displays "Wavelength (m):" and is positioned below the frequency label.
    Wavelength Field: A read-only text field that displays the calculated wavelength, positioned next to the wavelength label.
    Calculate Button: A button labeled "Calculate Wavelength" that initiates the wavelength calculation when clicked.

Action Listener for Calculate Button

java

calculateButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        calculateWavelength(new BigDecimal(speedOfLight));
    }
});

    This code adds an action listener to the calculateButton, which calls the calculateWavelength method with the speed of light as a BigDecimal when the button is clicked.

Method to Calculate Wavelength

java

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

    Input Validation: The method tries to parse the frequency from the input field. If the input is not a valid number or is less than or equal to zero, it displays an error message.
    Frequency Conversion: The frequency is converted from megahertz (MHz) to hertz (Hz) using BigDecimal for precision.
    Wavelength Calculation: The wavelength is calculated using the formula:
    λ=cf
    λ=fc​ where cc is the speed of light and ff is the frequency in hertz.
    Display Results: The calculated wavelength is displayed in the wavelength field, and a message dialog shows the result.

Frame Properties

    Closable: The frame can be closed by the user.
    Iconifiable: The frame can be minimized.
    Maximizable: The frame can be maximized.
    Resizable: The frame can be resized by the user.

Summary

The CalculateWavelengthFrame class provides a user-friendly interface for calculating the wavelength of electromagnetic waves based on a user-provided frequency. It utilizes BigDecimal for improved precision in calculations, ensures input validation, and provides visual feedback through message dialogs. This class plays an essential role in the ham radio courses  by facilitating accurate wavelength calculations.

# GraphFrame Class Overview

The `GraphFrame` class is part of the `antenna_calculations` package and extends `JInternalFrame`. This class provides a graphical interface to display a chart of wavelength versus frequency using the JFreeChart library. Below is a detailed breakdown of its components and functionalities.

## Key Components

### Instance Variables

- **double speedOfLight**: Stores the speed of light, which is used for wavelength calculations.
- **JLabel coordinatesLabel**: A label that displays the frequency and wavelength values when the mouse hovers over the graph.

### Constructor

```java
public GraphFrame(String title, double minFrequency, double maxFrequency, double speedOfLight) {
    this.speedOfLight = speedOfLight;
    // Frame properties and dataset creation...
}

    Parameters:
        String title: The title of the internal frame.
        double minFrequency: The minimum frequency value for the graph.
        double maxFrequency: The maximum frequency value for the graph.
        double speedOfLight: The speed of light in meters per second.

    Frame Initialization:
        Sets the title and size of the internal frame.
        Configures the frame to be closable, iconifiable, maximizable, and resizable.

Dataset and Chart Creation

The dataset for the graph is created using an XYSeries object, which stores frequency and wavelength pairs:

java

XYSeries series = new XYSeries("Wavelength vs Frequency");
for (double frequency = minFrequency; frequency <= maxFrequency; frequency += 1) {
    double wavelength = speedOfLight / (frequency * 1_000_000); // Frequency in Hz, Wavelength in meters
    series.add(frequency, wavelength);
}

    This loop iterates through frequency values from minFrequency to maxFrequency, calculates the corresponding wavelength, and adds them to the series.

Chart Configuration

java

JFreeChart chart = ChartFactory.createXYLineChart(
    "Wavelength vs Frequency",
    "Frequency (MHz)",
    "Wavelength (meters)",
    dataset,
    PlotOrientation.VERTICAL,
    true,
    true,
    false
);

    The chart is created with the specified title, axis labels, dataset, and orientation.

Customizing the Chart

java

XYPlot plot = chart.getXYPlot();
XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
renderer.setSeriesPaint(0, Color.BLUE);
renderer.setSeriesShapesVisible(0, false);
plot.setRenderer(renderer);

    The plot is customized to set the line color to blue and hide the shapes for the data points.

Chart Panel and Interaction

java

ChartPanel chartPanel = new ChartPanel(chart);
chartPanel.setMouseWheelEnabled(true);

    The chart is added to a ChartPanel, which enables mouse wheel support for zooming.

Mouse Listener for Coordinate Display

java

chartPanel.addChartMouseListener(new ChartMouseListener() {
    @Override
    public void chartMouseMoved(ChartMouseEvent event) {
        // Get the coordinates where the mouse is located
        if (event.getEntity() != null) {
            double mouseX = event.getTrigger().getX();
            double mouseY = event.getTrigger().getY();
            Point2D point = chartPanel.translateScreenToJava2D(new Point((int) mouseX, (int) mouseY));
            Rectangle2D plotArea = chartPanel.getScreenDataArea();
            double frequency = plot.getDomainAxis().java2DToValue(point.getX(), plotArea, plot.getDomainAxisEdge());
            double wavelength = speedOfLight / (frequency * 1_000_000); // Calculate wavelength in meters
            coordinatesLabel.setText(String.format("Frequency = %.2f MHz, Wavelength = %.6f meters", frequency, wavelength));
        }
    }
});

    The chartMouseMoved method calculates the frequency and wavelength based on the mouse position over the graph and updates the coordinatesLabel accordingly.

Layout Setup

java

setLayout(new BorderLayout());
add(chartPanel, BorderLayout.CENTER);
add(coordinatesLabel, BorderLayout.SOUTH);

    The layout of the internal frame is set to BorderLayout, placing the chart in the center and the coordinates label at the bottom.

Summary

The GraphFrame class provides a comprehensive GUI for visualizing the relationship between wavelength and frequency. It utilizes the JFreeChart library to create an interactive chart, allowing users to hover over the graph and see real-time calculations of frequency and wavelength. This class enhances the functionality for ham radio courses  by providing a clear visual representation of electromagnetic wave properties.


# DipoleFrame Class Overview

The `DipoleFrame` class is part of the `antenna_calculations` package and extends `JInternalFrame`. This class provides a graphical user interface (GUI) for calculating the properties of a dipole antenna, including the frequency, wavelength, and total length of the dipole. The calculations are based on the speed of light and allow users to select their preferred calculation method. 

## Key Components

### Instance Variables

- **double speedOfLight**: Stores the speed of light, used for calculations related to frequency and wavelength.
- **JRadioButton rbtnCalculateFrequency**: Radio button to select calculation of frequency and wavelength based on the total length of the dipole.
- **JRadioButton rbtnCalculateLength**: Radio button to select calculation of the total length of the dipole based on frequency or wavelength.
- **JTextField txtFrequency**: Text field for user input of frequency in megahertz (MHz).
- **JTextField txtWavelength**: Text field for user input of wavelength in meters.
- **JTextField txtLength**: Text field for user input of the total length of the dipole in meters.
- **JButton btnCalculate**: Button to trigger the calculation based on user input.

### Constructor

```java
public DipoleFrame(double speedOfLight) {
    this.speedOfLight = speedOfLight;
    // Frame properties and component initialization...
}


    Parameters:
        double speedOfLight: The speed of light used in calculations.
    Frame Initialization:
        Sets the title and size of the internal frame.
        Configures the frame to be closable, iconifiable, maximizable, and non-resizable.
        Calls the initComponents() method to set up the UI components.

Method: initComponents()

The initComponents method initializes the UI components and sets their properties:

java

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

    Radio Buttons: Two radio buttons are created for selecting calculation methods.
    ButtonGroup: Groups the radio buttons so only one can be selected at a time.
    Text Fields: Creates text fields for user input.
    Button: The calculate button triggers the performCalculation method when clicked.
    Layout: Uses a GridLayout to arrange components in a grid format.
    Action Listeners: Added to the radio buttons to clear input fields when a selection is made.

Method: clearFields()

java

private void clearFields() {
    // Clear the text fields
    txtFrequency.setText("");
    txtWavelength.setText("");
    txtLength.setText("");
}

    This method clears the input fields for frequency, wavelength, and length to ensure a clean input state when switching calculation modes.

Method: performCalculation(ActionEvent event)

The performCalculation method executes the calculations based on the selected radio button:

java

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

    Frequency Calculation: If the frequency radio button is selected, it retrieves the length of the dipole, calculates frequency and wavelength, and updates the respective text fields. Input validation is performed to ensure valid numerical entries.
    Length Calculation: If the length radio button is selected, it checks for input in either frequency or wavelength, calculates the missing parameter based on the provided value, and updates the appropriate text fields. Error handling with JOptionPane alerts the user for invalid or empty input.

Summary

The DipoleFrame class provides an interactive GUI for users to perform calculations related to dipole antennas. It enables the calculation of frequency and wavelength based on the dipole's length or vice versa, leveraging the speed of light. The use of radio buttons allows for a straightforward selection of calculation methods, while text fields facilitate user input. The class incorporates input validation and user feedback to enhance the user experience and ensure accurate calculations.
