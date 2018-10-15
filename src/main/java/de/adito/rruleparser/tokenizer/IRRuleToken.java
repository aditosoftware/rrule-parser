package de.adito.rruleparser.tokenizer;

import de.adito.rruleparser.tokenizer.token.FreqToken;

/**
 * Describes a RRule token, which contains the name of the token and
 * the current value (T).
 *
 * @param <T> The accepted value of the token.
 * @see FreqToken
 */
public interface IRRuleToken<T>
{
  /**
   * Returns the name of the RRule token (e.g. "FREQ"/"INTERVAL"/"UNTIL"/...)
   *
   * @return Name of the RRule token.
   */
  String getName();

  /**
   * Returns the parsed the value of the RRule token. (e.g. "YEARLY", for token "FREQ")
   *
   * @return Parsed value of the RRule token.
   */
  T getValue();
}
