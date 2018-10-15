package de.adito.rruleparser;

import de.adito.rruleparser.text.ITextBuilder;
import de.adito.rruleparser.tokenizer.*;
import de.adito.rruleparser.tokenizer.exception.RRuleTokenizeException;

public class RRuleParser implements IRRuleParser
{
  private IRRuleTokenizer rruleTokenizer;
  private ITextBuilder textBuilder;

  public RRuleParser(IRRuleTokenizer pRRuleTokenizer, ITextBuilder pTextBuilder)
  {
    rruleTokenizer = pRRuleTokenizer;
  }

  @Override
  public String parseRRule(String pRRule) throws RRuleTokenizeException
  {
    IRRuleTokenContainer rruleTokens = _tokenizeRRule(pRRule);

    String builtText = _buildText(rruleTokens);

    return builtText;
  }

  private IRRuleTokenContainer _tokenizeRRule(String pInput) throws RRuleTokenizeException
  {
    return rruleTokenizer.tokenize(pInput);
  }

  private String _buildText(IRRuleTokenContainer pTokenContainer)
  {
    return textBuilder.buildText(pTokenContainer);
  }
}
