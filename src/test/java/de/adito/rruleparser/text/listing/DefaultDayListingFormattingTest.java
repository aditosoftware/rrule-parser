package de.adito.rruleparser.text.listing;

import de.adito.rruleparser.text.formatting.DefaultDateFormatting;
import de.adito.rruleparser.translation.LanguagePackageFragmentTranslator;
import de.adito.rruleparser.translation.language.EnglishTranslation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultDayListingFormattingTest
{

  private static IDayListingFormatting dayListingFormatting = new DefaultDayListingFormatting(
      new DefaultDateFormatting(new LanguagePackageFragmentTranslator(new EnglishTranslation())));

  private static Stream<Arguments> createTestCases()
  {
    return Stream.of(
        Arguments.of("Monday - Friday", Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
                                                      DayOfWeek.THURSDAY, DayOfWeek.FRIDAY)),
        Arguments.of("Monday, Tuesday", Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY)),
        Arguments.of("Monday - Wednesday", Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY)),
        Arguments.of("Mon, Tue, Wed, Fri, Sat, Sun", Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
                                                                   DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)),
        Arguments.of("Mon, Wed, Fri, Sun", Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY, DayOfWeek.SUNDAY)),
        Arguments.of("Monday - Sunday", Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
                                                      DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)),
        Arguments.of("Mon, Tue, Thu, Fri", Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY)),
        Arguments.of("Mon, Tue, Wed, Sat", Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.SATURDAY))
    );
  }

  @ParameterizedTest
  @MethodSource("createTestCases")
  void testChainDetecting(String expectingString, List<DayOfWeek> pDayOfWeekInput)
  {
    assertEquals(expectingString, dayListingFormatting.formatDayListing(pDayOfWeekInput));
  }
}