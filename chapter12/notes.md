modules
- packages can be grouped into these
- like a JAR file except devs get to choose which packages to expose?

JAR 
- zip file with extra info 
- .jar extension
- packages are grouped into this

JPMS - Java Platform Module System 
- format for JAR files
- partitioning of the JDK into modules
- additional command line options for Java tools


module
- group of one or more packages + a special module-info.java file
- contents of file -> module declaration

module-info.java -> root
all other .java -> in packages



javac --module-path mods 
    -d feeding
    feeding/zoo/animal/feed*.java feeding/module-info.java


-cp
--class-path
-classpath
all of these same

--module-path
-p
they're the same

--module
-m
same

need clarity on 
-classpath flag and -d flag


java --module-path module/location --module module.name/package.name.Class

jar -cvf mods/zoo.animal.feeding.jar -C feeding/.

exports 
- directive used to indicate  that a module intends for those packages to be used by Java code outside the module
- can export to specific module
exports packagename to moduleName;


requires 
- directive specifying a module is needed 
requires transitive 

opens



provides 
uses

^^ no order for any of these directives

stood out - protected - outside module - accessible to subclasses only if package is exported 


