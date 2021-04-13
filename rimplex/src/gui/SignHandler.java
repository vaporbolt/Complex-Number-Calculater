package gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ACtion handler for the sign button.
 * 
 * @author Endre Szakal
 * @version 4/12/2021
 *
 */
public class SignHandler implements ActionListener
{

  private InputField input;

  /**
   * Parameterized Constructor.
   * 
   * @param input
   *          InputField
   */
  public SignHandler(InputField input)
  {
    this.input = input;
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    try
    {
      String text = input.getTextField().getText();
      if (text.charAt(0) == '-' && text.charAt(1) == '(' && text.charAt(text.length() - 1) == ')')
      {
        text = text.substring(2, text.length() - 1); // make it positive
      }
      else if (text.charAt(0) == '-')
      {
        text = text.substring(1); // make it positive
      }
      else if (text.charAt(0) == '(' && text.charAt(text.length() - 1) == ')')
      {
        text = "-" + text; // make it negative
      }
      else
      {
        text = "-(" + text + ")"; // make it negative
      }
      input.getTextField().setText(text);
    }
    catch (Exception ex)
    {
      Toolkit.getDefaultToolkit().beep();
    }
  }

}
