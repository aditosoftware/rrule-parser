package de.adito.rruleparser.tokenizer.token;

import de.adito.rruleparser.tokenizer.IRRuleToken;

public class IntervalToken implements IRRuleToken<Integer>
{
  private Integer value;

  public IntervalToken(Object pValue)
  {
    value = (Integer) pValue;
  }

  @Override
  public String getName()
  {
    return "INTERVAL";
  }

  @Override
  public Integer getValue()
  {
    return value;
  }
}
