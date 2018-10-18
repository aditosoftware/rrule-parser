package de.adito.rruleparser.translation.language;

import de.adito.rruleparser.translation.ETranslationFragment;

import java.util.Locale;

/**
 * Implements the english translation.
 */
public class EnglishTranslation implements ILanguagePackage
{
  @Override
  public String getFragment(ETranslationFragment pTranslationFragment)
  {
    switch (pTranslationFragment)
    {
      case DAILY:
        return "daily";
      case WEEKLY:
        return "weekly";
      case MONTHLY:
        return "monthly";
      case YEARLY:
        return "annually";
      case DAYS:
        return "days";
      case WEEKS:
        return "weeks";
      case MONTHS:
        return "months";
      case YEARS:
        return "years";
      case DAY:
        return "day";
      case FIRST:
        return "first";
      case SECOND:
        return "second";
      case THIRD:
        return "third";
      case FOURTH:
        return "fourth";
      case LAST:
        return "last";
      case UNTIL:
        return "until";
      case TIMES:
        return "times";
      case ON:
        return "on";
      case EVERY:
        return "every";
      case OF:
        return "of";
      default:
        return "";
    }
  }

  @Override
  public Locale getCompatibleLocale()
  {
    return Locale.ENGLISH;
  }
}
