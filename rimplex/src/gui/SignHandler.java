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
  
  /**
   * applies the opposite sign of the given string.
   * @param input String
   */
  private String applySign(String input)
  {
    
    if (input.charAt(0) == '-' && input.charAt(1) == '(' && input.charAt(input.length() - 1) == ')')
    {
      input = input.substring(2, input.length() - 1); // make it positive
    }
    else if (input.charAt(0) == '-')
    {
      input = input.substring(1); // make it positive
    }
    else if (input.charAt(0) == '(' && input.charAt(input.length() - 1) == ')')
    {
      input = "-" + input; // make it negative
    }
    else
    {
      input = "-(" + input + ")"; // make it negative
    }
    return input;
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String operations = "+-*/";
    try
    {
      String text = input.getTextField().getText();
      // if theres an operation in the field
      if (text.contains(operations))
      {
        // get index of operation
        int opIndex = 0;
        for (int i = 0 ; i < operations.length(); i++)
        {
          int tmp = text.indexOf(operations.charAt(i));
          if (tmp != -1)
          {
            opIndex = tmp; 
          }
        }
        // get right (current) operand
        String currentOperand = text.substring(opIndex);
        // check if the current operand is a complex number
      }
      
      text = applySign(text);
      input.getTextField().setText(text);
    }
    catch (Exception ex)
    {
      Toolkit.getDefaultToolkit().beep();
    }
  }

}
