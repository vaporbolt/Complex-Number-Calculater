package app;

import java.util.ArrayList;

import gui.DisplayComponent;
import gui.GuiContainer;
import math.ComplexNumber;
import math.Operation;

/**
 * Driver for the Rimplex application.
 * 
 * @author Endre Szakal
 * @version 3/29/2021
 *
 */
public class RimplexDriver
{
  
  public static ArrayList<ComplexNumber> complexNumbers = new ArrayList<ComplexNumber>();

  /**
   * main method.
   * @param args String[]
   */
  public static void main(String[] args)
  {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        // create GUI
        GuiContainer container = GuiContainer.createInstance();
        container.showGUI();
        
        // get display
        DisplayComponent display = container.getDisplay();
        
        // insert Complex Numbers
        ComplexNumber a = new ComplexNumber(5, 10);
        ComplexNumber b = new ComplexNumber(7, 2);
        complexNumbers.add(a);
        complexNumbers.add(b);
        
        // display Complex Numbers
        display.addComplexNumber(complexNumbers.get(0));
        display.addText(" + ");
        display.addComplexNumber(complexNumbers.get(1));
        display.addText(" = ");
        
        // calculate result
        ComplexNumber result = Operation.add(a, b);
        display.addComplexNumber(result);
      
      }
  });
  }
}
