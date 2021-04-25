package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import visualization.CartesianPlane;

/**
 * Handles actions for the reset button.
 * 
 * @author Seth Roper
 * @version 3/31/2021
 *
 */
public class ResetHandler implements ActionListener
{

  private DisplayComponent display;
  private InputField input;
  private CartesianPlane plane;
  private StepDisplay steps;

  /**
   * Creates a reset handler.
   * 
   * @param display
   *          the system's display
   * @param input
   *          the input field
   * @param plane
   *          the graph
   * 
   * @param steps
   *          StepDisplay
   */
  public ResetHandler(final DisplayComponent display, final InputField input,
      final CartesianPlane plane, final StepDisplay steps)
  {
    this.display = display;
    this.input = input;
    this.plane = plane;
    this.steps = steps;
  }

  /**
   * When clicked, the text from display and input field is cleared, the text from display is
   * cleared, and the graph is cleared.
   * 
   * @param e
   *          when user clicks the reset button
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    // resets display text, input field text, complex numbers list, and operations list
    display.reset();
    input.clear();
    plane.reset();
    steps.reset();
    input.getTextField().requestFocus();

  }

}
