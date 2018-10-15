package de.adito.rruleparser.tokenizer;

import de.adito.rruleparser.tokenizer.token.*;

public interface IRRuleTokenContainer
{
  int ruleCount();

  FreqToken getFreq();

  UntilToken getUntil();

  CountToken getCount();

  IntervalToken getInterval();

  ByDayToken getByDay();

  ByMonthDayToken getByMontDay();

  ByMonthToken getByMonth();

  BySetPosToken getBySetPos();
}
