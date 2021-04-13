package gui;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JWindow;

public class FrameListener implements ComponentListener
{

  private JWindow history;
  private JWindow graph;
  
  public FrameListener(JWindow history, JWindow graph)
  {
    this.history = history;
    this.graph = graph;
  }
  @Override
  public void componentResized(ComponentEvent e)
  {
    // TODO Auto-generated method stub
    history.setVisible(false);
    graph.setVisible(false);
    
  }

  @Override
  public void componentMoved(ComponentEvent e)
  {
    // TODO Auto-generated method stub
    history.setLocation(e.getComponent().getX() + 400, e.getComponent().getY() + 165);
    graph.setLocation(e.getComponent().getX() - 275, e.getComponent().getY() + 165);
    
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
    
  }

}
