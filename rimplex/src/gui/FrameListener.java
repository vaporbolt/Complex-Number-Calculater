package gui;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JWindow;

public class FrameListener implements ComponentListener
{

  private JWindow history;
  
  public FrameListener(JWindow history)
  {
    this.history = history;
  }
  @Override
  public void componentResized(ComponentEvent e)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void componentMoved(ComponentEvent e)
  {
    // TODO Auto-generated method stub
    history.setLocation(e.getComponent().getX() + 400, e.getComponent().getY() + 165);
    
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
    
  }

}
