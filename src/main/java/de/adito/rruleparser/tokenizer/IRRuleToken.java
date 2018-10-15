package de.adito.rruleparser.tokenizer;

public interface IRRuleToken<T>
{
  String getName();

  T getValue();
}
