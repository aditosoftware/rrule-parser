package de.adito.rruleparser.tokenizer.token;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ByDayTokenTest
{

  @Test
  void testGetByDayOfWeekSingle()
  {
    assertEquals("MO", ByDayToken.getByDayOfWeek(DayOfWeek.MONDAY));
    assertEquals("FR", ByDayToken.getByDayOfWeek(DayOfWeek.FRIDAY));
  }

  @Test
  void testGetbyDayofWeekList()
  {
    assertEquals("MO,FR,SA", ByDayToken.getByDayOfWeek(DayOfWeek.MONDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY));
  }

  @Test
  void testDayListEquals()
  {
    ByDayToken.DayList firstDayList = new ByDayToken.DayList(Arrays.asList(DayOfWeek.FRIDAY, DayOfWeek.WEDNESDAY, DayOfWeek.MONDAY));
    ByDayToken.DayList secondDayList = new ByDayToken.DayList(Arrays.asList(DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY, DayOfWeek.MONDAY));

    assertEquals(firstDayList, secondDayList);
  }

  @Test
  void testDayListShouldNotEqual()
  {
    ByDayToken.DayList firstDayList = new ByDayToken.DayList(Arrays.asList(DayOfWeek.FRIDAY, DayOfWeek.WEDNESDAY, DayOfWeek.MONDAY));
    ByDayToken.DayList secondDayList = new ByDayToken.DayList(Arrays.asList(DayOfWeek.WEDNESDAY, DayOfWeek.MONDAY));

    assertNotEquals(firstDayList, secondDayList);
  }
}