package de.adito.rruleparser.tokenizer;

import de.adito.rruleparser.tokenizer.exception.RRuleTokenizeException;
import de.adito.rruleparser.tokenizer.validation.RRuleValidator;
import de.adito.rruleparser.tokenizer.value.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}