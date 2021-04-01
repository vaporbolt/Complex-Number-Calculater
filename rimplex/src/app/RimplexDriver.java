package app;

import java.util.ArrayList;
import java.util.Random;
import gui.DisplayComponent;
import gui.GuiContainer;
import gui.InputField;
import math.ComplexNumber;
import math.Operation;

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

        // get input field
        InputField inputField = container.getInputField();

        // insert Complex Numbers
       /* Random r = new Random();
        for (int i = 0; i < 2; i++)
        {
          double a = r.nextInt(100);
          double b = r.nextInt(100);
          complexNumbers.add(new ComplexNumber(a, b));
        }*/
        complexNumbers.add(new ComplexNumber(5, 9));
        complexNumbers.add(new ComplexNumber(1, 3));



        // display Complex Numbers
        for (ComplexNumber num : complexNumbers)
        {
          if (complexNumbers.indexOf(num) == complexNumbers.size() - 1)
          {
            display.addComplexNumber(num);
          }
          else
          {
            display.addComplexNumber(num);
            display.addText(" + ");
          }
        }
        display.addText(" = ");

        // calculate result
        ComplexNumber result = complexNumbers.get(0);
        for (int i = 1; i < complexNumbers.size(); i++)
        {
          result = Operation.add(result, complexNumbers.get(i));
        }

        // display result
        display.addComplexNumber(result);
        display.addText("\n\n");

        complexNumbers = new ArrayList<ComplexNumber>();
        complexNumbers.add(new ComplexNumber(6, 3));
        complexNumbers.add(new ComplexNumber(12, 9));

        
        // display Complex Numbers
        for (ComplexNumber num : complexNumbers)
        {
          if (complexNumbers.indexOf(num) == complexNumbers.size() - 1)
          {
            display.addComplexNumber(num);
          }
          else
          {
            display.addComplexNumber(num);
            display.addText(" - ");
          }
        }
        display.addText(" = ");

        // calculate result
        result = complexNumbers.get(0);
        for (int i = 1; i < complexNumbers.size(); i++)
        {
          result = Operation.subtract(result, complexNumbers.get(i));
        }

        // display result
        display.addComplexNumber(result);
        display.addText("\n\n");

        complexNumbers = new ArrayList<ComplexNumber>();
        complexNumbers.add(new ComplexNumber(4, 2));
        complexNumbers.add(new ComplexNumber(8, 3));
        
        for (ComplexNumber num : complexNumbers)
        {
          if (complexNumbers.indexOf(num) == complexNumbers.size() - 1)
          {
            display.addComplexNumber(num);
          }
          else
          {
            display.addComplexNumber(num);
            display.addText(" * ");
          }
        }
        display.addText(" = ");

        // calculate result
        result = complexNumbers.get(0);
        for (int i = 1; i < complexNumbers.size(); i++)
        {
          result = Operation.multiply(result, complexNumbers.get(i));
        }

        // display result
        display.addComplexNumber(result);
        display.addText("\n\n");

        
        complexNumbers = new ArrayList<ComplexNumber>();
        complexNumbers.add(new ComplexNumber(4, 2));
        complexNumbers.add(new ComplexNumber(8, 3));
        for (ComplexNumber num : complexNumbers)
        {
          if (complexNumbers.indexOf(num) == complexNumbers.size() - 1)
          {
            display.addComplexNumber(num);
          }
          else
          {
            display.addComplexNumber(num);
            display.addText(" / ");
          }
        }
        display.addText(" = ");

        // calculate result
        result = complexNumbers.get(0);
        for (int i = 1; i < complexNumbers.size(); i++)
        {
          result = Operation.divide(result, complexNumbers.get(i));
        }

        // display result
        display.addComplexNumber(result);
        display.addText("\n\n");

        // transfer text from inputField to display after pressing enter
        inputField.enterText(display, display.getText());

        // set all i characters in display to italics
        display.displayTypesetting(0, display.getText().length() - 1);
      }
    });
  }
}
