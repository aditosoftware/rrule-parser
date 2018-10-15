package de.adito.rruleparser.tokenizer.validation;

import de.adito.rruleparser.tokenizer.IRRuleTokenContainer;
import de.adito.rruleparser.tokenizer.validation.exception.RRuleValidationException;

/**
 * Default implementation of {@link IRRuleValidator}. Currently validates the following:
 * - If the"FREQ" token is set exactly once.
 * - "UNTIL" or "COUNT" token is set (Both can't be set.)
 */
public class RRuleValidator implements IRRuleValidator
{
  @Override
  public void validateRRuleParts(IRRuleTokenContainer pTokenContainer)
  {
    _ensureFreqExistence(pTokenContainer);
    _ensureSingleExistenceUntilCount(pTokenContainer);
  }

  private void _ensureFreqExistence(IRRuleTokenContainer pTokenContainer)
  {
    if (pTokenContainer.getFreq() == null)
      throw new RRuleValidationException("Required rrule part 'FREQ' is not present");
  }

  private void _ensureSingleExistenceUntilCount(IRRuleTokenContainer pTokenContainer)
  {
    if (pTokenContainer.getUntil() != null && pTokenContainer.getCount() != null)
      throw new RRuleValidationException("RRule part 'UNTIL' and 'COUNT' are present at the same time");
  }
}
