package de.adito.rruleparser.tokenizer.validation;

import de.adito.rruleparser.tokenizer.IRRuleTokenContainer;
import de.adito.rruleparser.tokenizer.validation.exception.RRuleValidationException;

/**
 * Describes a RRule constraint validator which checks if the RRules
 * are set correctly.
 *
 * @see RRuleValidator
 */
public interface IRRuleValidator
{
  /**
   * Validates the given RRule token container.
   *
   * @param pTokenContainer RRule token container to check against.o
   * @throws RRuleValidationException If the validation of the rrule
   *                                  token container fails.
   */
  void validateRRuleParts(IRRuleTokenContainer pTokenContainer);
}
