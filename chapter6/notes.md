will see records for the first time 

class - keyword 
only extendable (?) if not marked final 
inheritance is transitive - obvious but anyway
direct sub/superclass

recap: 4 access levels : public, protected, package (no keyword for this), private

class modifiers - 
final - no extending this class
abstract - may contain abstract methods, and requires a concrete sublcass to instantiate
sealed (NEW TO ME) - final to all but specific list of classes 
(sounds kinda racist ngl) 
non-sealed (ALSO NEW TO ME) - subclass of a sealed class permits potentially unnamed sublcasses 
static - used for static nested classes defined within a class

wow only a preview to what's coming in the next chapter (sealed, non sealed) - not happening here, just a deep dive into what you know already 

java supports single inheritance 
and multiple levels of inheritance 
BUT ABSOLUTELY NO SUPPORT FOR MULTIPLE INHERITANCE 
java choosing avoidance over anything else 

PRIVATE MEMBERS ARE NEVER INHERITED 
mind fuck moment: Java uses the most granular scope
so when it sees the following: 
public void setColor(String color) {
    color = color;
}
it thinks that the param is being assigned the same val as the param itself - i feel betrayed, guess that's what i get for using @Getter and @Setter so often ;-;
same param name as an instance var makes the usage of 'this' mandatory 

super only moves up one inheritance level

this vs super 
q to self: a extends b, b extends c
c has property x 
b has property x 
a also has property x 
all x protected
can we access x of c when an object of type a is created ?

JAVA IS CASE SENSITIVE 
constructor overloeading
instantiation
when Java sees the new keyword, it allocates memory for the new object 

default constructor (default no-args constructor)- also empty body
'generated' - happens during compile time 
IMPORTANT: THE COMPILER ONLY INSERTS THE DEFAULT CONSTRUCTOR WHEN NO CONSTRUCTORS ARE DEFINED 
When this() is used with parantheses, Java calls another constructor on the same instance of the class 
apparently this, and this() are very different 
this -> refers to isntance of the class
this() -> refers to a constructor call withiin the class 
if you call this(), it must be the first statement in the constructor 
----- as a result there can be only one call to this() in any constructor, no matter what, even irrelevant print statement must come later on 
and obviously, can't call yourself to create infinite recursion 
to summarize-
1. class can contain many overloaded constructors, provided the signature for each is distinct
2. compiler inserts default no-args constructor if no constructors are declared 
3. if a constructor calls this(), then it must be the first line of the constructor
4. java doesn't allow cyclic constructor calls
5. if a constructor calls super() or this(), then it must be the first line of the constructor 
6. if the constructor does not contain a this() or super() reference, then the compiler automatically inserts super() with no arguments as the first line of the constructor 

the first statement of every constructor is a call to a parent constructor using super() or another constructor in the class using this()
super != super()
super -> used to reference members of the parent class 
super() -> calls parent constructor 
a lot of shit happening with calls to super()
super() -> ALWAYS REFERS TO THE MOST DIRECT PARENT 

one of the most important rules with class initialization is that it happens at most once for each class

Initializing class X:
1. initialize the superclass of X
2. process all static variable declarations in the order in which they appear in the class 
3. process all static initializers in the order in which they appear in the class 

a class must be initialized before it is referenced or used
the class containing the program entry point (main method) 
is loaded before the main() method is executed

initialize instance of X:
1. initialize Class X if it has not been previously initialized 
2. initialize the superclass instance of X
3. process all instance variable declarations in the order in which they appear in the class 
4. process all instance initializers in the order in which they appear in the class
5. initialize the constructor, including any overloeaded constructors referenced with this() 

GiraffeFamily and Okapi examples were tough, need practice here

to remember:
1. class initialized at most once by the JVM before it is referenced or used 
2. static final variables must be assigned a value exactly once, either when they are declared or in a static initializer
3. all final fields must be assigned a value exactly once, either whey they are declared, in an instance initializer, or in a constructor 
4. non-final static and instance variables defined without a value are assigned a default value based on their type 
5. the order of initialization is as follows: variable declarations, then initializers, and final contructors



