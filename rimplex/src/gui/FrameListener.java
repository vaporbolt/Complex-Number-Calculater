package gui;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JWindow;

public class FrameListener implements ComponentListener
{

  private JWindow history;
  private JWindow graph;
  private JWindow settings;
  
  public FrameListener(JWindow history, JWindow graph, JWindow settings)
  {
    this.history = history;
    this.graph = graph;
    this.settings = settings;
  }
  @Override
  public void componentResized(ComponentEvent e)
  {
    // TODO Auto-generated method stub
    history.setVisible(false);
    graph.setVisible(false);
    settings.setVisible(false);
    
  }

  @Override
  public void componentMoved(ComponentEvent e)
  {
    // TODO Auto-generated method stub
    history.setLocation(e.getComponent().getX() + 305, e.getComponent().getY() + 165);
    graph.setLocation(e.getComponent().getX() - 295, e.getComponent().getY() + 165);
    settings.setLocation(e.getComponent().getX() + 50, e.getComponent().getY() + 50);
    
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
    
  }

}
