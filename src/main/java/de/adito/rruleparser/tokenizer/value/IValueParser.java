package de.adito.rruleparser.tokenizer.value;

import de.adito.rruleparser.tokenizer.exception.RRuleTokenizeException;
import de.adito.rruleparser.tokenizer.token.ERRuleToken;

public interface IValueParser
{
  Object parseValue(ERRuleToken pRRuleToken, String value) throws RRuleTokenizeException;
}
