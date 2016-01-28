# UML-Gen

## Instructions
####UML
Place, alter or select a .text file in config/.
Go to src/asm/FilePaths.java and add or select a variable with the filepath string
Open up designParser and set INPUT_FILE_PATH equal to FilePaths.<Path var name>
Run DesignParser
A DOT will be generated and output to the file path described in DesignParser.OUTPUT_PATH
 
Enter a terminal:
```bash
    cd ../output_path_folder
    dot -T png -o <DOT file name> <Output Image Name>
```

####Sequence Diagram
Run the main method in the Sequence Main class. This method takes in a fully quality method/class name and a max depth parameter. The resultant Sd file can be found in the input output folder and loaded into sd edit.


## Milestone 4 Design
Our application has several small design changes.  IDesignType was added as interface for IKlassParts that reference design traits. 
SingletonDesign was created extending KlassDecorator and implementing DesignType. Additional a new visitor, SingletonClassVisitor was created to decorate Klass objects with the SingletonDesign Object.
![Alt text](images/Milestone-4-UML.png "Milestone Four Design")

## Milestone 3 Design
Our application has several design types.
Our Asm parser uses a decorator pattern to decorate visitor pattern visitors. 

These visitors utilize our internal data storage which is a slight play on decorator allowing for multiple unique objects and individual object ownership.
This data structure is also traversable.

Finally we one of two lambda style visitors for visit the data structure depending on if we are looking to generate UML diagrams or sequence diagrams.
![Alt text](images/SimpleDesignDiagram.png "Milestone Three Design")

## Pre Milestone 3 Design
Our design is based off of a culmination of two design patterns. We kept the initial visitor pattern from asm and then, using the decorator pattern, we created a storage system to represent the class structure . 

In this pattern interfaces, a superclass, fields, and methods are all added as decorators to a base class object as parses through the java class files. 

We then created a KlassStorage instance to allow each set of interfaces a shared memory system on which to decorate the class. This keeps the instance updating with each call.

Our design will be simple to adjust and is flexible at runtime. The only issues is the shared memory is not completely thread safe, nor do we use thread safe data structures. 

##M2:
Uses and Association Arrows were introduced
![Alt text](images/??.png "Milestone Two Design")



## Who Did What
###M1:
Everyone alternated pair programming on Doolan's Desktop for the design and the first part of the implementation.
######Austin
Started working on the automated testing once there was enough working code to test.
######Doolan and Davis
Built and designed the initial application

 
 

###M2:
Doolan's Desktop was used for the bulk of the coding.
#####Doolan and Davis
Doolan took care of the initial method implantation, the RegEx and String parsing.
Davis took care of the base field alterations implementations.
Davis and Doolan read through and attempted to trouble shoot the following ASM 5.0 class documents:
+MethodVisitor
..+visitLocalVariableAnnotation
..+visitVarInsn
+ClassVisitor
+ClassReader
+CodeReader
#####Niccum
Created tests that established the correct behavior of arrow detection code
Visually tested the  Abstract Factory PizzaStore
Updated UML diagrams of our code

###M3:
For milestone 3, our team did a semi-complete redesign of milestone 1 and 2 in order to better utilize design principals and make the application more versatile. 
#####Doolan
Doolan started off this milestone by refactoring the base design. 
He removed and added dependants, removed all of the print methods in instances of klassparts and added and additional Lambda Style visitor pattern. 
He also visually tested these changes to make sure it worked.
#####Davis 
Davis then took this modified design and added the ability to sub traverse methods by adding an ASM method visitor instance. 
He got the project up to Milestone 2.
#####Doolan and Davis
Doolan and Davis then took the code further to address the milestone 3 requirements. 
Doolan worked on writing a constructor function to initiate the limited traversal using the ASM visitors and design structure.
Davis worked on learning SdEdit and the print pattern needed. Doolan and Davis then debugged and integrated the code
#####Niccum
Through the milestone Niccum pulled down the changes to the code and implemented automated unit tests. 
He also manually generated diagrams to use as a baseline for the project code. 
Finally he assisted in Debugging attempts and visually tested the outputted diagrams. 


###M4:
For milestone 3, our team made small design changes to milestone 3 code to add design types
####Disclaimer:
This code was not shown to Chandan on demo day due to complications with the asm files. The features were working, the asm issues have been addressed and little to no modifications has been changed to the M4 specific code. 
#####Davis, Doolan, and Niccum
Met and white boarded the design
#####Doolan
Doolan created the new classes and interfaces. He also wrote the UMLOutputStream lambda listeners for design and singleton IKlassParts. 

#####Davis 
Davis coded the SingletonClassVisitor and implemented the logic to detect if the class is a singleton.

#####Niccum
Through the milestone Niccum pulled down the changes to the code and implemented automated unit tests. 
He also manually generated diagrams to use as a baseline for the project code. 
Finally he assisted in Debugging attempts and visually tested the outputed diagrams. 
