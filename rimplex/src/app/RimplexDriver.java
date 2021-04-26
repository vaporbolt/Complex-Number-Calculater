package app;

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
  public static void main(final String[] args)
  {
    javax.swing.SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        ColorScheme scheme = null;
        RimplexIcon icon = null;
        
        // Create ColorScheme
        try
        {
          scheme = new ColorScheme();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }

        // create GUI
        GuiContainer container = GuiContainer.createInstance(scheme);
        
        // Create icon and add it to the JFrame.
        try
        {
          icon = new RimplexIcon();
          icon.addIcon(container.getJframe());
        }
        catch (IllegalStateException e)
        {
          e.printStackTrace();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
        
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
