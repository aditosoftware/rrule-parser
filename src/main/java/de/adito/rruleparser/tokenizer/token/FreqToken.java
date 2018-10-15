package de.adito.rruleparser.tokenizer.token;

import de.adito.rruleparser.tokenizer.IRRuleToken;

public class FreqToken implements IRRuleToken<FreqToken.FreqValue>
{
  private FreqValue freqValue;

  public FreqToken(Object pFreqValue)
  {
    freqValue = (FreqValue) pFreqValue;

  }

  @Override
  public String getName()
  {
    return "FREQ";
  }

  @Override
  public FreqValue getValue()
  {
    return freqValue;
  }

  public enum FreqValue
  {
    SECONDLY, MINUTELY,
    HOURLY, DAILY,
    WEEKLY, MONTHLY,
    YEARLY
  }
}
