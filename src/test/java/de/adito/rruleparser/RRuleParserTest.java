package de.adito.rruleparser;

import de.adito.rruleparser.tokenizer.exception.RRuleTokenizeException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RRuleParserTest
{
  @Test
  @DisplayName("Test the parser from the entry point")
  void testGeneralFunctionality() throws RRuleTokenizeException
  {
    // Creates a rrule parser with english translation
    RRuleParser ruleParser = new RRuleParser();

    String parseResult = ruleParser.parseRRule("FREQ=MONTHLY;BYSETPOS=4;BYDAY=SU;INTERVAL=5");

    assertEquals("Every 5 months on fourth sunday", parseResult);

  }
}