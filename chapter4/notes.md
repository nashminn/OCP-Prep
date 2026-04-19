String 
- implements interface CharSequence
- + 
--- both numeric -> number addition
--- either operand not numeric -> string concatenation
--- evaluation from left to right


System.out.println("c"+ 1 + 2); -> c12
System.out.println( 1 + 2 + "c"); -> 3c

immutable

length() -> METHOD FOR STRING
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
insert()

delete(st, ed)
deleteCharAt()

replace() -> different for StringBuilder (delete + insert)
reverse() -> REVERSE EXISTS FOR STRINGBUILDER AND NOT STRING

it says i saw earlier that equals() uses logical equality rather than object equality for String objects
but i have no memory of this

StringBuilder authors were lazy and didn't bother to implement the equals method properly so now to check equality 
you have to first .toString() that thing and then check with .equals()

String pool aka the intern pool -> location in JVM that collects all strings
string pool has literal values and constanst that appear in program

intern() -> will use an object in the string pool if one is present

even as i'm reading, compile time consts are getting confusing

never use intern() or == -> they're for the exam 

array -> area of memory on heap

char[] letters; -> a reference variable, not a primitive one

array == ordered list uwu

int[] nums = new int[3];
int[] more = {12, 13, 53}; // anonymous array 
int conf[], sth; // valid apparently

arrays are objects so can call .equals() on them 
but it does not look at the elements of the array 
just the reference [should be obvious by now ig]

to print an array nicely:
Arrays.toString(arra);
arra.toString() returns other stuff arrayType;@hashCode

array doesn't allocate space for the objects, it allocates space for the reference to those objects

arra.length -> prop, not a method the way it is for String 
and it doesn't care if the array is populated or not 

Arrays.sort()
Arrays.binarySearch(arra, targetNumber)

array not sorted -> output unpredictable

equals -> == - object reference check, otherwise size + element + order
compare -> 0 means equals, negative -> 1st smaller, positive -> 1st larger
and a bunch of other stuff

null is the smallest 

Arrays.compare() -> both must be of the same type

mismatch -> equal - returns -1 
otherwise returns first index where they differ

int [][] args = new int[2][];
args[0] = new int[5];
args[1] = new int[3];

rounding double -> returns long 
rounding float -> returns int

ceil, floor (double) -> return double 

pow(double num, double exponent) -> return double
Math.random() -> return double 

BigInteger
BigDecimal -> calcs involving money
^^ immutable classes

use valueOf for these two


LocalDate - no time, no time zone
LocalTime - no date, no time zone
LocalDateTime - date + time, no time zone
ZonedDateTime - date + time + time zone

now() for all of them uwu
UTC == GMT
+02:00 == GMT + 2 == UTC + 2

date and time classes have private constructors 
new LocalDate() // DOES NOT COMPILE

date and time classes are immutable

With LocalDate
plusDays
plusWeeks
plusMonths
plusYears

the with methods -> to create a copy of an object with specified fields altered to specified value 

LocalDate + .atTime() -> LocalDateTime
LocalTime + .atDate() -> LocalDate - idk why not LocalDateTIme
LocalDateTime + .atZone(zoneId) -> ZonedDateTime

cannot chain methods when creating a Period
Period methods are static 

Period >= day -> output with P
Duration -> days, hours, minutes, seconds, nanoseconds
-> output with PT

ChronoUnit - has sth called HALF_DAYS (12 hours)

-- too many small details in this section

