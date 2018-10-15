package de.adito.rruleparser;

import de.adito.rruleparser.text.*;
import de.adito.rruleparser.tokenizer.*;
import de.adito.rruleparser.tokenizer.exception.RRuleTokenizeException;
import de.adito.rruleparser.tokenizer.validation.RRuleValidator;
import de.adito.rruleparser.tokenizer.value.RRuleValueParser;
import de.adito.rruleparser.translation.LanguagePackageFragmentTranslator;
import de.adito.rruleparser.translation.language.*;

public class RRuleParser implements IRRuleParser
{
  private IRRuleTokenizer tokenizer;
  private ITextBuilder textBuilder;

  /**
   * Creates the RRuleParser. With the english translation.
   */
  public RRuleParser()
  {
    this(new EnglishTranslation());
  }

  /**
   * Creates the RRuleParser. With {@link TextBuilder} as TextBuilder.
   *
   * @param pLanguagePackage The language to use.
   */
  public RRuleParser(ILanguagePackage pLanguagePackage)
  {
    this(new TextBuilder(new LanguagePackageFragmentTranslator(pLanguagePackage)));
  }

  /**
   * Creates the RRuleParser. With {@link TextBuilder} as TextBuilder and the
   * english translation.
   *
   * @param pRRuleTokenizer The tokenizer to use.
   */
  public RRuleParser(IRRuleTokenizer pRRuleTokenizer)
  {
    this(pRRuleTokenizer, new TextBuilder(new LanguagePackageFragmentTranslator(new EnglishTranslation())));
  }

  /**
   * Creates the RRuleParser. With {@link RRuleTokenizer} as RRuleTokenizer.
   *
   * @param pTextBuilder The text builder to use.
   */
  public RRuleParser(ITextBuilder pTextBuilder)
  {
    this(new RRuleTokenizer(new RRuleValueParser(), new RRuleValidator()), pTextBuilder);
  }

  /**
   * Creates the RRuleParser.
   *
   * @param pRRuleTokenizer The tokenizer to use.
   * @param pTextBuilder    The text builder to use.
   */
  public RRuleParser(IRRuleTokenizer pRRuleTokenizer, ITextBuilder pTextBuilder)
  {
    tokenizer = pRRuleTokenizer;
    textBuilder = pTextBuilder;
  }

  @Override
  public String parseRRule(String pRRule) throws RRuleTokenizeException
  {
    // Build the rrule token container from the given rule.
    IRRuleTokenContainer rruleTokenContainer = _tokenizeRRule(pRRule);

    // Build the text for the rrule token container
    return _buildText(rruleTokenContainer);
  }

  private IRRuleTokenContainer _tokenizeRRule(String pInput) throws RRuleTokenizeException
  {
    return tokenizer.tokenize(pInput);
  }

  private String _buildText(IRRuleTokenContainer pTokenContainer)
  {
    return textBuilder.buildText(pTokenContainer);
  }
}
