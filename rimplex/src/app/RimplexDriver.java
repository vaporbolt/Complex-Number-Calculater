package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import color.ColorScheme;
import gui.DisplayComponent;
import gui.GuiContainer;
import gui.InputField;
import gui.StepDisplay;
import pictures.RimplexIcon;
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
        // Get ColorScheme from passed color scheme file
        ColorScheme scheme = null;
        RimplexIcon icon;

        try
        {
          BufferedReader in = new BufferedReader(new FileReader(args[0]));
          scheme = ColorScheme.createInstance(in);
        }
        catch (IllegalStateException | IOException e)
        {
          if (e instanceof IllegalStateException)
            throw new IllegalStateException("a scheme already exists!");
        }
        

      

        // create GUI
        GuiContainer container = GuiContainer.createInstance(scheme);
        // Get icon from a passed image file, and set the jframe to it.
        icon = RimplexIcon.createInstance(args[1], container.getJframe());
        container.showGUI();

        // get display
        DisplayComponent display = container.getDisplay();

        // get plane
        CartesianPlane plane = container.getPlane();
        
        // get step window
        StepDisplay steps = container.getSteps();

        // get input field
        InputField inputField = container.getInputField();

        // transfers text from inputField to display upon hitting enter
        inputField.enterText(display, plane, steps);
        
      }
    });
  }

}
