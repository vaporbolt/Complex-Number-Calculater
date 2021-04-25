package printing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

/**
 * @author Seth Roper
 * @version 4/23/21
 *
 */
public class Printer implements Printable
{

  final Component comp;

  /**
   * @param comp
   *          the component to be printed.
   */
  public Printer(final Component comp)
  {

    this.comp = comp;
  }

  @Override
  public int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex)
      throws PrinterException
  {
    if (pageIndex > 0)
    {
      return Printable.NO_SUCH_PAGE;
    }

    // get the bounds of the component
    Dimension dim = comp.getSize();
    double cHeight = dim.getHeight();
    double cWidth = dim.getWidth();

    // get the bounds of the printable area
    double pHeight = pageFormat.getImageableHeight();
    double pWidth = pageFormat.getImageableWidth();

    double pXStart = pageFormat.getImageableX();
    double pYStart = pageFormat.getImageableY();

    double xRatio = pWidth / cWidth;
    double yRatio = pHeight / cHeight;

    Graphics2D g2 = (Graphics2D) graphics;
    g2.translate(pXStart, pYStart);
    g2.scale(xRatio, yRatio);
    comp.printAll(g2);

    return Printable.PAGE_EXISTS;
  }

}
