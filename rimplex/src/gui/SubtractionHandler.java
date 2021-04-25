package gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles actions for the subtraction button.
 * 
 * @author Seth Roper
 * @version 3/31/2021
 *
 */
public class SubtractionHandler implements ActionListener
{

  private InputField input;

  /**
   * Creates a subtraction handler.
   * 
   * @param input
   *          the input field
   */
  public SubtractionHandler(final InputField input)
  {
    this.input = input;
  }

  /**
   * When clicked, adds a "-" to the input field.
   * 
   * @param e
   *          when the user clicks the "-" button
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    // tries to click button
    try
    {
      // adds subtraction sign to input field
      input.getTextField().setText(input.getTextField().getText() + " -");
      input.inputTypesetting(0, input.getTextField().getText().length());
    }
    catch (Exception ex)
    {
      // beep if button doesn't work
      Toolkit.getDefaultToolkit().beep();
    }
  }

}
