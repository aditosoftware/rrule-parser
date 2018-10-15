package de.adito.rruleparser.tokenizer;

import de.adito.rruleparser.tokenizer.validation.RRuleValidator;
import de.adito.rruleparser.tokenizer.value.RRuleValueParser;

public class RRuleTokenizerFactor
{
  public static IRRuleTokenizer defaultImpl()
  {
    return new RRuleTokenizer(new RRuleValueParser(), new RRuleValidator());
  }
}
