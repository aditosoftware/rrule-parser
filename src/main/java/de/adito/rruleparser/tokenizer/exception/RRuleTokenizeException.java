package de.adito.rruleparser.tokenizer.exception;

import de.adito.rruleparser.tokenizer.IRRuleTokenizer;

/**
 * Exception which will be thrown if the {@link IRRuleTokenizer} encountered
 * an invalid character during the analyzation of the rrule.
 */
public class RRuleTokenizeException extends Exception
{
  public RRuleTokenizeException(String message)
  {
    super(message);
  }
}
