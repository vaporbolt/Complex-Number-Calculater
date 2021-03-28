package gui;

import java.util.HashMap;
import java.awt.*;

import javax.swing.*;

/**
 * @author Seth Roper
 * @version 3/27/2020
 * 
 * A class that contains all the gui components in a gui layout.
 * a GuiContainer object is a singleton object that creates a window and holds 
 * the JPanel Pane and all components.
 *
 */
@SuppressWarnings("unused")
public class GuiContainer
{
  // keeps track if a GUIContainer exists or not.
  private static boolean exists = false;
  
  private final int jframeWidth = 400;
  private final int jframeHeight = 400;
  
  // holds the frame
  private JFrame frame = new JFrame("Rimplex");
  // holds all of the buttons(add subtract reset etc.)
  private HashMap<String, JButton> buttons = new HashMap<String, JButton>();
  
  private InputField inputField = InputField.createInstance();
  

  /**
   * creates the GUI container object with the proper gridbagLayout.
   */
  private GuiContainer()
  {
    this.addComponetsToPane();
  }
  
  
  /**
   * creates a GuiContainer object if one doesn't already exist, or throws an IllegalStateException
   * otherwise.
   * @return a GUI container containing all of the gui componets.
   */
  public static GuiContainer createInstance()
  {
    if(exists)
    {
      throw new IllegalStateException("GuiContainer already exists");
    }
    
    else
    {
      exists = true;
      return new GuiContainer();
    }
  }
  
  /**
   * @return the jframe for the gui.
   */
  public JFrame getJframe()
  {
    return this.frame;
  }
  
  
  /**
   * sets the gui to visible for display.
   */
  public void showGUI()
  {
    frame.setMaximumSize(new Dimension(400, 400));
    frame.setSize(this.jframeWidth, this.jframeHeight);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  /**
   * helper method adds the componets to the pane.
   */
  @SuppressWarnings("static-access")
  private void addComponetsToPane()
  {
    
    Container contentPane = frame.getContentPane();
    GridBagLayout gbl = new GridBagLayout();
    contentPane.setLayout(gbl);
    GridBagConstraints gbc = new GridBagConstraints();
    JButton button;
    
    // Display
    TextArea area = new TextArea();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = gbc.REMAINDER;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 1;
    gbc.weighty = 1;
    area.setPreferredSize(new Dimension(400,200));
    area.setMinimumSize(new Dimension(400, 100));
    gbc.insets = new Insets(50,20,50,50);  //top padding
    gbl.setConstraints(area, gbc);
    contentPane.add(area);
    
    /* InputField
    JTextField textField = this.inputField.getTextField();
    
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.ipadx = 400;
    gbc.ipady = 20;
    gbl.setConstraints(textField, gbc);  area.setPreferredSize(new Dimension(400, 20));
    textField.setMinimumSize(new Dimension(400, 20));
    contentPane.add(textField);
    /*
    
    /*resetButton
    button = new JButton("R");
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(100,20,0,20);  //top padding
    gbl.setConstraints(button, gbc);
    contentPane.add(button);
    /*
    
    /*
    gbc = new GridBagConstraints();
    button = new JButton("test 2"); 
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0.4;
    gbc.weighty = 1;
    gbc.anchor = GridBagConstraints.PAGE_END; //bottom of space
    gbc.insets = new Insets(100,0,0,0);  //top padding
    gbl.setConstraints(button, gbc);
    contentPane.add(button);
    */
  
    
    /* output Field
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 7;
    contentPane.add(new TextArea(), c);
    */
    
    

    
  }

}
