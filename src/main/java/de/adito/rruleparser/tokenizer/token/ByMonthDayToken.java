package de.adito.rruleparser.tokenizer.token;

import de.adito.rruleparser.tokenizer.IRRuleToken;

public class ByMonthDayToken implements IRRuleToken<Integer>
{
  private Integer value;

  public ByMonthDayToken(Object pValue)
  {
    value = (Integer) pValue;
  }

  @Override
  public String getName()
  {
    return "BYMONTHDAY";
  }

  @Override
  public Integer getValue()
  {
    return value;
  }
}
