NIO.2 >>> I/O -> preferred approach

NIO - non-blocking input / output, sometimes new I/O

file and directories uwu 

File class 
Path interface 

topmost directory in the file system - root directory
on Linux: /
on Windows: C:\

path - representation of a fiel or directory within a file system 
parent/child

Java offers system property to retreive the local separator character for the current environment 

System.getProperty("file.separator")

absolute path - from root
relative - from current working directory 

path starts with: 
/ -> absolute path 
C: -> (starting with drive letter, it can be other drives as well?), absolute path

path symbols:
. -> reference to the current directory
.. -> reference to the parest of the current directory 

symbolic link 
- a special file within a file system that serves as a reference or pointer to another file or directory 
- has the word link in it, but is actually a file 
- transparent to the user (os takes care of resolving the reference to the actual file)
- I/O APIs don't support symlinks, BUT NIO.2 includes full support fo creating, detecting, and navigating symbolic links with the file systems

creating a file or path 
