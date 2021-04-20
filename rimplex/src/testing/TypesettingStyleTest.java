package testing;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import org.junit.jupiter.api.Test;
import gui.TypesettingStyle;

class TypesettingStyleTest
{

  @Test
  void test()
  {
    StyleContext sc = new StyleContext();
    Style italic = sc.addStyle("BLACK", null);
    italic.addAttribute(StyleConstants.Italic, true);
    
    assertEquals(italic.getAttribute(italic), 
        TypesettingStyle.applyTypesetting(true).getAttribute(TypesettingStyle.applyTypesetting(true)));
  }

}
