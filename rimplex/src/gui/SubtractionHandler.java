package gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import math.OperationType;

/**
 * @author Seth Roper
 * @version 3/31/2021
 * Handles actions for the subtraction button.
 *
 */
public class SubtractionHandler implements ActionListener
{

  private InputField input;
  private ArrayList<OperationType> operations;
  
  /**
   * Creates a subtraction handler.
   * 
   * @param display the system's display
   * @param operations the operations list
   */
  public SubtractionHandler(InputField input, ArrayList<OperationType> operations)
  {
    this.operations = operations;
    this.input = input;
  }
  
  /**
   * When clicked, adds subtraction to the operation list
   * and adds a "-" to the input field.
   * 
   * @param e when the user clicks the "-" button
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    // tries to click button
    try
    {
      // adds subtraction to the operations list
      operations.add(OperationType.SUBTRACTION);
      
      // adds subtraction sign to input field
      input.getTextField().setText(input.getTextField().getText() + " - ");
    }
    catch (Exception ex)
    {
      // beep if button doesn't work
      Toolkit.getDefaultToolkit().beep();
    }
  }

}
