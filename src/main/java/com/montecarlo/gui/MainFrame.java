package com.montecarlo.gui;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MainFrame {

  @Value("${height}")
  int height;
  @Value("${width}")
  int width;
  private JFrame frame = new JFrame("JDF-14");

  @PostConstruct
  void initFrame(){
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(width,height);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  public void addComponent(java.awt.Component component){
    frame.add(component);
    frame.revalidate();
  }

  public void changeTitle(String title){
    frame.setTitle("JDF-14 "+title);
  }

}
