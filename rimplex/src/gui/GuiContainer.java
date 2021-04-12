package gui;

import java.util.HashMap;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import app.RimplexDriver;
import math.ComplexNumber;
import visualization.CartesianPlane;

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
  
  private final int jframeWidth = 1000;
  private final int jframeHeight = 400;
  
  // holds the frame
  private JFrame frame = new JFrame("Rimplex");
  
  // holds all of the buttons(add subtract reset etc.)
  //private HashMap<String, JButton> buttons = new HashMap<String, JButton>();
  
  private InputField inputField = InputField.createInstance();

  private DisplayComponent display;

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
   * gets the GUI's display.
   * @return DisplayComponent
   */
  public DisplayComponent getDisplay()
  {
    return this.display;
  }
  
  /**
   * gets GUI's inputfield.
   * @return InputField
   */
  public InputField getInputField()
  {
    return this.inputField;
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
    frame.setMaximumSize(new Dimension(400, 1000));
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

    
    // InputField/ display.
    JTextPane textField = this.inputField.getTextField();
    
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridwidth = gbc.REMAINDER - 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(40, 0, 0, 0);
    gbl.setConstraints(textField, gbc);
    contentPane.add(textField);
    
    // Display
    display = DisplayComponent.createInstance();
    
    // create scroll pane for the display/history and set a restricting size
    JScrollPane scrollDisplay = new JScrollPane(display.getPanel());
    scrollDisplay.setPreferredSize(new Dimension(200, 200));
    
    gbc = new GridBagConstraints();
    gbc.gridx = 10;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 5;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.ipadx = 200;
    gbc.ipady = 200;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gbc.insets = new Insets(0, 100, 10, 10);
    gbl.setConstraints(scrollDisplay, gbc);  
    contentPane.add(scrollDisplay);
    
    // Cartesian Plane
    CartesianPlane plane = new CartesianPlane();
    plane.addPoint(new ComplexNumber(4, 5));
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 5;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.ipadx = 200;
    gbc.ipady = 200;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(0, 5, 10, 100);
    gbl.setConstraints(plane, gbc);  
    contentPane.add(plane);
    
    
    
    // inverse button
    button = new JButton("inv");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener((ActionListener) new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        // TODO Auto-generated method stub
        textField.setText(textField.getText() + " ^-1 ");
        inputField.inputTypesetting(0, textField.getText().length());
      }
      
    });
    contentPane.add(button);
    
    // Clear Button
    button = new JButton("C");
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new ClearHandler(inputField));
    contentPane.add(button);
    
    // Addition Button
    button = new JButton("+");
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new AdditionHandler(inputField, RimplexDriver.operations));
    contentPane.add(button);
    
    // subtraction Button
    button = new JButton("-");
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new SubtractionHandler(inputField, RimplexDriver.operations));
    contentPane.add(button);
    
    // reset button
    button = new JButton("R");
    gbc = new GridBagConstraints();
    gbc.gridx = 5;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new ResetHandler(display, inputField, RimplexDriver.complexNumbers, RimplexDriver.operations));
    contentPane.add(button);
    
    // open parentheses button
    button = new JButton("(");
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener((ActionListener) new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        // TODO Auto-generated method stub
        textField.setText(textField.getText() + "(");
        inputField.inputTypesetting(0, textField.getText().length());
      }
      
    });
    contentPane.add(button);
    
    // closed parentheses
    button = new JButton(")");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener((ActionListener) new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        // TODO Auto-generated method stub
        textField.setText(textField.getText() + ")");
        inputField.inputTypesetting(0, textField.getText().length());
      }
      
    });
    contentPane.add(button);
    
    // multiplication symbol
    button = new JButton("x");
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new MultiplicationHandler(inputField, RimplexDriver.operations));
    contentPane.add(button);
    
    // division symbol.
    button = new JButton("�");
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new DivisionHandler(inputField, RimplexDriver.operations));
    contentPane.add(button);
    
    // equals button
    button = new JButton("=");
    gbc = new GridBagConstraints();
    gbc.gridx = 5;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new EqualsHandler(display, inputField, RimplexDriver.complexNumbers, RimplexDriver.operations));
    contentPane.add(button);
    
    // complex number plane button
    button = new JButton("<");
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);  
    contentPane.add(button);
    
    // i button
    button = new JButton("i");
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);  
    button.addActionListener((ActionListener) new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        // TODO Auto-generated method stub
        textField.setText(textField.getText() + "i");
        inputField.inputTypesetting(0, textField.getText().length());
      }
      
    });
    contentPane.add(button);
    
    // history button
    button = new JButton(">");
    gbc = new GridBagConstraints();
    gbc.gridx = 5;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);  
    contentPane.add(button);
    

    // 0 button 
    button = new JButton("0");
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc); 
    button.addActionListener(new NumActionHandler(inputField, 0));
    contentPane.add(button);
    
    // 1 button
    button = new JButton("1");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);  
    button.addActionListener(new NumActionHandler(inputField, 1));
    contentPane.add(button);
    
    // 2 button
    button = new JButton("2");
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new NumActionHandler(inputField, 2));
    contentPane.add(button);
    // 3 button
    button = new JButton("3");
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);  
    button.addActionListener(new NumActionHandler(inputField, 3));
    contentPane.add(button);
    
    
    // 4 button
    button = new JButton("4");
    gbc = new GridBagConstraints();
    gbc.gridx = 5;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc); 
    button.addActionListener(new NumActionHandler(inputField, 4));
    contentPane.add(button);
    
    // 5 button
    button = new JButton("5");
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 5;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);  
    button.addActionListener(new NumActionHandler(inputField, 5));
    contentPane.add(button);
    
    // 6 button
    button = new JButton("6");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 5;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new NumActionHandler(inputField, 6));
    contentPane.add(button);
    
    // 7 button
    button = new JButton("7");
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 5;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new NumActionHandler(inputField, 7));
    contentPane.add(button);
    
    // 8 button
    button = new JButton("8");
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    gbc.gridy = 5;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new NumActionHandler(inputField, 8));
    contentPane.add(button);
    
    // 9 button
    button = new JButton("9");
    gbc = new GridBagConstraints();
    gbc.gridx = 5;
    gbc.gridy = 5;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc); 
    button.addActionListener(new NumActionHandler(inputField, 9));
    contentPane.add(button);
    
    
    
    

    
  }
  


}
