package de.adito.rruleparser.tokenizer;

import de.adito.rruleparser.tokenizer.exception.RRuleTokenizeException;

public interface IRRuleTokenizer
{
  IRRuleTokenContainer tokenize(String rruleInput) throws RRuleTokenizeException;
}
