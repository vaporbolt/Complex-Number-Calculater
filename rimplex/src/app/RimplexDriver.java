package app;

import gui.DisplayComponent;
import gui.GuiContainer;
import gui.InputField;
import visualization.CartesianPlane;

/**
 * Driver for the Rimplex application.
 * 
 * @author Endre Szakal, Sebastien Roy
 * @version 3/29/2021
 *
 */
public class RimplexDriver
{

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
        // bruh
        
        // create GUI
        GuiContainer container = GuiContainer.createInstance();
        container.showGUI();

        // get display
        DisplayComponent display = container.getDisplay();
        
        // get plane
        CartesianPlane plane = container.getPlane();

        // get input field
        InputField inputField = container.getInputField();

        // transfers text from inputField to display upon hitting enter
        inputField.enterText(display, plane);
      }
    });
  }
  
}
