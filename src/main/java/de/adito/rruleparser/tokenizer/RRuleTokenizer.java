package de.adito.rruleparser.tokenizer;

import de.adito.rruleparser.tokenizer.exception.RRuleTokenizeException;
import de.adito.rruleparser.tokenizer.token.ERRuleToken;
import de.adito.rruleparser.tokenizer.validation.IRRuleValidator;
import de.adito.rruleparser.tokenizer.value.IValueParser;

import java.util.*;

/**
 * Default implementation of {@link IRRuleTokenizer}.
 */
public class RRuleTokenizer implements IRRuleTokenizer
{
  private IValueParser valueParser;
  private IRRuleValidator rruleValidator;

  public RRuleTokenizer(IValueParser pValueParser, IRRuleValidator pRRuleValidator)
  {
    valueParser = pValueParser;
    rruleValidator = pRRuleValidator;
  }

  @Override
  public IRRuleTokenContainer tokenize(String rruleInput) throws RRuleTokenizeException
  {
    List<IRRuleToken> rruleTokens = new ArrayList<>();
    String[] rruleParts = _splitRRule(rruleInput);

    for (String rrulePart : rruleParts)
    {
      rruleTokens.add(_processRRulePart(rrulePart));
    }

    IRRuleTokenContainer tokenContainer = new RRuleTokenContainer(rruleTokens);

    rruleValidator.validateRRuleParts(tokenContainer);
    return tokenContainer;
  }

  private IRRuleToken _processRRulePart(String rrulePart) throws RRuleTokenizeException
  {
    String[] rruleKeyValue = _splitRRulePart(rrulePart);
    if (rruleKeyValue.length != 2)
      throw new RRuleTokenizeException(String.format("RRule part '%s' contains more than key/value", rrulePart));

    String rruleKey = rruleKeyValue[0];
    String rruleValue = rruleKeyValue[1];

    ERRuleToken rruleToken = ERRuleToken.searchRRuleToken(rruleKey);
    if (rruleToken == null)
      throw new RRuleTokenizeException(String.format("RRule key '%s' not found", rruleKey));

    Object valueInstance = _parseValue(rruleToken, rruleValue);
    if (valueInstance == null)
      throw new RRuleTokenizeException(String.format("RRule value '%s' is not applicable for key '%s'", rruleValue, rruleKey));

    return rruleToken.getTokenInstance(valueInstance);
  }

  private String[] _splitRRule(String rruleInput)
  {
    return rruleInput.split(";");
  }

  private String[] _splitRRulePart(String rrulePartInput)
  {
    return rrulePartInput.split("=");
  }

  private Object _parseValue(ERRuleToken pRRuleToken, String pPayload) throws RRuleTokenizeException
  {
    return valueParser.parseValue(pRRuleToken, pPayload);
  }
}
