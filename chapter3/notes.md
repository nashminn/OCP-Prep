code that first checks if a variable is of a particular type and then immediately casts to that type is extremely common in the java world, so a short syntax for that exists now: 

number insteanceof Integer data -> number instanceof Integer ? -> a = (Integer) number
here data is the pattern variable

^^ avoid ClassCastException

before 21: subtypeObject instanceof type tp -> not allowed

flow scoping 
- variable is only in scope when the compiler can definitively determine its type 
- batshit crazy stuff - scoping var not strictly limited to if / else block :shocked:

two flavours of switch : statement (no return value), expression (returns value)

String hmm = swtich(sth) {
    case 0 -> "uwu";
    case 1 -> ":p";
    default -> "XD";
};

mixed usage of : and -> does not compile 
with expressions, default is OFTEN required 
because they must return some value 

switch statement is not required to contain any case clauses
ATTENTION: no support for boolean, and long [neither for float, and double but that should be obvious]

from java21+, any object type can be used as a switch variable, provided that pattern matching is used

case value -> MUST BE compile time constant 
(usable: literals, enum constants, final constant variables) (no method execution for value whatsoever)
expressions allowed given that they can be resolved to a value at runtime

data type for case clauses must match the data type of the switch variable

case EnumName.ENUM1, case ENUM1 -> both valid 

with expressions -> match only a single branch - so breaks not needed here 

some batshit ambiguity there with statements that look like expressions 

switch "expression" needs to be exhaustive
statements have to be exhaustive too at times

3 possible ways to exhaust:
1. if enum: write for all possible values
2. add a default case
3. with pattern matching, cover all possible patterns


yield == return within a switch block

