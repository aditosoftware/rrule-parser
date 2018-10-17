package de.adito.rruleparser.tokenizer.token;

import de.adito.rruleparser.tokenizer.IRRuleToken;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

public class ByDayToken implements IRRuleToken<ByDayToken.DayList>
{
  private DayList value;

  public ByDayToken(Object pValue)
  {
    value = (DayList) pValue;
  }

  @Override
  public String getName()
  {
    return "BYDAY";
  }

  @Override
  public DayList getValue()
  {
    return value;
  }

  public static class DayList
  {
    private List<DayOfWeek> dayList;

    public DayList(List<DayOfWeek> pDayList)
    {
      dayList = pDayList;
    }

    public List<DayOfWeek> getDayList()
    {
      return Collections.unmodifiableList(dayList);
    }

    @Override
    public String toString()
    {
      return getByDayOfWeek(dayList.toArray(new DayOfWeek[0]));
    }
  }

  /**
   * Helper method to get the required string of the day.
   *
   * @param pDayOfWeek DayOfWeek to convert.
   * @return String representative of the DayOfWeek.
   */
  public static String getByDayOfWeek(DayOfWeek pDayOfWeek)
  {
    // Only US required
    String displayName = pDayOfWeek.getDisplayName(TextStyle.FULL, Locale.US);

    return displayName.substring(0, 2).toUpperCase();
  }

  /**
   * Helper method to get the required string which contains multiple days.
   *
   * @param pDayOfWeeks DayOfWeek to convert.
   * @return String representative list of days.
   */
  public static String getByDayOfWeek(DayOfWeek... pDayOfWeeks)
  {
    return Arrays.stream(pDayOfWeeks).map(ByDayToken::getByDayOfWeek).collect(Collectors.joining(","));
  }
}
