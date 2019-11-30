package com.montecarlo.engine;

import com.montecarlo.gui.MainFrame;
import com.montecarlo.gui.PaintPanel;
import com.montecarlo.gui.chart.Chart;
import com.montecarlo.gui.chart.DatasetBuilder;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Point2D;
import java.util.Random;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Engine {

  private final MainFrame mainFrame;
  private final PaintPanel paintPanel;
  private final DatasetBuilder datasetBuilder;
  private final Chart chart;

  private Shape circle;
  private Random random = new Random();

  @Value("${countOfDots}")
  private double count;
  @Value("${samplingInterval}")
  double step;

  public Engine(MainFrame mainFrame, PaintPanel paintPanel,
      DatasetBuilder datasetBuilder, Chart chart) {
    this.mainFrame = mainFrame;
    this.paintPanel = paintPanel;
    this.datasetBuilder = datasetBuilder;
    this.chart = chart;
  }

  @PostConstruct
  void start() {
    try {
      initMainFrame();
      counting();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private void counting() {
    double amountInCircle = 0;
    double position = 1;
    for (int i = 0; i < count; i++) {
      Point2D randomPoint = getRandomPoint();
      if (circle.contains(randomPoint)) {
        paintPanel.paintComponent(getLittleRedEllipse(randomPoint), Color.GREEN);
        amountInCircle++;
      } else {
        paintPanel.paintComponent(getLittleRedEllipse(randomPoint), Color.RED);
      }
      if (i == position) {
        datasetBuilder.addPoint(calculatePI(amountInCircle, i), i);
        position += step;
        mainFrame.changeTitle("Current PI:" + calculatePI(amountInCircle, i));
      }
    }
    datasetBuilder.addPoint((amountInCircle / count) * 4, count);
    mainFrame.changeTitle("Current PI:" + calculatePI(amountInCircle, count));
    chart.showChart(datasetBuilder.createDataset());
  }

  private double calculatePI(double amountInCircle, double currentDotsCount) {
    return (amountInCircle / currentDotsCount) * 4;
  }

  private Point2D getRandomPoint() {
    return new Point2D.Double(
        random.nextInt(paintPanel.getWidth()),
        random.nextInt(paintPanel.getHeight())
    );
  }

  private Double getLittleRedEllipse(Point2D point) {
    return new Double(point.getX(), point.getY(), 2, 2);
  }

  private void initMainFrame() throws InterruptedException {
    mainFrame.addComponent(paintPanel);
    Shape rectangle = new Rectangle(0, 0, paintPanel.getWidth(), paintPanel.getHeight());
    paintPanel.paintComponent(rectangle, Color.white);
    Thread.sleep(100);
    circle = new Double(0, 0, paintPanel.getWidth(), paintPanel.getHeight());
    paintPanel.paintComponent(circle, Color.lightGray);
  }

}
