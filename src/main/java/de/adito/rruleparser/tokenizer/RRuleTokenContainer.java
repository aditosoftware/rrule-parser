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

  /**
   * Checks if the given container is equals to the current container.
   * Will check if the {@link this#tokenInstancesMap} has different sizes.
   * And als if the value each token is equal.
   *
   * @param obj Container to check against
   * @return {@code true} if the containers are equal, {@code false} otherwisen.
   */
  @Override
  public boolean equals(Object obj)
  {
    // Can currently only compare with it self.
    if (obj instanceof RRuleTokenContainer)
    {
      RRuleTokenContainer foreignTokenContainer = (RRuleTokenContainer) obj;

      // Different sized containers can't be equal.
      if (foreignTokenContainer.tokenInstancesMap.size() != this.tokenInstancesMap.size())
        return false;

      for (IRRuleToken cToken : foreignTokenContainer.tokenInstancesMap.values())
        if (!cToken.getValue().equals(this.tokenInstancesMap.get(cToken.getClass()).getValue()))
          return false;

      return true;
    }
    else
      return false;
  }
}
