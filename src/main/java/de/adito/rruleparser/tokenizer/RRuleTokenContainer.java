package de.adito.rruleparser.tokenizer;

import de.adito.rruleparser.tokenizer.token.*;

import java.util.*;
import java.util.stream.Collectors;

public class RRuleTokenContainer implements IRRuleTokenContainer
{
  private Map<Class<? extends IRRuleToken>, IRRuleToken> tokenInstancesMap;

  RRuleTokenContainer(List<IRRuleToken> rruleTokens)
  {
    tokenInstancesMap = new LinkedHashMap<>();

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

  @Override
  public void merge(IRRuleTokenContainer pTokenContainer)
  {
    if (pTokenContainer.getFreq() != null && getFreq() == null)
      tokenInstancesMap.put(pTokenContainer.getFreq().getClass(), pTokenContainer.getFreq());

    if (pTokenContainer.getUntil() != null && getUntil() == null)
      tokenInstancesMap.put(pTokenContainer.getUntil().getClass(), pTokenContainer.getUntil());

    if (pTokenContainer.getCount() != null && getCount() == null)
      tokenInstancesMap.put(pTokenContainer.getCount().getClass(), pTokenContainer.getCount());

    if (pTokenContainer.getInterval() != null && getInterval() == null)
      tokenInstancesMap.put(pTokenContainer.getInterval().getClass(), pTokenContainer.getInterval());

    if (pTokenContainer.getByDay() != null && getByDay() == null)
      tokenInstancesMap.put(pTokenContainer.getByDay().getClass(), pTokenContainer.getByDay());

    if (pTokenContainer.getByMontDay() != null && getByMontDay() == null)
      tokenInstancesMap.put(pTokenContainer.getByMontDay().getClass(), pTokenContainer.getByMontDay());

    if (pTokenContainer.getByMonth() != null && getByMonth() == null)
      tokenInstancesMap.put(pTokenContainer.getByMonth().getClass(), pTokenContainer.getByMonth());

    if (pTokenContainer.getBySetPos() != null && getBySetPos() == null)
      tokenInstancesMap.put(pTokenContainer.getBySetPos().getClass(), pTokenContainer.getBySetPos());

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

  /**
   * Converts the tokens in the container to joined string.
   *
   * @return Current tokens as valid RRule.
   */
  @Override
  public String toString()
  {
    return this.tokenInstancesMap.values().stream()
        .map(v -> v.getName() + "=" + v.getValue().toString())
        .collect(Collectors.joining(";"));
  }
}
