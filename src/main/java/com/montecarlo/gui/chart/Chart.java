package com.montecarlo.gui.chart;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;
import org.springframework.stereotype.Component;

@Component
public class Chart {

  public void showChart(XYDataset dataset){
    JFrame chartFrame = getChartFrame();
    JFreeChart chart = createChart(dataset);
    JPanel chartPanel = createChartPanel(chart);
    chartFrame.add(chartPanel);
    chartFrame.setVisible(true);
  }

  private JFrame getChartFrame(){
    JFrame jFrame = new JFrame("PI Curve");
    jFrame.setDefaultCloseOperation(3);
    jFrame.setSize(1000,700);
    jFrame.setLocationRelativeTo(null);
    return jFrame;
  }

  private JPanel createChartPanel(JFreeChart chart) {
    chart.setPadding(new RectangleInsets(4, 8, 2, 2));
    ChartPanel panel = new ChartPanel(chart);
    panel.setFillZoomRectangle(true);
    panel.setMouseWheelEnabled(true);
    panel.setPreferredSize(new Dimension(600, 300));
    return panel;
  }

  private JFreeChart createChart(XYDataset dataset){
    JFreeChart chart = ChartFactory.createXYLineChart(
        "Curve",
        "Dot count",
        "Accuracy",
        dataset
    );

    chart.setBackgroundPaint(Color.white);

    XYPlot plot = (XYPlot) chart.getPlot();
    plot.setBackgroundPaint(Color.lightGray);
    plot.setDomainGridlinePaint(Color.white);
    plot.setRangeGridlinePaint(Color.white);
    plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
    plot.setDomainCrosshairVisible(true);
    plot.setRangeCrosshairVisible(true);

    XYItemRenderer r = plot.getRenderer();
    if (r instanceof XYLineAndShapeRenderer) {
      XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
      renderer.setBaseShapesVisible(true);
      renderer.setBaseShapesFilled(true);
      renderer.setDrawSeriesLineAsPath(true);
    }
    return chart;
  }



}
