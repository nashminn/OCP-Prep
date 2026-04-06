# Chapter 1 Notes

## File & Class Structure
- each file -> 1 public class
- file name == public class name + .java
- top-level type -> can be defined independently, ex - class

## Methods
- entry point -> main()
- signature = method name + parameter type list

## Imports
- java.lang -> auto imported
- while importing -> only 1 wildcard allowed
- no importing methods

## Compilation
- javac -d -> to specify target directory
- package structure is preserved under the requested target directory

8 built in primitive types
- single value in memory

boolean
byte 
short 
int 
long
float 
double
char

need f or F to denote a float value otherwise java assume it to be double

bit size of boolean? unspecified, dependent on the JVM where the code is being executed
short, char -> both 16 bits, short -> signed, char -> unsigned

all references are the same size regardless of their type
object -> sits on the heap without a name
object -> gets garbage collected, not the reference to it 

valueOf() -> wrapped
parseInt() -> primitive
