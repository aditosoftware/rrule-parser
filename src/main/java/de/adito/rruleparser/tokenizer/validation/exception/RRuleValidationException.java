package de.adito.rruleparser.tokenizer.validation.exception;

/**
 * Runtime exception which can be thrown during constraint validation of the
 * RRule tokens. (e.g. will be thrown if the "FREQ" token is not set.)
 */
public class RRuleValidationException extends RuntimeException
{
  public RRuleValidationException(String message)
  {
    super(message);
  }
}
