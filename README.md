# UML-Gen
## Design
Our design is based off of a culmination of two design patterns. We kept the intial visitor pattern from asm and then, using the decorator pattern, we created a storage system to represent the class structure . 

In this pattern interfaces, a superclass, fields, and methods are all added as decorators to a base class object as parses through the java class files. 

We then created a KlassStorage instance to allow each set of interfaces a shared memory system on which to decorate the class. This keeps the instance updating with each call.

Our design will be simple to adjust and is flexable at runtime. The only issues is the shared memory is not completely thread safe, nor do we use thread safe datastructures. 



## Who Did What
Everyone alternated pair programming on Doolan's PC for the design and the first part of the implementation. Austin then started working on the automated testing once there was enough working code to test. Doolan and Davis then alternated on Doolan's PC finishing the project implementation and fixing bugs.

## Instructions
The main class is run with the paths to the class names as argument strings. The DOT file result can then be passed into GraphViz to generate the UML image.