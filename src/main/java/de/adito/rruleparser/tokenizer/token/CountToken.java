package de.adito.rruleparser.tokenizer.token;

import de.adito.rruleparser.tokenizer.IRRuleToken;

public class CountToken implements IRRuleToken<Integer>
{
  private Integer value;

  public CountToken(Object pValue)
  {
    value = (Integer) pValue;
  }

  @Override
  public String getName()
  {
    return "COUNT";
  }

  @Override
  public Integer getValue()
  {
    return value;
  }
}
