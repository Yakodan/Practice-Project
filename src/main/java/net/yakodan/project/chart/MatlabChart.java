package net.yakodan.project.chart;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.annotations.XYTitleAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.*;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.chart.ui.RectangleEdge;

import javax.swing.*;

/**
 * @author Jean-Paul from
 * <a href="https://stackoverflow.com/questions/38931111/how-to-make-plots-in-java-like-in-matlab-same-syntax">
 * https://stackoverflow.com/questions/38931111/how-to-make-plots-in-java-like-in-matlab-same-syntax</a>
 */
public class MatlabChart {

    Font font;
    JFreeChart chart;
    LegendTitle legend;
    ArrayList<Color> colors;
    ArrayList<Stroke> strokes;
    ArrayList<Boolean> linesIsVisible;
    ArrayList<Boolean> dotsIsVisible;
    XYSeriesCollection dataset;
    int numberOfPlots;

    public MatlabChart() {
        font = JFreeChart.DEFAULT_TITLE_FONT;
        colors = new ArrayList<Color>();
        strokes = new ArrayList<Stroke>();
        linesIsVisible = new ArrayList<>();
        dotsIsVisible = new ArrayList<>();
        dataset = new XYSeriesCollection();
        numberOfPlots = 0;
    }

    public void plot(double[] x, double[] y, String spec, float lineWidth, String title, boolean isVisibleLine, boolean isVisibleDot) {
        final XYSeries series = new XYSeries(title);
        for (int i = 0; i < x.length; i++)
            series.add(x[i], y[i]);
        dataset.addSeries(series);
        linesIsVisible.add(isVisibleLine);
        dotsIsVisible.add(isVisibleDot);
        numberOfPlots++;
        findColor(spec, lineWidth);
    }

    public void renderPlot() {
        JFreeChart chart = ChartFactory.createXYLineChart(null, null, null, dataset, PlotOrientation.VERTICAL, true, false, false);
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        for(int i=0;i<numberOfPlots;i++){
            renderer.setSeriesLinesVisible(i, linesIsVisible.get(i));
            renderer.setSeriesShapesVisible(i, dotsIsVisible.get(i));
        }
        plot.setRenderer(renderer);
        for (int i = 0; i < numberOfPlots; i++) {
            plot.getRenderer().setSeriesPaint(i, colors.get(i));
            plot.getRenderer().setSeriesStroke(i, strokes.get(i));
        }
        ((NumberAxis) plot.getDomainAxis()).setAutoRangeIncludesZero(false);
        ((NumberAxis) plot.getRangeAxis()).setAutoRangeIncludesZero(false);
        plot.setBackgroundPaint(Color.WHITE);
        legend = chart.getLegend();
        chart.removeLegend();
        this.chart = chart;
    }

    public void showPlot() {
        JFreeChart chart = this.chart;

        ChartPanel panel = new ChartPanel(chart);
        JFrame frame = new JFrame();
        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public JFreeChart getPlot() {
        return this.chart;
    }

    public void checkExists() {
        if (chart == null) {
            throw new IllegalArgumentException("First plot something in the chart before you modify it.");
        }
    }

    public void font(String name, int fontSize) {
        checkExists();
        font = new Font(name, Font.PLAIN, fontSize);
        chart.getTitle().setFont(font);
        chart.getXYPlot().getDomainAxis().setLabelFont(font);
        chart.getXYPlot().getDomainAxis().setTickLabelFont(font);
        chart.getXYPlot().getRangeAxis().setLabelFont(font);
        chart.getXYPlot().getRangeAxis().setTickLabelFont(font);
        legend.setItemFont(font);
    }

    public void title(String title) {
        checkExists();
        chart.setTitle(title);
    }

    public void xlim(double l, double u) {
        checkExists();
        chart.getXYPlot().getDomainAxis().setRange(l, u);
    }

    public void ylim(double l, double u) {
        checkExists();
        chart.getXYPlot().getRangeAxis().setRange(l, u);
    }

    public void xlabel(String label) {
        checkExists();
        chart.getXYPlot().getDomainAxis().setLabel(label);
    }

    public void ylabel(String label) {
        checkExists();
        chart.getXYPlot().getRangeAxis().setLabel(label);
    }

    public void legend(String position) {
        checkExists();
        legend.setItemFont(font);
        legend.setBackgroundPaint(Color.WHITE);
        legend.setFrame(new BlockBorder(Color.BLACK));
        if (position.toLowerCase().equals("northoutside")) {
            legend.setPosition(RectangleEdge.TOP);
            chart.addLegend(legend);
        } else if (position.toLowerCase().equals("eastoutside")) {
            legend.setPosition(RectangleEdge.RIGHT);
            chart.addLegend(legend);
        } else if (position.toLowerCase().equals("southoutside")) {
            legend.setPosition(RectangleEdge.BOTTOM);
            chart.addLegend(legend);
        } else if (position.toLowerCase().equals("westoutside")) {
            legend.setPosition(RectangleEdge.LEFT);
            chart.addLegend(legend);
        } else if (position.toLowerCase().equals("north")) {
            legend.setPosition(RectangleEdge.TOP);
            XYTitleAnnotation ta = new XYTitleAnnotation(0.50, 0.98, legend, RectangleAnchor.TOP);
            chart.getXYPlot().addAnnotation(ta);
        } else if (position.toLowerCase().equals("northeast")) {
            legend.setPosition(RectangleEdge.TOP);
            XYTitleAnnotation ta = new XYTitleAnnotation(0.98, 0.98, legend, RectangleAnchor.TOP_RIGHT);
            chart.getXYPlot().addAnnotation(ta);
        } else if (position.toLowerCase().equals("east")) {
            legend.setPosition(RectangleEdge.RIGHT);
            XYTitleAnnotation ta = new XYTitleAnnotation(0.98, 0.50, legend, RectangleAnchor.RIGHT);
            chart.getXYPlot().addAnnotation(ta);
        } else if (position.toLowerCase().equals("southeast")) {
            legend.setPosition(RectangleEdge.BOTTOM);
            XYTitleAnnotation ta = new XYTitleAnnotation(0.98, 0.02, legend, RectangleAnchor.BOTTOM_RIGHT);
            chart.getXYPlot().addAnnotation(ta);
        } else if (position.toLowerCase().equals("south")) {
            legend.setPosition(RectangleEdge.BOTTOM);
            XYTitleAnnotation ta = new XYTitleAnnotation(0.50, 0.02, legend, RectangleAnchor.BOTTOM);
            chart.getXYPlot().addAnnotation(ta);
        } else if (position.toLowerCase().equals("southwest")) {
            legend.setPosition(RectangleEdge.BOTTOM);
            XYTitleAnnotation ta = new XYTitleAnnotation(0.02, 0.02, legend, RectangleAnchor.BOTTOM_LEFT);
            chart.getXYPlot().addAnnotation(ta);
        } else if (position.toLowerCase().equals("west")) {
            legend.setPosition(RectangleEdge.LEFT);
            XYTitleAnnotation ta = new XYTitleAnnotation(0.02, 0.50, legend, RectangleAnchor.LEFT);
            chart.getXYPlot().addAnnotation(ta);
        } else if (position.toLowerCase().equals("northwest")) {
            legend.setPosition(RectangleEdge.TOP);
            XYTitleAnnotation ta = new XYTitleAnnotation(0.02, 0.98, legend, RectangleAnchor.TOP_LEFT);
            chart.getXYPlot().addAnnotation(ta);
        }
    }

    public void grid(String xAxis, String yAxis) {
        checkExists();
        if (xAxis.equalsIgnoreCase("on")) {
            chart.getXYPlot().setDomainGridlinesVisible(true);
            chart.getXYPlot().setDomainMinorGridlinesVisible(true);
            chart.getXYPlot().setDomainGridlinePaint(Color.GRAY);
        } else {
            chart.getXYPlot().setDomainGridlinesVisible(false);
            chart.getXYPlot().setDomainMinorGridlinesVisible(false);
        }
        if (yAxis.equalsIgnoreCase("on")) {
            chart.getXYPlot().setRangeGridlinesVisible(true);
            chart.getXYPlot().setRangeMinorGridlinesVisible(true);
            chart.getXYPlot().setRangeGridlinePaint(Color.GRAY);
        } else {
            chart.getXYPlot().setRangeGridlinesVisible(false);
            chart.getXYPlot().setRangeMinorGridlinesVisible(false);
        }
    }

    public void saveas(String fileName, int width, int height) {
        checkExists();
        File file = new File(fileName);
        try {
            ChartUtils.saveChartAsJPEG(file, this.chart, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findColor(String spec, float lineWidth) {
        float dash[] = {5.0f};
        float dot[] = {lineWidth};
        Color color = Color.RED;                    // Default color is red
        Stroke stroke = new BasicStroke(lineWidth); // Default stroke is line
        if (spec.contains("-"))
            stroke = new BasicStroke(lineWidth);
        else if (spec.contains(":"))
            stroke = new BasicStroke(lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
        else if (spec.contains("."))
            stroke = new BasicStroke(lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2.0f, dot, 0.0f);
        if (spec.contains("y"))
            color = Color.YELLOW;
        else if (spec.contains("m"))
            color = Color.MAGENTA;
        else if (spec.contains("c"))
            color = Color.CYAN;
        else if (spec.contains("r"))
            color = Color.RED;
        else if (spec.contains("g"))
            color = Color.GREEN;
        else if (spec.contains("b"))
            color = Color.BLUE;
        else if (spec.contains("k"))
            color = Color.BLACK;
        colors.add(color);
        strokes.add(stroke);
    }
}

