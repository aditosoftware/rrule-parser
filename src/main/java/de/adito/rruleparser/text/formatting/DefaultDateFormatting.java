package de.adito.rruleparser.text.formatting;

import de.adito.rruleparser.translation.IFragmentTranslator;

import java.time.*;
import java.time.format.*;

/**
 * Implements the default date formatting, which is valid within
 * the english language. It uses the internal translation using
 * the given locale from the {@link IFragmentTranslator}.
 */
public class DefaultDateFormatting implements IDateFormatting
{
  private IFragmentTranslator fragmentTranslator;

  public DefaultDateFormatting(IFragmentTranslator pIFragmentTranslator)
  {
    fragmentTranslator = pIFragmentTranslator;
  }

  /**
   * Formats the given date to e.g. 01 Jan 2018
   *
   * @param pLocalDate The date to format.
   * @return Formatted date.
   */
  @Override
  public String formatFullDate(LocalDate pLocalDate)
  {
    return pLocalDate.format(DateTimeFormatter.ofPattern(
        "dd MMM yyyy", fragmentTranslator.getCompatibleLocale()));
  }

  /**
   * Formats the given MonthDay to e.g. January 01.
   *
   * @param pMonthDay The MonthDay to format.
   * @return Formatted date.
   */
  @Override
  public String formatMonthDay(MonthDay pMonthDay)
  {
    return pMonthDay.format(DateTimeFormatter.ofPattern(
        "MMMM dd", fragmentTranslator.getCompatibleLocale()));
  }

  /**
   * Formats a date which just contains the month into
   * a readable text depending on the implementation.
   *
   * @param pMonth The month to format.
   * @return Formatted date.
   */
  @Override
  public String formatMonth(Month pMonth)
  {
    return pMonth.getDisplayName(TextStyle.FULL, fragmentTranslator.getCompatibleLocale());
  }

  /**
   * Formats a date which just contains the day into
   * a readable text depending on the implementation.
   *
   * @param pDayOfWeek Day to format.
   * @return Day in the desired format.
   */
  @Override
  public String formatDay(DayOfWeek pDayOfWeek)
  {
    return pDayOfWeek.getDisplayName(TextStyle.FULL, fragmentTranslator.getCompatibleLocale());
  }

  /**
   * Same as {@link this#formatDay(DayOfWeek)}, but
   * returns the shorted name of the day.
   *
   * @param pDayOfWeek Day to format.
   * @return Day in the desired format.
   */
  @Override
  public String formatDayShort(DayOfWeek pDayOfWeek)
  {
    return pDayOfWeek.getDisplayName(TextStyle.SHORT, fragmentTranslator.getCompatibleLocale());
  }
}
