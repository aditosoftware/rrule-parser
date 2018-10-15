package de.adito.rruleparser.tokenizer;

import de.adito.rruleparser.tokenizer.exception.RRuleTokenizeException;
import de.adito.rruleparser.tokenizer.validation.exception.RRuleValidationException;

/**
 * Describes an interface which is able to tokenize a given rrule string.
 *
 * @see RRuleTokenizer for default implementation.
 */
public interface IRRuleTokenizer
{
  /**
   * Takes a rrule string as first parameter and tokenize it into key/value pairs.
   *
   * @param rruleInput RRule as stringo to tokenize.
   * @return The parsed tokens as a {@link RRuleTokenContainer}.
   * @throws RRuleTokenizeException   If the tokenization fails somehow.
   * @throws RRuleValidationException If the validation fails.
   */
  IRRuleTokenContainer tokenize(String rruleInput) throws RRuleTokenizeException;
}
