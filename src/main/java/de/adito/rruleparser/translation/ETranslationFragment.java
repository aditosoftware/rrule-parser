package de.adito.rruleparser.translation;

/**
 * This enum defines all required text blocks, which are required
 * to build a human readable text of a rrule.
 */
public enum ETranslationFragment
{
  // Frequency
  DAILY,
  WEEKLY,
  MONTHLY,
  YEARLY,

  DAYS,
  WEEKS,
  MONTHS,
  YEARS,
  DAY,

  // SetPos
  FIRST,
  SECOND,
  THIRD,
  FOURTH,
  LAST,

  // Utils
  UNTIL,
  TIMES,
  ON,
  EVERY,
  OF
}
