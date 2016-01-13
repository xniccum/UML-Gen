package asm.asmVisitor;

import asm.KlassStorage;
import asm.UmlOutputStream;
import asm.impl2.Klass;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedOutputStream;

public class DesignParser {
	/**
	 * Reads in a list of Java Classes and reverse engineers their design.
	 * 
	 * @param args: the names of the classes, separated by spaces. 
	 * 		For example: java DesignParser java.lang.String edu.rosehulman.csse374.ClassFieldVisitor java.lang.Math
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException{
		OutputStream dotOut = new FileOutputStream("inputOutput/output.dot");
        UmlOutputStream umlOut = new UmlOutputStream(dotOut);

		String s ="strict digraph G {\n" +
				"    fontname = \"Bitstream Vera Sans\"\n" +
				"    fontsize = 8\n" +
				"\n" +
				"    node [\n" +
				"    fontname = \"Bitstream Vera Sans\"\n" +
				"    fontsize = 8\n" +
				"    shape = \"record\"\n" +
				"    ]\n" +
				"\n" +
				"    edge [\n" +
				"    fontname = \"Bitstream Vera Sans\"\n" +
				"    fontsize = 8\n" +
				"    ]\n";
        umlOut.write(s.getBytes());
        //OutputStream uml = new PipedOutputStream();
		//UmlOutputStream umlOut = new UmlOutputStream(uml);

		for(String className: args){
			//KlassStorage storage = new KlassStorage();
            Klass klass = new Klass();
			// ASM's ClassReader does the heavy lifting of parsing the compiled Java class
			ClassReader reader=new ClassReader(className);

			// make class declaration visitor to get superclass and interfaces
			ClassVisitor decVisitor = new ClassDeclarationVisitor(Opcodes.ASM5, klass);
			
			// DECORATE declaration visitor with field visitor
			ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, decVisitor, klass);
			
			// DECORATE field visitor with method visitor
			ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor, klass);

			// Tell the Reader to use our (heavily decorated) ClassVisitor to visit the class
			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
			//string.append(storage.toString());
			//dotOut.write(storage.toString().getBytes());
            umlOut.setClassName(klass.getName());
			umlOut.write(klass);

		}
		//System.out.println(string.toString());
       // dotOut.write(umlOut.);
        umlOut.write("}".getBytes());
        umlOut.close();
	}
}