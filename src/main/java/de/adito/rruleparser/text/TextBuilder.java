package de.adito.rruleparser.text;

import de.adito.rruleparser.text.formatting.*;
import de.adito.rruleparser.text.listing.*;
import de.adito.rruleparser.tokenizer.IRRuleTokenContainer;
import de.adito.rruleparser.tokenizer.token.*;
import de.adito.rruleparser.translation.*;

import java.time.MonthDay;

public class TextBuilder implements ITextBuilder
{
  private IFragmentTranslator fragmentTranslator;
  private IDateFormatting dateFormatting;
  private IDayListingFormatting dayListingFormatting;

  public TextBuilder(IFragmentTranslator pFragmentTranslator)
  {
    this(pFragmentTranslator, new DefaultDateFormatting(pFragmentTranslator));
  }

  public TextBuilder(IFragmentTranslator pIFragmentTranslator, IDayListingFormatting pDayListingFormatting)
  {
    this(pIFragmentTranslator, new DefaultDateFormatting(pIFragmentTranslator), pDayListingFormatting);
  }

  public TextBuilder(IFragmentTranslator pFragmentTranslator, IDateFormatting pDateFormatting)
  {
    this(pFragmentTranslator, pDateFormatting, new DefaultDayListingFormatting(pFragmentTranslator, pDateFormatting));
  }

  public TextBuilder(IFragmentTranslator pFragmentTranslator, IDateFormatting pDateFormatting, IDayListingFormatting pDayListingFormatting)
  {
    fragmentTranslator = pFragmentTranslator;
    dateFormatting = pDateFormatting;
    dayListingFormatting = pDayListingFormatting;
  }

  @Override
  public String buildText(IRRuleTokenContainer pTokenContainer)
  {
    String resultString = _buildFrequency(pTokenContainer);


    FreqToken.FreqValue freqValue = pTokenContainer.getFreq().getValue();
    if (freqValue == FreqToken.FreqValue.WEEKLY)
    {
      resultString += " " + _buildWeeklyDays(pTokenContainer);
    }
    else if (freqValue == FreqToken.FreqValue.MONTHLY && pTokenContainer.getByMontDay() != null)
    {
      resultString += " " + _buildMonthlyOnDay(pTokenContainer);
    }
    else if (freqValue == FreqToken.FreqValue.MONTHLY && pTokenContainer.getBySetPos() != null
        && pTokenContainer.getByDay() != null)
    {
      resultString += " " + _buildMonthlyOnNumberedDay(pTokenContainer);
    }
    else if (freqValue == FreqToken.FreqValue.YEARLY && pTokenContainer.getByMonth() != null
        && pTokenContainer.getByMontDay() != null)
    {
      resultString += " " + _buildYearlyOnDay(pTokenContainer);
    }
    else if (freqValue == FreqToken.FreqValue.YEARLY && pTokenContainer.getByDay() != null
        && pTokenContainer.getBySetPos() != null && pTokenContainer.getByMonth() != null)
    {
      resultString += " " + _buildYearlyOnNumbered(pTokenContainer);
    }

    // Endings
    if (pTokenContainer.getUntil() != null)
    {
      String ending = _buildUntilDateEnding(pTokenContainer);
      if (ending != null)
        resultString += ", " + ending;
    }
    else if (pTokenContainer.getCount() != null)
    {
      String ending = _buildCountEnding(pTokenContainer);
      if (ending != null)
        resultString += ", " + ending;
    }


    return _capitalizeFirstLetter(resultString.trim());
  }

  private String _capitalizeFirstLetter(String result)
  {
    String firstLetter = result.substring(0, 1);

    return firstLetter.toUpperCase() + result.substring(1);
  }

  /* ---- FREQUENCY ---- */

  private String _buildFrequency(IRRuleTokenContainer pTokenContainer)
  {
    FreqToken.FreqValue freqValue = pTokenContainer.getFreq().getValue();

    int interval = 1;
    if (pTokenContainer.getInterval() != null)
      interval = pTokenContainer.getInterval().getValue();

    String value = null;
    if (interval == 1)
    {
      switch (freqValue)
      {
        case DAILY:
          value = fragmentTranslator.getTranslatedFragment(ETranslationFragment.DAILY);
          break;
        case WEEKLY:
          value = fragmentTranslator.getTranslatedFragment(ETranslationFragment.WEEKLY);
          break;
        case MONTHLY:
          value = fragmentTranslator.getTranslatedFragment(ETranslationFragment.MONTHLY);
          break;
        case YEARLY:
          value = fragmentTranslator.getTranslatedFragment(ETranslationFragment.YEARLY);
          break;
      }
    }
    else
    {
      value = fragmentTranslator.getTranslatedFragment(ETranslationFragment.EVERY) + " " + interval + " ";
      switch (freqValue)
      {
        case DAILY:
          value += fragmentTranslator.getTranslatedFragment(ETranslationFragment.DAYS);
          break;
        case WEEKLY:
          value += fragmentTranslator.getTranslatedFragment(ETranslationFragment.WEEKS);
          break;
        case MONTHLY:
          value += fragmentTranslator.getTranslatedFragment(ETranslationFragment.MONTHS);
          break;
        case YEARLY:
          value += fragmentTranslator.getTranslatedFragment(ETranslationFragment.YEARS);
          break;
      }
    }

    if (value == null)
      return "";
    return value;
  }

  /* ---- WEEKLY ---- */

  private String _buildWeeklyDays(IRRuleTokenContainer pTokenContainer)
  {
    ByDayToken byDay = pTokenContainer.getByDay();
    if (byDay == null)
      return "";

    String result = fragmentTranslator.getTranslatedFragment(ETranslationFragment.ON) + " ";
    result += dayListingFormatting.formatDayListing(byDay.getValue().getDayList());

    return result;
  }

  /* ---- MONTHLY ---- */

  private String _buildMonthlyOnDay(IRRuleTokenContainer pTokenContainer)
  {
    ByMonthDayToken byMonthDay = pTokenContainer.getByMontDay();
    if (byMonthDay == null)
      return "";

    return fragmentTranslator.getTranslatedFragment(ETranslationFragment.ON)
        + " " + fragmentTranslator.getTranslatedFragment(ETranslationFragment.DAY) + " " + byMonthDay.getValue();
  }


  private String _buildMonthlyOnNumberedDay(IRRuleTokenContainer pTokenContainer)
  {
    ByDayToken byDayToken = pTokenContainer.getByDay();
    BySetPosToken bySetPosToken = pTokenContainer.getBySetPos();
    if (byDayToken == null || bySetPosToken == null)
      return "";

    String result = fragmentTranslator.getTranslatedFragment(ETranslationFragment.ON);
    result += " " + _translateSetPosNumber(bySetPosToken.getValue());
    result += " " + dateFormatting.formatDay(byDayToken.getValue().getDayList().get(0));

    return result;
  }

  /* ---- YEARLY ---- */
  private String _buildYearlyOnDay(IRRuleTokenContainer pTokenContainer)
  {
    ByMonthToken byMonthToken = pTokenContainer.getByMonth();
    ByMonthDayToken byMonthDayToken = pTokenContainer.getByMontDay();
    if (byMonthDayToken == null || byMonthToken == null)
      return "";

    String result = fragmentTranslator.getTranslatedFragment(ETranslationFragment.ON);
    result += " " + dateFormatting.formatMonthDay(MonthDay.of(byMonthToken.getValue(), byMonthDayToken.getValue()));

    return result;
  }

  private String _buildYearlyOnNumbered(IRRuleTokenContainer pTokenContainer)
  {
    ByDayToken byDayToken = pTokenContainer.getByDay();
    BySetPosToken bySetPosToken = pTokenContainer.getBySetPos();
    ByMonthToken byMonthToken = pTokenContainer.getByMonth();
    if (byDayToken == null || bySetPosToken == null || byMonthToken == null)
      return "";

    String result = fragmentTranslator.getTranslatedFragment(ETranslationFragment.ON) + " " + _translateSetPosNumber(bySetPosToken.getValue());
    result += " " + dateFormatting.formatDay(byDayToken.getValue().getDayList().get(0));
    result += " " + fragmentTranslator.getTranslatedFragment(ETranslationFragment.OF);
    result += " " + dateFormatting.formatMonth(byMonthToken.getValue());

    return result;
  }

  /* ---- ENDINGS ---- */

  private String _buildUntilDateEnding(IRRuleTokenContainer pTokenContainer)
  {
    UntilToken untilToken = pTokenContainer.getUntil();
    if (untilToken == null)
      return null;

    String result = fragmentTranslator.getTranslatedFragment(ETranslationFragment.UNTIL);
    result += " " + dateFormatting.formatFullDate(untilToken.getValue().getLocalDateTime().toLocalDate());

    return result;
  }

  private String _buildCountEnding(IRRuleTokenContainer pTokenContainer)
  {
    CountToken countToken = pTokenContainer.getCount();
    if (countToken == null)
      return null;

    if (countToken.getValue() == 1)
      return null;

    return countToken.getValue() + " " + fragmentTranslator.getTranslatedFragment(ETranslationFragment.TIMES);
  }


  /* ---- TRANSLATE UTILS ---- */
  private String _translateSetPosNumber(int number)
  {
    if (number == -1)
      return fragmentTranslator.getTranslatedFragment(ETranslationFragment.LAST);
    if (number == 1)
      return fragmentTranslator.getTranslatedFragment(ETranslationFragment.FIRST);
    if (number == 2)
      return fragmentTranslator.getTranslatedFragment(ETranslationFragment.SECOND);
    if (number == 3)
      return fragmentTranslator.getTranslatedFragment(ETranslationFragment.THIRD);
    if (number == 4)
      return fragmentTranslator.getTranslatedFragment(ETranslationFragment.FOURTH);
    return "";
  }
}
