package de.adito.rruleparser.tokenizer.token;

import de.adito.rruleparser.tokenizer.IRRuleToken;

import java.time.LocalDate;

public class UntilToken implements IRRuleToken<LocalDate>
{
  private LocalDate localDate;

  public UntilToken(Object pLocalDate)
  {
    localDate = (LocalDate) pLocalDate;
  }

  @Override
  public String getName()
  {
    return "UNTIL";
  }

  @Override
  public LocalDate getValue()
  {
    return localDate;
  }
}
