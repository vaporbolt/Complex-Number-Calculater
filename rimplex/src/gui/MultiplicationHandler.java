package gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import math.OperationType;

/**
 * @author Seth Roper
 * @version 3/31/2021
 * Handles actions for the addition button.
 *
 */
public class MultiplicationHandler implements ActionListener
{

  private InputField input;
  private ArrayList<OperationType> operations;
  
  /**
   * Creates a multiplication handler.
   * 
   * @param display the system's display
   * @param operations the operations list
   */
  public MultiplicationHandler(InputField input, ArrayList<OperationType> operations)
  {
    this.operations = operations;
    this.input = input;
  }
  
  /**
   * When clicked, adds multiplication to the operation list
   * and adds a "*" to the input field.
   * 
   * @param e when the user clicks the "+" button
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    // try to click button
    try
    {
      // adds multiplication to the operations list
      operations.add(OperationType.MULTIPLICATION);
      
      // adds multiplication sign to input field
      input.getTextField().setText(input.getTextField().getText() + " * ");
    }
    catch (Exception ex)
    {
      // beep if button doesn't work
      Toolkit.getDefaultToolkit().beep();
    }
  }
}
