package de.adito.rruleparser.translation;

import de.adito.rruleparser.translation.language.ILanguagePackage;

import java.util.Locale;

/**
 * This implementation is able to to translate a fragment using different
 * language package.
 *
 * @see IFragmentTranslator
 */
public class LanguagePackageFragmentTranslator implements IFragmentTranslator
{
  private ILanguagePackage languagePackage;

  /**
   * Creates the LanguagePackageFragmentTranslator. The first parameter
   * defines the language package to use.
   *
   * @param pLanguagePackage The language package to use for this translator.
   */
  public LanguagePackageFragmentTranslator(ILanguagePackage pLanguagePackage)
  {
    languagePackage = pLanguagePackage;
  }

  /**
   * @see IFragmentTranslator#getTranslatedFragment(ETranslationFragment)
   */
  @Override
  public String getTranslatedFragment(ETranslationFragment pTranslationFragment)
  {
    return languagePackage.getFragment(pTranslationFragment);
  }

  @Override
  public Locale getCompatibleLocale()
  {
    return languagePackage.getCompatibleLocale();
  }
}
