package de.adito.rruleparser.translation;

/**
 * Describes a translation provider which should be able to translate the
 * required text blocks.
 *
 * @see LanguagePackageFragmentTranslator
 */
public interface IFragmentTranslator
{
  /**
   * Translates the given {@link ETranslationFragment}.
   *
   * @param pTranslationFragment Fragment to translate.
   * @return The translated fragment.
   */
  String getTranslatedFragment(ETranslationFragment pTranslationFragment);
}
