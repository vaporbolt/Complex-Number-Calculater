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
  private final int jframeHeight = 600;
  
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
    frame.getContentPane().setBackground(Color.CYAN);
    frame.setMaximumSize(new Dimension(400, 800));
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
    gbc.weighty = 0.5;
    gbc.insets = new Insets(50,20,50,50);  //top padding
    gbl.setConstraints(area, gbc);
    contentPane.add(area);
    
    // InputField
    JTextField textField = this.inputField.getTextField();
    
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = gbc.REMAINDER;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbl.setConstraints(textField, gbc);  
    contentPane.add(textField);
    
    
    // Reset button
    button = new JButton("R");
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);  
    contentPane.add(button);
    
    
    // clear button
    button = new JButton("C");
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);  
    contentPane.add(button);
    
    // add button
    button = new JButton("+");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);  
    contentPane.add(button);
    
    // subtract button
    button = new JButton("-");
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);  
    contentPane.add(button);
    
    // multiplication button
    button = new JButton("x");
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gbl.setConstraints(button, gbc);  
    contentPane.add(button);
    // divide button
    button = new JButton("÷");
    gbc = new GridBagConstraints();
    gbc.gridx = 5;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gbc.insets = new Insets(5, 10, 10, 5);
    gbl.setConstraints(button, gbc);  
    contentPane.add(button);
    
    // = button
    button = new JButton("=");
    gbc = new GridBagConstraints();
    gbc.gridx = 6;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);  
    contentPane.add(button);
    
    
    
    
    
    

    

  
    
    
    

    
  }

}
