package de.adito.rruleparser.tokenizer.token;

import de.adito.rruleparser.tokenizer.IRRuleToken;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UntilToken implements IRRuleToken<UntilToken.ValueWrapper>
{
  public static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");

  private ValueWrapper valueWrapper;

  public UntilToken(Object pValueWrapper)
  {
    valueWrapper = (ValueWrapper) pValueWrapper;
  }

  @Override
  public String getName()
  {
    return "UNTIL";
  }

  @Override
  public ValueWrapper getValue()
  {
    return valueWrapper;
  }

  public static class ValueWrapper
  {
    private LocalDateTime localDate;

    public ValueWrapper(LocalDateTime LocalDateTime)
    {
      localDate = LocalDateTime;
    }

    public LocalDateTime getLocalDateTime()
    {
      return localDate;
    }

    @Override
    public String toString()
    {
      return getLocalDateTime().format(DATE_FORMAT);
    }
  }
}
