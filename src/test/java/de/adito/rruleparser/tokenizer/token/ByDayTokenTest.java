package de.adito.rruleparser.tokenizer.token;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}