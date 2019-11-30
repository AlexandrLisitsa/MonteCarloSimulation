package com.montecarlo.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D.Double;
import javax.swing.JPanel;
import org.springframework.stereotype.Component;

@Component
public class PaintPanel extends JPanel {

  private Shape shape;
  private Color color;

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D brush = (Graphics2D) g;
    if (shape != null) {
      brush.setColor(color);
      Rectangle pos = shape.getBounds();
      if(shape.getClass().equals(Rectangle.class)){
        brush.fillRect(pos.x,pos.y,pos.width,pos.height);
      }else if (shape.getClass().equals(Double.class)){
        brush.fillOval(pos.x,pos.y,pos.width,pos.height);
      }
    }
  }

  public void paintComponent(Shape shape, Color color) {
    this.shape = shape;
    this.color = color;
    this.paintComponent(this.getGraphics());
  }

}
