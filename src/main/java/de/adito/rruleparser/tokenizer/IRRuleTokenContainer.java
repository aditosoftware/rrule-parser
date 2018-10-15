package de.adito.rruleparser.tokenizer;

import de.adito.rruleparser.tokenizer.token.*;

/**
 * Describes a container which can hold all possible tokens from an rrule.
 *
 * @see RRuleTokenContainer
 */
public interface IRRuleTokenContainer
{
  /**
   * Returns the total amount of rules.
   *
   * @return Total count of rules
   */
  int ruleCount();

  /**
   * Returns the "FREQ" token.
   * {@code null} if not set.
   *
   * @return "FREQ" token or {@code null}.
   */
  FreqToken getFreq();

  /**
   * Returns the "UNTIL" token.
   * {@code null} if not set.
   *
   * @return "UNTIL" token or {@code null}.
   */
  UntilToken getUntil();

  /**
   * Returns the "COUNT" token.
   * {@code null} if not set.
   *
   * @return "COUNT" token or {@code null}.
   */
  CountToken getCount();

  /**
   * Returns the "INTERVAL" token.
   * {@code null} if not set.
   *
   * @return "INTERVAL" token or {@code null}.
   */
  IntervalToken getInterval();

  /**
   * Returns the "BYDAY" token.
   * {@code null} if not set.
   *
   * @return "BYDAY" token or {@code null}.
   */
  ByDayToken getByDay();

  /**
   * Returns the "BYMONTHDAY" token.
   * {@code null} if not set.
   *
   * @return "BYMONTHDAY" token or {@code null}.
   */
  ByMonthDayToken getByMontDay();

  /**
   * Returns the "BYMONTH" token.
   * {@code null} if not set.
   *
   * @return "BYMONTH" token or {@code null}.
   */
  ByMonthToken getByMonth();

  /**
   * Returns the "BYSETPOS" token.
   * {@code null} if not set.
   *
   * @return "BYSETPOS" token or {@code null}.
   */
  BySetPosToken getBySetPos();
}
