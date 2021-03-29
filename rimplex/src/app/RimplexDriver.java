package app;

import java.util.ArrayList;

import gui.DisplayComponent;
import gui.GuiContainer;
import math.ComplexNumber;

/**
 * Driver for the Rimplex application.
 * 
 * @author Endre Szakal
 * @version 3/29/2021
 *
 */
public class RimplexDriver
{
  
  public static ArrayList<ComplexNumber> complexNumbers;

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
        
        // insert Complex Numbers
        DisplayComponent display = container.getDisplay();
        display.addComplexNumber(new ComplexNumber(5, 10));
      }
  });
  }
}
