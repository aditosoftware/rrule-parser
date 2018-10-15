package de.adito.rruleparser.tokenizer;

import de.adito.rruleparser.tokenizer.token.*;

import java.util.*;

public class RRuleTokenContainer implements IRRuleTokenContainer
{
  private Map<Class<? extends IRRuleToken>, IRRuleToken> tokenInstancesMap;

  RRuleTokenContainer(List<IRRuleToken> rruleTokens)
  {
    tokenInstancesMap = new HashMap<>();

    for (IRRuleToken token : rruleTokens)
      tokenInstancesMap.put(token.getClass(), token);
  }

  @Override
  public int ruleCount()
  {
    return tokenInstancesMap.size();
  }

  @Override
  public FreqToken getFreq()
  {
    return (FreqToken) tokenInstancesMap.get(FreqToken.class);
  }

  @Override
  public UntilToken getUntil()
  {
    return (UntilToken) tokenInstancesMap.get(UntilToken.class);
  }

  @Override
  public CountToken getCount()
  {
    return (CountToken) tokenInstancesMap.get(CountToken.class);
  }

  @Override
  public IntervalToken getInterval()
  {
    return (IntervalToken) tokenInstancesMap.get(IntervalToken.class);
  }

  @Override
  public ByDayToken getByDay()
  {
    return (ByDayToken) tokenInstancesMap.get(ByDayToken.class);
  }

  @Override
  public ByMonthDayToken getByMontDay()
  {
    return (ByMonthDayToken) tokenInstancesMap.get(ByMonthDayToken.class);
  }

  @Override
  public ByMonthToken getByMonth()
  {
    return (ByMonthToken) tokenInstancesMap.get(ByMonthToken.class);
  }

  @Override
  public BySetPosToken getBySetPos()
  {
    return (BySetPosToken) tokenInstancesMap.get(BySetPosToken.class);
  }
}
