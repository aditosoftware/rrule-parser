package de.adito.rruleparser.tokenizer.token;

import de.adito.rruleparser.tokenizer.IRRuleToken;

public class BySetPosToken implements IRRuleToken<Integer>
{
  private Integer value;

  public BySetPosToken(Object pValue)
  {
    value = (Integer) pValue;
  }

  @Override
  public String getName()
  {
    return "BYSETPOS";
  }

  @Override
  public Integer getValue()
  {
    return value;
  }
}
