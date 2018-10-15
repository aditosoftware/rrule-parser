package de.adito.rruleparser;

import de.adito.rruleparser.tokenizer.exception.RRuleTokenizeException;
import de.adito.rruleparser.tokenizer.validation.exception.RRuleValidationException;

/**
 * Describes an parser which accepts a RRule (e.g. "FREQ=MONTHLY;BYSETPOS=4;BYDAY=SU;INTERVAL=5;COUNT=1")
 * and converts it to a human readable text (e.g. Every 5 months on fourth sunday).
 * (https://tools.ietf.org/html/rfc2445#section-4.3.10)
 *
 * @see RRuleParser
 */
public interface IRRuleParser
{
  /**
   * Takes a RRule as first parameter and coverts it into a human readable text.
   *
   * @param pRRule The RRule to convert.
   * @return Convert RRule as human readable text.
   * @throws RRuleTokenizeException   If the given RRule is invalid
   * @throws RRuleValidationException If the given RRule contains
   *                                  invalid keys (e.g. 'FREQ' key is not set).
   */
  String parseRRule(String pRRule) throws RRuleTokenizeException;
}
