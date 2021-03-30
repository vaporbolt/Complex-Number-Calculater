package app;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.BadLocationException;

import gui.DisplayComponent;
import gui.GuiContainer;
import gui.TypesettingStyle;
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
  // keeps track of ComplexNumber numbers
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
        Random r = new Random();
        for (int i = 0; i < r.nextInt(20) + 2; i++)
        {
          double a = r.nextInt(100);
          double b = r.nextInt(100);
          complexNumbers.add(new ComplexNumber(a, b));
        }
        
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
            display.addText(" / ");
          }
        }
        display.addText(" = ");
        
        // calculate result
        ComplexNumber result = complexNumbers.get(0);
        for (int i = 1; i < complexNumbers.size(); i++)
        {
          result = Operation.divide(result, complexNumbers.get(i));
        }
        
        // display result
        display.addComplexNumber(result);
        try
        {
          String s = display.getPanel().getDocument().getText(0, display.getPanel().getDocument().getLength());
          int length = 0;
          while (s.contains("i"))
          {
            display.getPanel().getDocument().remove(s.indexOf("i") + length, 1);
            display.getPanel().getDocument().insertString(s.indexOf("i") + length, "i", TypesettingStyle.applyTypesetting());
            length += s.substring(0, s.indexOf("i") + 1).length();
            s = s.substring(s.indexOf("i") + 1);
          }
        }
        catch (BadLocationException e)
        {
          
        }
      }
  });
  }
}
