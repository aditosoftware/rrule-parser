package de.adito.rruleparser;

import de.adito.rruleparser.tokenizer.exception.RRuleTokenizeException;

public interface IRRuleParser
{
  String parseRRule(String pRRule) throws RRuleTokenizeException;
}
