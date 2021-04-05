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
public class AdditionHandler implements ActionListener
{

  private DisplayComponent display;
  private ArrayList<OperationType> operations;
  
  /**
   * Creates an addition handler
   * 
   * @param display the system's display
   * @param operations the operations list
   */
  public AdditionHandler(DisplayComponent display, ArrayList<OperationType> operations)
  {
    this.display = display;
    this.operations = operations;
  }
  
  /**
   * When clicked, adds addition to the operation list
   * and adds a "+" to the display.
   * 
   * @param e when the user clicks the "+" button
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    // try to use button
    try
    {
      // add addition to operation list
      operations.add(OperationType.ADDITION);
      
      // add a "+" to display
      display.addText(" + ");
      display.displayTypesetting(0, display.getText().length());
    }
    catch(Exception ex)
    {
      // beep if button doesn't work
      Toolkit.getDefaultToolkit().beep();
    }
    
  }

}
