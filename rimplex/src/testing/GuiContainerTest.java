package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gui.GuiContainer;

class GuiContainerTest
{

  public static void main(String[] args) {
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            GuiContainer container = GuiContainer.createInstance();
            container.showGUI();
        }
    });
    
    
  }

}
