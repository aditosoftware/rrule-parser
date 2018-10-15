package de.adito.rruleparser.tokenizer.token;

import de.adito.rruleparser.tokenizer.IRRuleToken;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public enum ERRuleToken
{
  Freq(FreqToken.class),
  Until(UntilToken.class),
  Count(CountToken.class),
  Interval(IntervalToken.class),
  ByDay(ByDayToken.class),
  ByMonthDay(ByMonthDayToken.class),
  ByMonth(ByMonthToken.class),
  BySetPos(BySetPosToken.class),
  ;

  private Class<? extends IRRuleToken> rruleTokenClass;

  ERRuleToken(Class<? extends IRRuleToken> pRRuleTokenClass)
  {
    rruleTokenClass = pRRuleTokenClass;
  }

  public static ERRuleToken searchRRuleToken(String input)
  {
    return Stream.of(ERRuleToken.values())
        .filter(v -> v.toString().toUpperCase().equals(input.toUpperCase()))
        .findFirst().orElse(null);
  }

  public IRRuleToken getTokenInstance(Object value)
  {
    try
    {
      return rruleTokenClass.getConstructor(Object.class).newInstance(value);
    }
    catch (InstantiationException | IllegalAccessException
        | InvocationTargetException | NoSuchMethodException pE)
    {

      // Todo: Exception handling
      pE.printStackTrace();
      return null;
    }
  }
}
