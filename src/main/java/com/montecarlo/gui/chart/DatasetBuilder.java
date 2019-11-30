package com.montecarlo.gui.chart;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.stereotype.Component;

@Component
public class DatasetBuilder {

  private XYSeries dataset = new XYSeries("PI");

  public void addPoint(double pi,double dotCount){
    dataset.add(dotCount,pi);
  }

  public XYSeriesCollection createDataset(){
    XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
    xySeriesCollection.addSeries(dataset);
    return xySeriesCollection;
  }

}
