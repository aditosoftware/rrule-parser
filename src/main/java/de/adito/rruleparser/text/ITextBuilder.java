package de.adito.rruleparser.text;

import de.adito.rruleparser.tokenizer.IRRuleTokenContainer;

/**
 * Describes an interface which is able to translate
 * a given {@link IRRuleTokenContainer}.
 */
public interface ITextBuilder
{
  /**
   * Builds a valid human readable text out of the
   * given {@link IRRuleTokenContainer}.
   *
   * @param pRRuleTokenContainer Token container to convert.
   * @return Converted text.
   */
  String buildText(IRRuleTokenContainer pRRuleTokenContainer);
}
