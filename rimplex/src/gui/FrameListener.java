package gui;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JWindow;

/**
 * 
 * @author Jackson Bantley
 *
 */
public class FrameListener implements ComponentListener
{

  private JWindow history;
  private JWindow graph;
  private JWindow settings;
  private JWindow step;

  /**
   * Parameterized Constructor.
   * 
   * @param history
   *          JWindow
   * @param graph
   *          JWindow
   * @param settings
   *          JWindow
   * @param step
   *          JWindow
   */
  public FrameListener(final JWindow history, final JWindow graph, final JWindow settings,
      final JWindow step)
  {
    this.history = history;
    this.graph = graph;
    this.settings = settings;
    this.step = step;
  }

  /**
   * Called when the main frame is resized.
   * Closes all of the windows.
   * 
   * @param e
   *          when the frame is resized.
   */
  @Override
  public void componentResized(final ComponentEvent e)
  {
    // TODO Auto-generated method stub
    history.setVisible(false);
    graph.setVisible(false);
    settings.setVisible(false);
    step.setVisible(false);

  }

  /**
   * Called when the main frame is moved.
   * Moves all of the windows so that it follows the main frame.
   * 
   * @param e
   *          when the frame is moved.
   */
  @Override
  public void componentMoved(final ComponentEvent e)
  {
    // TODO Auto-generated method stub
    history.setLocation(e.getComponent().getX() + 305, e.getComponent().getY() + 135);
    graph.setLocation(e.getComponent().getX() - 295, e.getComponent().getY() + 135);
    settings.setLocation(e.getComponent().getX() + 300, e.getComponent().getY() + 35);
    step.setLocation(e.getComponent().getX() + 10,
        e.getComponent().getY() + e.getComponent().getHeight() - 45);

  }

  /**
   * Called when the main frame is opened.
   * Do nothing.
   * 
   * @param e
   *          when the frame is opened.
   */
  @Override
  public void componentShown(final ComponentEvent e)
  {
    // TODO Auto-generated method stub

  }

  /**
   * Called when the main frame is closed.
   * Closes all of the windows.
   * 
   * @param e
   *          when the frame is closed.
   */
  @Override
  public void componentHidden(final ComponentEvent e)
  {
    // TODO Auto-generated method stub
    history.setVisible(false);
    graph.setVisible(false);
    settings.setVisible(false);
    step.setVisible(false);

  }

}
