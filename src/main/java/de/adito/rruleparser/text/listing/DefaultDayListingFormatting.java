package de.adito.rruleparser.text.listing;

import de.adito.rruleparser.text.formatting.IDateFormatting;
import de.adito.rruleparser.translation.IFragmentTranslator;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

public class DefaultDayListingFormatting implements IDayListingFormatting
{
  private IFragmentTranslator fragmentTranslator;
  private IDateFormatting dateFormatting;

  public DefaultDayListingFormatting(IFragmentTranslator pFragmentTranslator, IDateFormatting pDateFormatting)
  {
    fragmentTranslator = pFragmentTranslator;
    dateFormatting = pDateFormatting;
  }

  @Override
  public String formatDayListing(List<DayOfWeek> pDayOfWeeks)
  {
    List<DayOfWeek> orderedDayOfWeeks = orderDayOfWeek(pDayOfWeeks);

    List<List<DayOfWeek>> chains = _detectChain(orderedDayOfWeeks);

    if (chains.size() == 1
        && chains.get(0).size() > 2
        && chains.get(0).size() == orderedDayOfWeeks.size())
    {
      List<DayOfWeek> chainContent = chains.get(0);
      return _buildChainRepresentation(chainContent.get(0), chainContent.get(chainContent.size() - 1));
    }
    else
    {
      // Just list them
      if (orderedDayOfWeeks.size() > 2)
        return _buildMonthsShortString(orderedDayOfWeeks);
      else
        return _buildMonthsString(orderedDayOfWeeks);
    }
  }

  private List<List<DayOfWeek>> _detectChain(List<DayOfWeek> pDayOfWeeks)
  {
    List<DayOfWeek> fullDays = getFilledDayOfWeek();
    List<List<DayOfWeek>> chains = new ArrayList<>();

    int currentChainlength = 0;
    Integer lastChainOrdinal = null;
    List<DayOfWeek> currentChain = new ArrayList<>();

    for (int i = 0; i < pDayOfWeeks.size(); i++)
    {
      DayOfWeek currentDayOfWeek = pDayOfWeeks.get(i);
      int ordinalOfDay = fullDays.indexOf(currentDayOfWeek);

      // Chain is broken
      if (lastChainOrdinal != null && lastChainOrdinal != (ordinalOfDay - 1))
      {
        chains.add(currentChain);
        currentChain = null;
        currentChainlength = 0;
        lastChainOrdinal = null;
      }

      // Chain has not started yet
      if (lastChainOrdinal == null)
      {
        currentChain = new ArrayList<>();
        lastChainOrdinal = ordinalOfDay;
        currentChainlength++;
        currentChain.add(currentDayOfWeek);
      }
      else if (lastChainOrdinal == ordinalOfDay - 1)
      {
        lastChainOrdinal = ordinalOfDay;
        currentChainlength++;
        currentChain.add(currentDayOfWeek);

        // Entire input list is a chain
        if (i == (pDayOfWeeks.size() - 1))
          chains.add(currentChain);
      }
    }

    return chains;
  }

  private String _buildMonthsShortString(List<DayOfWeek> pDayOfWeeks)
  {
    return pDayOfWeeks.stream().map(v -> dateFormatting.formatDayShort(v)).collect(Collectors.joining(", "));
  }

  private String _buildMonthsString(List<DayOfWeek> pDayOfWeeks)
  {
    return pDayOfWeeks.stream().map(v -> dateFormatting.formatDay(v)).collect(Collectors.joining(", "));
  }

  private String _buildChainRepresentation(DayOfWeek pStart, DayOfWeek pEnd)
  {
    return dateFormatting.formatDay(pStart) + " - " + dateFormatting.formatDay(pEnd);
  }

  /**
   * Orders the given DayOfWeeks by the implemented algorithm.
   * This method is protected to modify the first day of week.
   *
   * @param pDayOfWeeks List to order.
   * @return New ordered list.
   */
  protected List<DayOfWeek> orderDayOfWeek(List<DayOfWeek> pDayOfWeeks)
  {
    List<DayOfWeek> orderedDayOfWeeks = new ArrayList<>(pDayOfWeeks);
    Collections.sort(orderedDayOfWeeks);
    return orderedDayOfWeeks;
  }

  /**
   * Returns a list with all possible day of weeks in the required order.
   * This method is protected to modify the first day of week.
   *
   * @return List of days.o
   */
  protected List<DayOfWeek> getFilledDayOfWeek()
  {
    return Arrays.asList(DayOfWeek.values());
  }
}
