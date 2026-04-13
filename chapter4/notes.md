String 
- implements interface CharSequence
- + 
--- both numeric -> number addition
--- either operand not numeric -> string concatenation
--- evaluation from left to right


System.out.println("c"+ 1 + 2); -> c12
System.out.println( 1 + 2 + "c"); -> 3c

immutable

length()
charAt(index) // start from 0 obviously
indexOf(c'har')

code point size > char size 
codePointAt() // this is new to me 


substring - book claims this is the trickiest
substring(start index, optional end before index) -> returns group of characters 

char can be passed to an int parameter type


public String toLowerCase()
public String toUpperCase()

returns news, original stays same


equals(Object obj)
equalsIgnoreCase(String str)

toString()

- the following two need to be overridden at the same time sort of
equals(Object) 
hashCode()
because a.equals(b) <-> a.hashCode() == b.hashCode()
will cause issues with HashMap and HashSet


startsWith()
endsWith() 

contains()

replace(char, char)
replace(charSeq, charSeq)

white space removal: (\t, \r, \n)
trim()
strip() = trim() + unicode support

Unicode whitespace character = '\u2000'

variations of strip: 
stripLeading()
stripTrailing()

indent(noOfIndents) -> extra shit it does for some reason: normalizes the whitespace
normalizing? 1. adds \n to end of string, 2. any line break -> \n (fuck you windows), 

stripIndent()
- gets rid of all incidental whitespaces

apparently both of these normalize, BUT STRIP INDENT DOES NOT ADD \n AT THE END IF MISSING

isEmpty() - "" -> true -> absolutely nothing
isBlank() - "", "   " -> true -> tbh they're all blank

either String.format("alsdjfoasdf %s %d", theStr, theInt) 
or "alsdjfoasdf %s %d".formatted(theStr, theInt);

%n -> for system dependent line separators (fu windows)

%f -> default = 6 digits after decimal point 
%.digitf - rounding applied, not truncation 
total length of output %totaldigits.

%012f, [o -> 00003.141593] including zero total 12, since after . count not specified, default 6 digits applied 

method chaining

StringBuilder - NOT immutable
StringBuffer -> thread support, sth negative, forgot already
new StringBuilder(size?)

appendCodePoint(int)
