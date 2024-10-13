package antenna_calculations;

import javax.swing.*;

public class MainFrame extends javax.swing.JFrame {

    public static final double SPEED_OF_LIGHT = 300000000.0; // Approximate speed of light in meters per second

    private JDesktopPane desktopPane;
    private JMenuBar mnuBar;
    private JMenu mnuOperations;
    private JMenu mnuGraphs;
    private JMenuItem mnuItemFrequency;
    private JMenuItem mnuItemWavelength;
    private JMenuItem mnuItemDipole;
    private JMenuItem mnuItemHFGraph;
    private JMenuItem mnuItemVHFGraph;
    private JMenuItem mnuItemUHFGraph;

    public MainFrame() {
        initComponents();
    }

    private void initComponents() {

        // Create desktop pane to hold internal frames
        desktopPane = new JDesktopPane();
        getContentPane().add(desktopPane);

        // Menu bar and items
        mnuBar = new JMenuBar();

        mnuOperations = new JMenu("Operations");
        mnuItemFrequency = new JMenuItem("Calculate Frequency");
        mnuItemWavelength = new JMenuItem("Calculate Wavelength");
        mnuItemDipole = new JMenuItem("Dipole HalfWave");

        mnuOperations.add(mnuItemFrequency);
        mnuOperations.add(mnuItemWavelength);
        mnuOperations.add(mnuItemDipole);
        mnuBar.add(mnuOperations);

        // Graphs menu
        mnuGraphs = new JMenu("Graphs");
        mnuItemHFGraph = new JMenuItem("Graph of 3 MHz to 30 MHz (HF)");
        mnuItemVHFGraph = new JMenuItem("Graph of 30 MHz to 300 MHz (VHF)");
        mnuItemUHFGraph = new JMenuItem("Graph of 300 MHz to 3000 MHz (UHF)");

        mnuGraphs.add(mnuItemHFGraph);
        mnuGraphs.add(mnuItemVHFGraph);
        mnuGraphs.add(mnuItemUHFGraph);
        mnuBar.add(mnuGraphs);

        setJMenuBar(mnuBar);

        // Action listener for "Calculate Frequency"
        mnuItemFrequency.addActionListener(evt -> openCalculateFrequencyFrame());

        // Action listener for "Calculate Wavelength"
        mnuItemWavelength.addActionListener(evt -> openCalculateWavelengthFrame());

        // Action listener for "Dipole HalfWave"
        mnuItemDipole.addActionListener(evt -> openDipoleFrame());

        // Action listeners for Graphs
        mnuItemHFGraph.addActionListener(evt -> openGraphFrame("Graph of 3 MHz to 30 MHz (HF)", 3, 30));
        mnuItemVHFGraph.addActionListener(evt -> openGraphFrame("Graph of 30 MHz to 300 MHz (VHF)", 30, 300));
        mnuItemUHFGraph.addActionListener(evt -> openGraphFrame("Graph of 300 MHz to 3000 MHz (UHF)", 300, 3000));

        // Frame settings
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
    }

    // Opens the CalculateFrequencyFrame
    private void openCalculateFrequencyFrame() {
        CalculateFrequencyFrame frequencyFrame = new CalculateFrequencyFrame(SPEED_OF_LIGHT);
        desktopPane.add(frequencyFrame);
        frequencyFrame.setVisible(true);
    }

    // Opens the CalculateWavelengthFrame
    private void openCalculateWavelengthFrame() {
        CalculateWavelengthFrame wavelengthFrame = new CalculateWavelengthFrame(SPEED_OF_LIGHT);
        desktopPane.add(wavelengthFrame);
        wavelengthFrame.setVisible(true);
    }

    // Opens the DipoleFrame
    private void openDipoleFrame() {
        DipoleFrame dipoleFrame = new DipoleFrame(SPEED_OF_LIGHT);
        desktopPane.add(dipoleFrame);
        dipoleFrame.setVisible(true);
    }

    // Opens the graph frame for HF, VHF, UHF
    private void openGraphFrame(String title, double minFrequency, double maxFrequency) {
        GraphFrame graphFrame = new GraphFrame(title, minFrequency, maxFrequency, SPEED_OF_LIGHT);
        desktopPane.add(graphFrame);
        graphFrame.setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            MainFrame mf = new MainFrame();
            mf.setLocationRelativeTo(null);
            mf.setVisible(true);
        });
    }
}
