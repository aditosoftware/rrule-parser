package de.adito.rruleparser.tokenizer.token;

import de.adito.rruleparser.tokenizer.IRRuleToken;

import java.time.DayOfWeek;
import java.util.*;

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
  }
}
