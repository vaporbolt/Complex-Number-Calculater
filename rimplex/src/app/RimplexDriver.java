package app;

import java.util.ArrayList;
import gui.DisplayComponent;
import gui.GuiContainer;
import gui.InputField;
import math.ComplexNumber;
import math.OperationType;

/**
 * Driver for the Rimplex application.
 * 
 * @author Endre Szakal, Sebastien Roy
 * @version 3/29/2021
 *
 */
public class RimplexDriver
{
  // keeps track of ComplexNumber numbers
  public static ArrayList<ComplexNumber> complexNumbers = new ArrayList<ComplexNumber>();
  
  // keeps track of Operations
  public static ArrayList<OperationType> operations = new ArrayList<OperationType>();

  /**
   * main method.
   * 
   * @param args
   *          String[]
   */
  public static void main(String[] args)
  {
    javax.swing.SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        // create GUI
        GuiContainer container = GuiContainer.createInstance();
        container.showGUI();

        // get display
        DisplayComponent display = container.getDisplay();

        // get input field
        InputField inputField = container.getInputField();

        // transfers text from inputField to display upon hitting enter
        inputField.enterText(display, complexNumbers);
        
        // set all i characters in display to italics
        display.displayTypesetting(0, display.getText().length() - 1);
      }
    });
  }
  
}
