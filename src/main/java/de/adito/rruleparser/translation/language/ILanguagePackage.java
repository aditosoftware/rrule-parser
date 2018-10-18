package de.adito.rruleparser.translation.language;

import de.adito.rruleparser.translation.ETranslationFragment;

import java.util.Locale;

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

  /**
   * Returns the locale that is compatible with this
   * language package.
   *
   * @return Locale of language package.
   */
  Locale getCompatibleLocale();
}
