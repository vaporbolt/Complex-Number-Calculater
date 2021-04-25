package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import printing.Printer;

/**
 * @author Seth Roper
 * @version 4/23/21
 * A listener for the printHistory button that opens a print dialogue for the history.
 *
 */
public class HistoryPrinterHandler implements ActionListener
{
  
  private Component comp;
  
  /**
   * @param comp the component to be printed.
   */
  public HistoryPrinterHandler(final Component comp)
  {
    this.comp = comp;
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    PrinterJob pjob = PrinterJob.getPrinterJob();
    PageFormat preformat = pjob.defaultPage();
    preformat.setOrientation(PageFormat.LANDSCAPE);
    PageFormat postformat = pjob.pageDialog(preformat);
    // If user does not hit cancel then print.
    if (preformat != postformat)
    {
      // Set print component
      pjob.setPrintable(new Printer(this.comp), postformat);
      if (pjob.printDialog())
      {
        try
        {
          pjob.print();
        }
        catch (PrinterException e1)
        {
          // printing failed.
          e1.printStackTrace();
        }
      }
    }
  }

}
