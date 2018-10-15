package de.adito.rruleparser.translation;

import de.adito.rruleparser.translation.language.ILanguagePackage;

public class FragmentTranslator implements IFragmentTranslator
{
  private ILanguagePackage languagePackage;

  public FragmentTranslator(ILanguagePackage pLanguagePackage)
  {
    languagePackage = pLanguagePackage;
  }

  @Override
  public String getTranslatedFragment(ETranslationFragment pTranslationFragment)
  {
    return languagePackage.getFragment(pTranslationFragment);
  }
}
