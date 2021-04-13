package gui;

import java.util.HashMap;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
  
  private final int jframeWidth = 450;
  private final int jframeHeight = 450;
  
  // holds the frame
  private JFrame frame = new JFrame("Rimplex");
  
  // holds all of the buttons(add subtract reset etc.)
  //private HashMap<String, JButton> buttons = new HashMap<String, JButton>();
  
  private InputField inputField = InputField.createInstance();

  private DisplayComponent display;
  
  private CartesianPlane plane;
  
  private JTextPane block = new JTextPane();
  
  private JWindow historyWindow;
  
  private JWindow planeWindow;

  /**
   * creates the GUI container object with the proper gridbagLayout.
   */
  private GuiContainer()
  {
    this.createPlaneWindow();
    this.createHistoryWindow();
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
   * gets the GUI's plane.
   * @return CartesianPlane
   */
  public CartesianPlane getPlane()
  {
    return this.plane;
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
   * @return the block for the gui.
   */
  public JTextPane getBlock()
  {
    return this.block;
  }
  
  /**
   * Sets the style for the buttons
   * 
   * @param button a button
   */
  private void setButton(JButton button)
  {
    button.setFocusPainted(false);
    button.setFont(new Font("TimesRoman", Font.PLAIN, 16));
    button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    button.setContentAreaFilled(false);
  }
  
  /**
   * sets the gui to visible for display.
   */
  public void showGUI()
  {
    frame.getContentPane().setBackground(Color.LIGHT_GRAY);
    frame.setMaximumSize(new Dimension(400, 1000));
    frame.setSize(this.jframeWidth, this.jframeHeight);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false); 
    frame.setLocation(500, 200);
    frame.addComponentListener(new FrameListener(historyWindow, planeWindow));
    this.historyWindow.getContentPane().setBackground(new Color(199, 238, 255));
    this.historyWindow.setSize(this.jframeWidth / 2 + 100, this.jframeHeight - 180);
    this.historyWindow.setVisible(true);
    this.historyWindow.setLocation(frame.getX() + 200, frame.getY() + 165);
    this.historyWindow.setVisible(false);
    this.historyWindow.setAlwaysOnTop(true);
    this.planeWindow.getContentPane().setBackground(new Color(199, 238, 255));
    this.planeWindow.setSize(this.jframeWidth / 2 + 100, this.jframeHeight - 180);
    this.planeWindow.setVisible(true);
    this.planeWindow.setLocation(frame.getX() - 275, frame.getY() + 165);
    this.planeWindow.setVisible(false);
    this.planeWindow.setAlwaysOnTop(true);
    frame.addWindowStateListener(new WindowStateListener() {
      @Override
      public void windowStateChanged(WindowEvent e)
      {
        historyWindow.setVisible(false);
        planeWindow.setVisible(false);
        
      }
   });
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
    
    // rimplex icon
    ImageIcon logo = new ImageIcon(this.getClass().getResource("/pictures/logoRimplex.png"));
    JLabel label = new JLabel(logo);
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.gridwidth = gbc.REMAINDER - 3;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(0, 0, 0, 90);
    gbl.setConstraints(label, gbc);
    contentPane.add(label);
    
    
    
    // InputField/ display.
    JTextPane textField = this.inputField.getTextField();
    
    gbc.gridx = 2;
    gbc.gridy = 1;
    gbc.gridwidth = gbc.REMAINDER - 4;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(40, 0, 0, 0);
    gbl.setConstraints(textField, gbc);
    contentPane.add(textField);
    
    // creates blocking text area
    block.setBackground(Color.LIGHT_GRAY);
    block.setEditable(false);
    
    gbc = new GridBagConstraints();
    gbc.gridx = 10;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 5;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.ipadx = 250;
    gbc.ipady = 200;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gbc.insets = new Insets(0, 100, 10, 10);
    gbl.setConstraints(block, gbc);  
    contentPane.add(block);
    
    // Display
    /*
    display = DisplayComponent.createInstance();
    
    // create scroll pane for the display/history and set a restricting size
    JScrollPane scrollDisplay = new JScrollPane(display.getPanel());
    scrollDisplay.setPreferredSize(new Dimension(200, 200));
    
    gbc = new GridBagConstraints();
    gbc.gridx = 10;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 5;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.ipadx = 200;
    gbc.ipady = 200;
    gbc.weightx = 0;
    gbc.weighty = 1;
    gbc.insets = new Insets(0, 100, 10, 50);
    gbl.setConstraints(scrollDisplay, gbc);  
    contentPane.add(scrollDisplay);
    */
    
    // Cartesian Plane
    CartesianPlane plane2 = new CartesianPlane();
    //plane.addPoint(new ComplexNumber(4, 5));
    JScrollPane scrollPlane = new JScrollPane(plane2);
    scrollPlane.setPreferredSize(new Dimension(200, 200));
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 5;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.ipadx = 200;
    gbc.ipady = 200;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(0, 50, 10, 0);
    gbl.setConstraints(scrollPlane, gbc);  
    contentPane.add(scrollPlane);
    
    
    
    // inverse button
    button = new JButton("inv");
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
    setButton(button);
    button.addActionListener((ActionListener) new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        
        textField.setText(textField.getText() + " ^-1 ");
        inputField.inputTypesetting(0, textField.getText().length());
      }
      
    });
    contentPane.add(button);
    
    // Clear Button
    button = new JButton("C");
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
    button.addActionListener(new ClearHandler(inputField));
    setButton(button);
    contentPane.add(button);
    
    // Addition Button
    button = new JButton("+");
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
    button.addActionListener(new AdditionHandler(inputField, RimplexDriver.operations));
    setButton(button);
    contentPane.add(button);
    
    // subtraction Button
    button = new JButton("-");
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
    button.addActionListener(new SubtractionHandler(inputField, RimplexDriver.operations));
    setButton(button);
    contentPane.add(button);
    
    // reset button
    button = new JButton("R");
    gbc = new GridBagConstraints();
    gbc.gridx = 6;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new ResetHandler(display, inputField, RimplexDriver.complexNumbers, RimplexDriver.operations, plane, block));
    setButton(button);
    contentPane.add(button);
    
    // open parentheses button
    button = new JButton("(");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
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
        
        textField.setText(textField.getText() + "(");
        inputField.inputTypesetting(0, textField.getText().length());
      }
      
    });
    setButton(button);
    contentPane.add(button);
    
    // closed parentheses
    button = new JButton(")");
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
        
        textField.setText(textField.getText() + ")");
        inputField.inputTypesetting(0, textField.getText().length());
      }
      
    });
    setButton(button);
    contentPane.add(button);
    
    // multiplication symbol
    button = new JButton("x");
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new MultiplicationHandler(inputField, RimplexDriver.operations));
    setButton(button);
    contentPane.add(button);
    
    // division symbol.
    button = new JButton("÷");
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
    button.addActionListener(new DivisionHandler(inputField, RimplexDriver.operations));
    setButton(button);
    contentPane.add(button);
    
    // equals button
    button = new JButton("=");
    gbc = new GridBagConstraints();
    gbc.gridx = 6;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new EqualsHandler(display, inputField, RimplexDriver.complexNumbers, RimplexDriver.operations, plane, block));
    setButton(button);
    contentPane.add(button);
    
    // complex number plane button
    button = new JButton("<");
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 50, 10, 25);
    gbl.setConstraints(button, gbc); 
    button.addActionListener((ActionListener) new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        if (!planeWindow.isVisible())
        {
          planeWindow.setVisible(true);
        }
      }
      
    });
    setButton(button);
    contentPane.add(button);
    
    // conjugate button
    button = new JButton("z*");
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
    button.addActionListener((ActionListener) new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        
        textField.setText(textField.getText() + " con(");
        inputField.inputTypesetting(0, textField.getText().length());
      }
      
    });
    setButton(button);
    contentPane.add(button);
    
    // exponet button
    button = new JButton("x²");
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
    button.addActionListener((ActionListener) new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        
        textField.setText(textField.getText() + "^");
        inputField.inputTypesetting(0, textField.getText().length());
      }
      
    });
    setButton(button);
    contentPane.add(button);


    // i button
    button = new JButton("i");
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
    button.addActionListener((ActionListener) new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        
        textField.setText(textField.getText() + "i");
        inputField.inputTypesetting(0, textField.getText().length());
      }
      
    });
    setButton(button);
    button.setFont(new Font("TimesRoman", Font.ITALIC, 16));
    contentPane.add(button);
    
    // history button
    button = new JButton(">");
    gbc = new GridBagConstraints();
    gbc.gridx = 7;
    gbc.gridy = 4;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 50, 10, 5);
    gbl.setConstraints(button, gbc);  
    button.addActionListener((ActionListener) new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        if (!historyWindow.isVisible())
        {
          int w = 320;
          historyWindow.setSize(0, historyWindow.getHeight());
          historyWindow.setVisible(true);
          
          Timer timer = new Timer(1, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              if (historyWindow.getWidth() < w)
              {
                historyWindow.setSize(historyWindow.getWidth() + 8, historyWindow.getHeight());
              } else 
              {
                ((Timer)evt.getSource()).stop();
              }
            }
          });
          timer.start();

        }
      }
      
    });
    setButton(button);
    contentPane.add(button);
    

    // 0 button 
    button = new JButton("0");
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
    button.addActionListener(new NumActionHandler(inputField, 0));
    button.setFocusPainted(false);
    button.setContentAreaFilled(false);
    contentPane.add(button);
    
    // 1 button
    button = new JButton("1");
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
    button.addActionListener(new NumActionHandler(inputField, 1));
    button.setFocusPainted(false);
    button.setContentAreaFilled(false);
    contentPane.add(button);
    
    // 2 button
    button = new JButton("2");
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
    button.addActionListener(new NumActionHandler(inputField, 2));
    button.setFocusPainted(false);
    button.setContentAreaFilled(false);
    contentPane.add(button);
    // 3 button
    button = new JButton("3");
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
    button.addActionListener(new NumActionHandler(inputField, 3));
    button.setFocusPainted(false);
    button.setContentAreaFilled(false);
    contentPane.add(button);
    
    // 4 button
    button = new JButton("4");
    gbc = new GridBagConstraints();
    gbc.gridx = 6;
    gbc.gridy = 5;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc); 
    button.addActionListener(new NumActionHandler(inputField, 4));
    button.setFocusPainted(false);
    button.setContentAreaFilled(false);
    contentPane.add(button);

    // 5 button
    button = new JButton("5");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 6;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);  
    button.addActionListener(new NumActionHandler(inputField, 5));
    button.setFocusPainted(false);
    button.setContentAreaFilled(false);
    contentPane.add(button);
    
    // 6 button
    button = new JButton("6");
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 6;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new NumActionHandler(inputField, 6));
    button.setFocusPainted(false);
    button.setContentAreaFilled(false);
    contentPane.add(button);
    
    // 7 button
    button = new JButton("7");
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    gbc.gridy = 6;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new NumActionHandler(inputField, 7));
    button.setFocusPainted(false);
    button.setContentAreaFilled(false);
    contentPane.add(button);
    
    // 8 button
    button = new JButton("8");
    gbc = new GridBagConstraints();
    gbc.gridx = 5;
    gbc.gridy = 6;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc);
    button.addActionListener(new NumActionHandler(inputField, 8));
    button.setFocusPainted(false);
    button.setContentAreaFilled(false);
    contentPane.add(button);
    
    // 9 button
    button = new JButton("9");
    gbc = new GridBagConstraints();
    gbc.gridx = 6;
    gbc.gridy = 6;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.insets = new Insets(10, 5, 10, 5);
    gbl.setConstraints(button, gbc); 
    button.addActionListener(new NumActionHandler(inputField, 9));
    button.setFocusPainted(false);
    button.setContentAreaFilled(false);
    contentPane.add(button);
    
    // decimal button
    button = new JButton(".");
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 7;
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
        
        textField.setText(textField.getText() + ".");
        inputField.inputTypesetting(0, textField.getText().length());
      }
      
    });
    setButton(button);
    contentPane.add(button);
    
    // back space button
    button = new JButton("<-");
    gbc = new GridBagConstraints();
    gbc.gridx = 6;
    gbc.gridy = 4;
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
        
        String s =  "";
        if (textField.getText().length() != 0) s = textField.getText().substring(0, textField.getText().length() - 1);
        textField.setText(s);
        inputField.inputTypesetting(0, textField.getText().length());
      }
      
    });
    setButton(button);
    contentPane.add(button);
    
    
    // sign button
    button = new JButton("+/-");
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
    button.addActionListener(new SignHandler(inputField));
    setButton(button);
    contentPane.add(button);
    
    

    
  }
  
  
  /**
   * creates a JWindow containing the history componet and a button that makes it invisible.
   */
  private void createHistoryWindow()
  {
    this.historyWindow = new JWindow();
    Container contentPane = this.historyWindow.getContentPane();
    GridBagLayout gbl = new GridBagLayout();
    contentPane.setLayout(gbl);
    GridBagConstraints gbc = new GridBagConstraints();
    JButton button;
    // Display
    display = DisplayComponent.createInstance();
    // create scroll pane for the display/history and set a restricting size
    JScrollPane scrollDisplay = new JScrollPane(display.getPanel());
    scrollDisplay.setViewportBorder(null);
    scrollDisplay.setBorder(null);
    scrollDisplay.setPreferredSize(new Dimension(200, 200));
    
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.ipadx = 200;
    gbc.ipady = 200;
    gbc.weightx = 0.9;
    gbc.weighty = 1;
    gbl.setConstraints(scrollDisplay, gbc);  
    contentPane.add(scrollDisplay);
    
    button = new JButton("<");
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0.1;
    gbc.weighty = 0;
    gbl.setConstraints(button, gbc); 
    button.setBackground(new Color(199, 238, 255));
    button.addActionListener((ActionListener) new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        if (historyWindow.isVisible())
        {
          historyWindow.setSize(320, historyWindow.getHeight());
          Timer timer = new Timer(1, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              if (historyWindow.getWidth() > 0)
              {
                historyWindow.setSize(historyWindow.getWidth() - 8, historyWindow.getHeight());
              } else 
              {
                historyWindow.setVisible(false);
                ((Timer)evt.getSource()).stop();
              }
            }
          });
          timer.start();
        }
      }
      
    });
    contentPane.add(button);
    
    
  }
  
  /**
   * creates a window to hold the complex number plane.
   */
  private void createPlaneWindow()
  {
    this.planeWindow = new JWindow();
    Container contentPane = this.planeWindow.getContentPane();
    GridBagLayout gbl = new GridBagLayout();
    contentPane.setLayout(gbl);
    GridBagConstraints gbc = new GridBagConstraints();
    JButton button;
    // Cartesian Plane
    plane = new CartesianPlane();
    JScrollPane scrollPlane = new JScrollPane(plane);
    scrollPlane.setPreferredSize(new Dimension(200, 200));
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 5;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.ipadx = 200;
    gbc.ipady = 200;
    gbc.weightx = .9;
    gbc.weighty = 0;
    gbl.setConstraints(scrollPlane, gbc);  
    contentPane.add(scrollPlane);
    
    button = new JButton(">");
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.weightx = 0.1;
    gbc.weighty = 0;
    gbc.insets = new Insets(90, 0, 0, 0);
    gbl.setConstraints(button, gbc); 
    button.setBackground(new Color(199, 238, 255));
    button.addActionListener((ActionListener) new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        if (planeWindow.isVisible())
        {
          planeWindow.setVisible(false);
        }
      }
      
    });
    contentPane.add(button);
  }


}

