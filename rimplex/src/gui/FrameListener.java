package gui;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JWindow;

public class FrameListener implements ComponentListener
{

  private JWindow history;
  private JWindow graph;
  private JWindow settings;
  private JWindow step;
  
  public FrameListener(JWindow history, JWindow graph, JWindow settings, JWindow step)
  {
    this.history = history;
    this.graph = graph;
    this.settings = settings;
    this.step = step;
  }
  @Override
  public void componentResized(ComponentEvent e)
  {
    // TODO Auto-generated method stub
    history.setVisible(false);
    graph.setVisible(false);
    settings.setVisible(false);
    step.setVisible(false);
    
  }

  @Override
  public void componentMoved(ComponentEvent e)
  {
    // TODO Auto-generated method stub
    history.setLocation(e.getComponent().getX() + 305, e.getComponent().getY() + 135);
    graph.setLocation(e.getComponent().getX() - 295, e.getComponent().getY() + 135);
    settings.setLocation(e.getComponent().getX() + 300, e.getComponent().getY() + 35);
    step.setLocation(e.getComponent().getX() + 10, e.getComponent().getY() + e.getComponent().getHeight() - 45);
    
  }

  @Override
  public void componentShown(ComponentEvent e)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void componentHidden(ComponentEvent e)
  {
    // TODO Auto-generated method stub
    history.setVisible(false);
    graph.setVisible(false);
    settings.setVisible(false);
    step.setVisible(false);
    
  }

}
