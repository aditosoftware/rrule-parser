package de.adito.rruleparser.text.listing;

import java.time.DayOfWeek;
import java.util.List;

/**
 * Describes an interface which is able to format a given list of
 * {@link DayOfWeek} into any format.
 */
public interface IDayListingFormatting
{
  /**
   * Formats the given list of DayOfWeek into a specific format.
   *
   * @param pDayOfWeeks List of DayOfWeek to format.
   * @return Formatted list.
   */
  String formatDayListing(List<DayOfWeek> pDayOfWeeks);
}
