package de.adito.rruleparser.translation.language;

import de.adito.rruleparser.translation.ETranslationFragment;

/**
 * Describes a lagnauge package which is able to provide the translation
 * for each {@link ETranslationFragment}.
 *
 * @see EnglishTranslation
 */
public interface ILanguagePackage
{
  /**
   * Returns the translated string for the given {@link ETranslationFragment}.
   *
   * @param pTranslationFragment Fragment to translate.
   * @return Translated fragment.
   */
  String getFragment(ETranslationFragment pTranslationFragment);
}
