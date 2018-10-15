package de.adito.rruleparser.tokenizer.token;

import de.adito.rruleparser.tokenizer.IRRuleToken;

import java.time.Month;

public class ByMonthToken implements IRRuleToken<Month>
{
  private Month value;

  public ByMonthToken(Object pValue)
  {
    value = (Month) pValue;
  }

  @Override
  public String getName()
  {
    return "BYMONTH";
  }

  @Override
  public Month getValue()
  {
    return value;
  }
}
