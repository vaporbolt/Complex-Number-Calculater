package gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Seth Roper
 * @version 3/31/2021 Handles actions for the addition button.
 *
 */
public class AdditionHandler implements ActionListener
{
  private InputField input;

  /**
   * Creates an addition handler.
   * 
   * @param input
   *          the input field
   */
  public AdditionHandler(final InputField input)
  {
    this.input = input;
  }

  /**
   * When clicked, adds a "+" to the input field.
   * 
   * @param e
   *          when the user clicks the "+" button
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    // try to use button
    try
    {
      // adds addition sign to input field
      input.getTextField().setText(input.getTextField().getText() + " + ");
      input.inputTypesetting(0, input.getTextField().getText().length());
    }
    catch (NullPointerException ex)
    {
      // beep if button doesn't work
      Toolkit.getDefaultToolkit().beep();
    }

  }

}
