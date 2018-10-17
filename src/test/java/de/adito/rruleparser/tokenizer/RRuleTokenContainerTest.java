package de.adito.rruleparser.tokenizer;

import de.adito.rruleparser.tokenizer.exception.RRuleTokenizeException;
import de.adito.rruleparser.tokenizer.validation.RRuleValidator;
import de.adito.rruleparser.tokenizer.value.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class RRuleTokenContainerTest
{
  private static IValueParser valueParser = new RRuleValueParser();
  private static IRRuleTokenizer tokenizer = new RRuleTokenizer(valueParser, new RRuleValidator());

  @Test
  @DisplayName("Test a simple example")
  void testSimpleContainer() throws RRuleTokenizeException
  {
    IRRuleTokenContainer tokenContainer = tokenizer.tokenize("FREQ=DAILY;INTERVAL=1");

    assertEquals(2, tokenContainer.ruleCount());
  }

  @Test
  @DisplayName("Test a complex example")
  void testComplexContainer() throws RRuleTokenizeException
  {
    IRRuleTokenContainer tokenContainer = tokenizer.tokenize("FREQ=YEARLY;BYDAY=TU;BYSETPOS=1;BYMONTH=4;UNTIL=20181023T220000Z");

    assertEquals(5, tokenContainer.ruleCount());
  }

  @Test
  @DisplayName("Test equals method with same container")
  void testEqualsMethodSameContainer() throws RRuleTokenizeException
  {
    IRRuleTokenContainer firstPart = tokenizer.tokenize("FREQ=DAILY;INTERVAL=1");
    assertEquals(firstPart, firstPart);
  }

  @Test
  @DisplayName("Test equals method with swapped container")
  void testEqualsMethodSwapped() throws RRuleTokenizeException
  {
    IRRuleTokenContainer firstPart = tokenizer.tokenize("FREQ=DAILY;INTERVAL=1");
    IRRuleTokenContainer secondPart = tokenizer.tokenize("INTERVAL=1;FREQ=DAILY");

    assertEquals(firstPart, secondPart);
  }

  @Test
  @DisplayName("Test equals method with different sized container")
  void testEqualsMethodComplex() throws RRuleTokenizeException
  {
    IRRuleTokenContainer firstPart = tokenizer.tokenize("FREQ=DAILY;INTERVAL=1");
    IRRuleTokenContainer secondPart = tokenizer.tokenize("INTERVAL=1;FREQ=DAILY;BYSETPOS=1");

    assertNotEquals(firstPart, secondPart);
  }

  @Test
  @DisplayName("Test equals method with large swapped container")
  void testEqualsMethodLargeSwapped() throws RRuleTokenizeException
  {
    IRRuleTokenContainer firstPart = tokenizer.tokenize("BYSETPOS=1;FREQ=DAILY;INTERVAL=1;BYMONTHDAY=1");
    IRRuleTokenContainer secondPart = tokenizer.tokenize("INTERVAL=1;BYMONTHDAY=1;FREQ=DAILY;BYSETPOS=1");

    assertEquals(firstPart, secondPart);
  }

  @Test
  @DisplayName("Test equals method with different values")
  void testEqualsMethodDifferentValues() throws RRuleTokenizeException
  {
    IRRuleTokenContainer firstPart = tokenizer.tokenize("BYSETPOS=1;FREQ=WEEKLY;INTERVAL=1;BYMONTHDAY=1");
    IRRuleTokenContainer secondPart = tokenizer.tokenize("INTERVAL=1;BYMONTHDAY=1;FREQ=YEARLY;BYSETPOS=1");

    assertNotEquals(firstPart, secondPart);
  }
}