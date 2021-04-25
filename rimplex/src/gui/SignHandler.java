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
  public SignHandler(final InputField input)
  {
    this.input = input;
  }

  /**
   * Reverses the sign of the current Complex Number.
   * 
   * @param e
   *          ActionEvent
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    char token;
    String numbers = "0123456789.i";
    int insert = 0;
    boolean start = false;
    try
    {
      String text = input.getTextField().getText();

      for (int i = text.length() - 1; i >= 0; i--)
      {
        token = text.charAt(i);
        if (token == ')')
        {
          insert = text.lastIndexOf('(');
          break;
        }
        if (numbers.indexOf(token) != -1)
        {
          start = true;
        }
        else if (start)
        {
          insert = i + 1;
          break;
        }

      }
      if (insert != 0 && text.charAt(insert - 1) == '-')
      {
        text = text.substring(0, insert - 1) + text.substring(insert);
      }
      else
      {
        text = text.substring(0, insert) + "-" + text.substring(insert);
      }

      input.getTextField().setText(text);
      input.inputTypesetting(0, input.getTextField().getText().length());

    }
    catch (Exception ex)
    {
      Toolkit.getDefaultToolkit().beep();
    }

  }

}
