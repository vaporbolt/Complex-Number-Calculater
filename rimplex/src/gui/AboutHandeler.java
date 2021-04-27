package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Seth Roper
 * @version 4/26/21
 * Action Listener for the about button. Constructs a new HelpWindow when pressed.
 *
 */
public class AboutHandeler implements ActionListener
{

  private AboutFrame frame;
  /**
   * @param frame the aboutFrame this listener shoul reference
   */
  public AboutHandeler(final AboutFrame frame)
  {
    this.frame = frame;
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    frame.createFrame();

  }

}
