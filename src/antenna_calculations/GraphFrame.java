package antenna_calculations;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class GraphFrame extends JInternalFrame {

    private final double speedOfLight;
    private JLabel coordinatesLabel;

    public GraphFrame(String title, double minFrequency, double maxFrequency, double speedOfLight) {
        this.speedOfLight = speedOfLight;
        setTitle(title);
        setSize(600, 400);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        // Create dataset and chart
        XYSeries series = new XYSeries("Wavelength vs Frequency");
        for (double frequency = minFrequency; frequency <= maxFrequency; frequency += 1) {
            double wavelength = speedOfLight / (frequency * 1_000_000); // Frequency in Hz, Wavelength in meters
            series.add(frequency, wavelength);
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
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

        // Customizing the chart
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesShapesVisible(0, false);
        plot.setRenderer(renderer);
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setRange(minFrequency, maxFrequency);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAutoRange(true);

        // Create chart panel and add it to the internal frame
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);

        // Label to display the frequency and wavelength when the mouse hovers over the chart
        coordinatesLabel = new JLabel("Move the mouse over the graph to see values.");
        coordinatesLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add chart mouse listener
        chartPanel.addChartMouseListener(new ChartMouseListener() {
            @Override
            public void chartMouseClicked(ChartMouseEvent event) {
                // Do nothing on click
            }

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

        // Layout setup
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);
        add(coordinatesLabel, BorderLayout.SOUTH);
    }
}
