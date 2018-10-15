package de.adito.rruleparser.tokenizer;

import de.adito.rruleparser.tokenizer.exception.RRuleTokenizeException;
import de.adito.rruleparser.tokenizer.token.FreqToken;
import de.adito.rruleparser.tokenizer.validation.*;
import de.adito.rruleparser.tokenizer.value.*;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;


class RRuleTokenizerTest
{
  private static IValueParser valueParser = new RRuleValueParser();
  private static IRRuleValidator rruleValidator = new RRuleValidator();
  private static IRRuleTokenizer tokenizer = new RRuleTokenizer(valueParser, rruleValidator);

  @Test
  void testFreq() throws RRuleTokenizeException
  {
    IRRuleTokenContainer tokenContainer = tokenizer.tokenize("FREQ=DAILY");

    assertEquals(1, tokenContainer.ruleCount());
    assertNotNull(tokenContainer.getFreq());
    assertEquals(FreqToken.FreqValue.DAILY, tokenContainer.getFreq().getValue());
  }

  @Test
  void testUntil() throws RRuleTokenizeException
  {
    IRRuleTokenContainer tokenContainer = tokenizer.tokenize("FREQ=DAILY;UNTIL=20181023T220000Z");

    assertEquals(2, tokenContainer.ruleCount());
    assertNotNull(tokenContainer.getUntil());
    assertEquals(LocalDate.of(2018, 10, 23), tokenContainer.getUntil().getValue());
  }

  @Test
  void testCount() throws RRuleTokenizeException
  {
    IRRuleTokenContainer tokenContainer = tokenizer.tokenize("FREQ=DAILY;COUNT=1");

    assertEquals(2, tokenContainer.ruleCount());
    assertNotNull(tokenContainer.getCount());
    assertEquals(1, (int) tokenContainer.getCount().getValue());
  }

  @Test
  void testInterval() throws RRuleTokenizeException
  {
    IRRuleTokenContainer tokenContainer = tokenizer.tokenize("FREQ=DAILY;INTERVAL=1");

    assertEquals(2, tokenContainer.ruleCount());
    assertNotNull(tokenContainer.getInterval());
    assertEquals(1, (int) tokenContainer.getInterval().getValue());
  }

  @Test
  void testByDay() throws RRuleTokenizeException
  {
    IRRuleTokenContainer tokenContainer = tokenizer.tokenize("FREQ=DAILY;BYDAY=MO,TU,WE,SA");

    assertEquals(2, tokenContainer.ruleCount());
    assertNotNull(tokenContainer.getByDay());
    assertEquals(4, tokenContainer.getByDay().getValue().getDayList().size());
  }

  @Test
  void testByMonthDay() throws RRuleTokenizeException
  {
    IRRuleTokenContainer tokenContainer = tokenizer.tokenize("FREQ=DAILY;BYMONTHDAY=2");

    assertEquals(2, tokenContainer.ruleCount());
    assertNotNull(tokenContainer.getByMontDay());
    assertEquals(2, (int) tokenContainer.getByMontDay().getValue());
  }

  @Test
  void testByMonth() throws RRuleTokenizeException
  {
    IRRuleTokenContainer tokenContainer = tokenizer.tokenize("FREQ=DAILY;BYMONTH=12");

    assertEquals(2, tokenContainer.ruleCount());
    assertNotNull(tokenContainer.getByMonth());
    assertEquals(Month.DECEMBER, tokenContainer.getByMonth().getValue());
  }

  @Test
  void testBySetPos() throws RRuleTokenizeException
  {
    IRRuleTokenContainer tokenContainer = tokenizer.tokenize("FREQ=DAILY;BYSETPOS=10");

    assertEquals(2, tokenContainer.ruleCount());
    assertNotNull(tokenContainer.getBySetPos());
    assertEquals(10, (int) tokenContainer.getBySetPos().getValue());
  }

  @Test
  void testComplex() throws RRuleTokenizeException
  {
    IRRuleTokenContainer tokenContainer = tokenizer.tokenize("FREQ=YEARLY;BYDAY=TU;BYSETPOS=1;BYMONTH=4;UNTIL=20181023T220000Z");

    assertEquals(5, tokenContainer.ruleCount());
    assertNotNull(tokenContainer.getFreq());
    assertNotNull(tokenContainer.getByDay());
    assertNotNull(tokenContainer.getBySetPos());
    assertNotNull(tokenContainer.getByMonth());
    assertNotNull(tokenContainer.getUntil());
    assertEquals(FreqToken.FreqValue.YEARLY, tokenContainer.getFreq().getValue());
  }

  @Test
  void testSimpleInvalid()
  {
    assertThrows(RRuleTokenizeException.class, () -> {
      tokenizer.tokenize("FREQ=NOFREQVALATALL");
    });
  }
}