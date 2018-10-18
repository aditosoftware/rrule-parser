package de.adito.rruleparser.text.formatting;

import java.time.*;

/**
 * Describes an interface which holds multiple methods to
 * customize the format of dates.
 */
public interface IDateFormatting
{
  /**
   * Formats a full date, which contains year, month and day into
   * a readable text depending on the implementation.
   *
   * @param pLocalDate The date to format.
   * @return Date in the desired format.
   */
  String formatFullDate(LocalDate pLocalDate);

  /**
   * Formats a date which just contains month and day into
   * a readable text depending on the implementation.
   *
   * @param pMonthDay The MonthDay to format.
   * @return MonthDay in the desired format.
   */
  String formatMonthDay(MonthDay pMonthDay);

  /**
   * Formats a date which just contains the month into
   * a readable text depending on the implementation.
   *
   * @param pMonth The month to format.
   * @return Month in the desired format.
   */
  String formatMonth(Month pMonth);

  /**
   * Formats a date which just contains the day into
   * a readable text depending on the implementation.
   *
   * @param pDayOfWeek Day to format.
   * @return Day in the desired format.
   */
  String formatDay(DayOfWeek pDayOfWeek);

  /**
   * Same as {@link this#formatDay(DayOfWeek)}, but
   * returns the shorted name of the day.
   *
   * @param pDayOfWeek Day to format.
   * @return Day in the desired format.
   */
  String formatDayShort(DayOfWeek pDayOfWeek);
}
