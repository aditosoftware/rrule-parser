package de.adito.rruleparser.tokenizer.validation;

import de.adito.rruleparser.tokenizer.IRRuleTokenContainer;

public interface IRRuleValidator
{
  void validateRRuleParts(IRRuleTokenContainer pTokenContainer);
}
