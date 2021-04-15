package gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Seth Roper
 * @version 3/31/2021
 * Handles actions for the division button.
 *
 */
public class DivisionHandler implements ActionListener
{

  private InputField input;
  
  /**
   * Creates a division handler
   * 
   * @param input the input field
   */
  public DivisionHandler(InputField input)
  {
    this.input = input;
  }
  
  /**
   * When clicked, adds a "/" to the input field.
   * 
   * @param e when the user clicks the division button
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    // tries to click button
    try
    {
      // adds division sign to input field
      input.getTextField().setText(input.getTextField().getText() + " / ");
      input.inputTypesetting(0, input.getTextField().getText().length());
    }
    catch (Exception ex)
    {
      // beep if button doesn't work
      Toolkit.getDefaultToolkit().beep();
    }
  }
}
