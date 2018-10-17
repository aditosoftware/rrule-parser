package de.adito.rruleparser.tokenizer.value;

import de.adito.rruleparser.tokenizer.exception.RRuleTokenizeException;
import de.adito.rruleparser.tokenizer.token.*;

import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * @see IValueParser
 */
public class RRuleValueParser implements IValueParser
{
  @Override
  public Object parseValue(ERRuleToken pRRuleToken, String value) throws RRuleTokenizeException
  {
    Object resultValue = null;
    switch (pRRuleToken)
    {
      case Freq:
        resultValue = _getFreqValue(value);
        break;
      case Until:
        resultValue = _getUntilValue(value);
        break;
      case Count:
        resultValue = _parseInteger(value);
        break;
      case Interval:
        resultValue = _parseInteger(value);
        break;
      case ByDay:
        resultValue = _getByDay(value);
        break;
      case ByMonthDay:
        resultValue = _parseInteger(value);
        break;
      case ByMonth:
        resultValue = _getByMonth(value);
        break;
      case BySetPos:
        resultValue = _parseInteger(value);
        break;
    }

    if (resultValue == null)
      throw new RRuleTokenizeException(String.format("Value '%s' is invalid for key '%s'", value, pRRuleToken.toString().toUpperCase()));

    return resultValue;
  }

  private FreqToken.FreqValue _getFreqValue(String pValue)
  {
    switch (pValue)
    {
      case "SECONDLY":
        return FreqToken.FreqValue.SECONDLY;
      case "MINUTELY":
        return FreqToken.FreqValue.MINUTELY;
      case "HOURLY":
        return FreqToken.FreqValue.HOURLY;
      case "DAILY":
        return FreqToken.FreqValue.DAILY;
      case "WEEKLY":
        return FreqToken.FreqValue.WEEKLY;
      case "MONTHLY":
        return FreqToken.FreqValue.MONTHLY;
      case "YEARLY":
        return FreqToken.FreqValue.YEARLY;
      default:
        return null;
    }
  }

  private UntilToken.ValueWrapper _getUntilValue(String pValue)
  {
    try
    {
      return new UntilToken.ValueWrapper(LocalDateTime.parse(pValue, UntilToken.DATE_FORMAT));
    }
    catch (DateTimeParseException e)
    {
      // Todo: Exception handling
      return null;
    }
  }

  private Integer _parseInteger(String pValue)
  {
    try
    {
      return Integer.valueOf(pValue);
    }
    catch (Exception e)
    {
      return null;
    }
  }

  private ByDayToken.DayList _getByDay(String pValue)
  {
    String[] parts = pValue.split(",");
    List<DayOfWeek> days = new ArrayList<>(7);

    for (String part : parts)
    {
      switch (part)
      {
        case "MO":
          days.add(DayOfWeek.MONDAY);
          break;
        case "TU":
          days.add(DayOfWeek.TUESDAY);
          break;
        case "WE":
          days.add(DayOfWeek.WEDNESDAY);
          break;
        case "TH":
          days.add(DayOfWeek.THURSDAY);
          break;
        case "FR":
          days.add(DayOfWeek.FRIDAY);
          break;
        case "SA":
          days.add(DayOfWeek.SATURDAY);
          break;
        case "SU":
          days.add(DayOfWeek.SUNDAY);
          break;
      }
    }

    if (parts.length != days.size())
      return null;

    return new ByDayToken.DayList(days);
  }

  private Month _getByMonth(String pValue)
  {
    try
    {
      return Month.of(Integer.parseInt(pValue));
    }
    catch (Exception e)
    {
      return null;
    }
  }
}
