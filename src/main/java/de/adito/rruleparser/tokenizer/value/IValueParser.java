package de.adito.rruleparser.tokenizer.value;

import de.adito.rruleparser.tokenizer.exception.RRuleTokenizeException;
import de.adito.rruleparser.tokenizer.token.ERRuleToken;

/**
 * Describes a value parser which is able to convert the value of a
 * rrule into the requried object.
 * (This additional interface exists because most rrule tokens have the same value type.)
 */
public interface IValueParser
{
  /**
   * Parses the value of a rrule into the required object.
   *
   * @param pRRuleToken RRule token to parse.
   * @param value       The incoming value as string which should be parsed.
   * @return The parsed object which is compatible with the token implementation.
   * @throws RRuleTokenizeException If the value for a token is invalid.
   */
  Object parseValue(ERRuleToken pRRuleToken, String value) throws RRuleTokenizeException;
}
