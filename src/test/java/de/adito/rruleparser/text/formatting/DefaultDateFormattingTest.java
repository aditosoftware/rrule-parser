package de.adito.rruleparser.text.formatting;

import de.adito.rruleparser.translation.LanguagePackageFragmentTranslator;
import de.adito.rruleparser.translation.language.EnglishTranslation;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultDateFormattingTest
{
  @Test
  void testFullDateFormatting()
  {
    IDateFormatting dateFormatting = new DefaultDateFormatting(new LanguagePackageFragmentTranslator(new EnglishTranslation()));

    String formatted = dateFormatting.formatFullDate(LocalDate.of(2018, 1, 1));

    assertEquals("01 Jan 2018", formatted);
  }

  @Test
  void testMonthDayFormatting()
  {
    IDateFormatting dateFormatting = new DefaultDateFormatting(new LanguagePackageFragmentTranslator(new EnglishTranslation()));

    String formatted = dateFormatting.formatMonthDay(MonthDay.of(1, 1));

    assertEquals("January 01", formatted);
  }
}