package de.adito.rruleparser.translation.language;

import de.adito.rruleparser.translation.ETranslationFragment;

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
      case MONDAY:
        return "monday";
      case TUESDAY:
        return "tuesday";
      case WEDNESDAY:
        return "wednesday";
      case THURSDAY:
        return "thursday";
      case FRIDAY:
        return "friday";
      case SATURDAY:
        return "saturday";
      case SUNDAY:
        return "sunday";
      case JANUARY:
        return "january";
      case FEBRUARY:
        return "february";
      case MARCH:
        return "march";
      case APRIL:
        return "april";
      case MAY:
        return "may";
      case JUNE:
        return "june";
      case JULY:
        return "july";
      case AUGUST:
        return "august";
      case SEPTEMBER:
        return "september";
      case OCTOBER:
        return "october";
      case NOVEMBER:
        return "november";
      case DECEMBER:
        return "december";
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
}
