package net.yakodan.project.tasks;

import net.yakodan.project.chart.MatlabChart;
import net.yakodan.project.polynom.Polynom;
import net.yakodan.project.polynom.PolynomialParser;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Task6 {

    public static final double[] x = new double[]{0, 1.8, 3.6, 5.4, 7.2};
    public static final double[] y = new double[]{-11.8, 11.9, -12.6, -2.6, 7.4};

    public static Polynom getLagrangePolynomial(double[] x, double[] y) {
        Polynom result = PolynomialParser.parse("0");
        for (int i = 0; i < y.length; i++) {
            Polynom current = PolynomialParser.parse(String.format("%s", y[i]));
            for (int j = 0; j < x.length; j++) {
                if (i == j) {
                    continue;
                }
                double coeff = (double) Math.round((x[i] - x[j]) * 1000000) / 1000000;
                String polyString = x[j] >= 0 ? String.format("x - %s", x[j]) : String.format("x + %s", -x[j]);
                current = current.multiply(PolynomialParser.parse(polyString));
                current = current.divideByNumber(coeff);
            }
            result = result.add(current);
        }

        return result;
    }

    public static Polynom getNewtonPolynomial(double[] x, double[] y) {
        Polynom result = PolynomialParser.parse(String.format("%s", y[0]));
        for (int i = 1; i < x.length; i++) {
            Polynom current = PolynomialParser.parse("1");
            for (int j = 0; j < i; j++) {
                String polyString = x[j] >= 0 ? String.format("x - %s", x[j]) : String.format("x + %s", -x[j]);
                current = current.multiply(PolynomialParser.parse(polyString));
            }

            double coeff = 0;

            for (int j = 0; j <= i; j++) {
                double temp = y[j];
                for (int k = 0; k <= i; k++) {
                    if (j == k) {
                        continue;
                    }
                    temp /= x[j] - x[k];
                }
                coeff += temp;
            }

            current = current.multiply(PolynomialParser.parse(String.format("%s", coeff)));

            result = result.add(current);
        }

        return result;
    }

    public static void showResult() {
        double h = 0.01;
        double right = 10;
        double[] xNew = new double[(int) (right / h)];
        double[] yLagrange = new double[xNew.length];
        double[] yNewton = new double[xNew.length];
        double[] xt = new double[x.length];
        double[] ytLagrange = new double[y.length];
        double[] ytNewton = new double[y.length];
        Polynom lagrangePolynom = getLagrangePolynomial(x, y);
        Polynom newtonPolynom = getNewtonPolynomial(x,y);

        for (int i = 0; i < xNew.length; i++) {
            xNew[i] = h * i;
            yLagrange[i] = lagrangePolynom.valueOf(xNew[i]);
            yNewton[i] = newtonPolynom.valueOf(xNew[i]);
        }
        for (int i = 0; i < x.length; i++) {
            xt[i] = x[i] + 0.9;
            ytLagrange[i] = (double) Math.round(lagrangePolynom.valueOf(xt[i])*100)/100;
            ytNewton[i] = (double) Math.round(newtonPolynom.valueOf(xt[i])*100)/100;
        }

        MatlabChart fig = new MatlabChart();
        fig.plot(xNew, yLagrange, "b-", 1.0f, "Многочлен Лагранжа", true, false);
        fig.plot(xNew, yNewton, "r-", 4.0f, "Многочлен Ньютона", true, false);
        fig.plot(x, y, "g.", 3.0f, "Оригинальные точки", false, true);
        fig.plot(xt, ytLagrange, "c.", 2.0f, "Промежуточные точки по Лагранжу", false, true);
        fig.plot(xt, ytNewton, "m.", 2.0f, "Промежуточные точки по Ньютону", false, true);
        fig.renderPlot();
        fig.grid("on", "on");
        fig.xlabel("x");
        fig.ylabel("y");
        fig.legend("southwest");
        JFreeChart chart = fig.getPlot();

        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());

        JTextArea labelLagrange = new JTextArea();
        labelLagrange.setText(String.format("Многочлен Лагранжа: %s\n" +
                "Значения функции в промежуточных точках по Лагранжу: %s", lagrangePolynom, Arrays.toString(ytLagrange)));
        labelLagrange.setFont(new Font("Arial", Font.BOLD,18));
        labelLagrange.setAlignmentX(TextArea.LEFT_ALIGNMENT);
//        labelLagrange.setPreferredSize(new Dimension(1000, 200));
        labelLagrange.setWrapStyleWord(true);
        labelLagrange.setEditable(false);
        labelLagrange.setFocusable(false);


        JTextArea labelNewton = new JTextArea();
        labelNewton.setText(String.format("Многочлен Ньютона: %s\n" +
                "Значения функции в промежуточных точках по Ньютону: %s", newtonPolynom, Arrays.toString(ytNewton)));
        labelNewton.setFont(new Font("Arial", Font.BOLD,18));
        labelNewton.setAlignmentX(TextArea.LEFT_ALIGNMENT);
//        labelNewton.setPreferredSize(new Dimension(1000, 200));
        labelNewton.setWrapStyleWord(true);
        labelNewton.setEditable(false);
        labelNewton.setFocusable(false);


        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(850,450));

        frame.getContentPane().add(chartPanel);
        frame.getContentPane().add(labelLagrange);
        frame.getContentPane().add(labelNewton);

        frame.setPreferredSize(new Dimension(850, 600));
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        showResult();
    }
}
