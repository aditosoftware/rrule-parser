package de.adito.rruleparser.tokenizer;

import de.adito.rruleparser.tokenizer.exception.RRuleTokenizeException;
import de.adito.rruleparser.tokenizer.validation.RRuleValidator;
import de.adito.rruleparser.tokenizer.value.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

  @ParameterizedTest
  @ValueSource(strings = {
      "FREQ=WEEKLY;BYDAY=MO,TU",
      "FREQ=YEARLY;BYMONTHDAY=1",
      "FREQ=YEARLY;BYSETPOS=1",
      "FREQ=YEARLY;COUNT=1",
      "FREQ=YEARLY;INTERVAL=1",
      "FREQ=YEARLY;UNTIL=20181023T220000Z"
  })
  @DisplayName("Test toString method")
  void testToStringMethod(String input) throws RRuleTokenizeException
  {
    IRRuleTokenContainer container = tokenizer.tokenize(input);
    assertEquals(input, container.toString());
  }

  @Test
  void testMergeSimple() throws RRuleTokenizeException
  {
    IRRuleTokenContainer firstPart = tokenizer.tokenize("FREQ=WEEKLY;INTERVAL=1");
    IRRuleTokenContainer secondPart = tokenizer.tokenize("FREQ=YEARLY;INTERVAL=1;BYMONTHDAY=1;BYSETPOS=1");

    // Merge secondPart into firstPart
    firstPart.merge(secondPart);

    assertNotNull(firstPart.getFreq());
    assertNotNull(firstPart.getByMontDay());
    assertNotNull(firstPart.getBySetPos());
    assertNotNull(firstPart.getByMontDay());

    assertEquals(secondPart.getByMontDay(), firstPart.getByMontDay());
    assertEquals(secondPart.getBySetPos(), firstPart.getBySetPos());
  }

  @Test
  void testMergeDoNotOverride() throws RRuleTokenizeException
  {
    IRRuleTokenContainer firstPart = tokenizer.tokenize("FREQ=WEEKLY;INTERVAL=1");
    IRRuleTokenContainer secondPart = tokenizer.tokenize("FREQ=YEARLY;INTERVAL=1;BYMONTHDAY=1;BYSETPOS=1");

    // Merge secondPart into firstPart
    firstPart.merge(secondPart);

    assertNotNull(firstPart.getFreq());
    assertNotEquals(secondPart.getFreq(), firstPart.getFreq());
    assertNotEquals(secondPart.getInterval(), firstPart.getInterval());
  }
}