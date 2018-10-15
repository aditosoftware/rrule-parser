# RRule parser
RRule parser is a small java library which lets you convert a [iCalendar RRule](https://tools.ietf.org/html/rfc2445#section-4.3.10) into human readable text.
##Usage
```java 
RRuleParser ruleParser = new RRuleParser();

String parseResult = ruleParser.parseRRule("FREQ=MONTHLY;BYSETPOS=4;BYDAY=SU;INTERVAL=5");
// Every 5 months on fourth sunday
```