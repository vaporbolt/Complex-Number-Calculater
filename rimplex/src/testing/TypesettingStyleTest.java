package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import gui.TypesettingStyle;

class TypesettingStyleTest
{

  @Test
  void test()
  {
    assertEquals("<i>i</i>", TypesettingStyle.applyTypesetting("i"));
    assertEquals("1 + 2<i>i</i>", TypesettingStyle.applyTypesetting("1 + 2i"));
  }

}
