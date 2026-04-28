signature = name + parameter list -> for how to reference method
does not include return type, access modifiers -> these are for where the method can be referenced 

method body -> required unless abstract method 

4 access:
1. private - same class 
2. package access - class from same package - no keyword for this, no keyword == package access, aka package-private or default
3. protected - same package or subclass
4. public - as the name suggests

specifiers
 
1. static
2. abstract
3. final
4. default
5. synchronized
6. native 
7. strictfp

** WHILE ACCESS MODIFIERS AND OPTIONAL SPECIFIERS CAN APPEAR IN ANY ORDER, THEY MUST ALL APPEAR BEFORE THE RETURN TYPE **

final and abstract don't go together - should be obvious 

signature - types of parameters and their order, the param names have no significance, method name matters though

no limit on the number of exceptions you feel like throwing from a method, comma separated

final variable initial declaration -> value assignment not mandatory
but unchangeable once it IS assigned

making local var as final -> good practice

Varars params
1. method can have at most one varargs parameter
2. if varargs param present, must be the last param in the list 

