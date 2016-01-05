package asm;

import asm.api.IKlassPart;
import asm.impl.Interphace;
import asm.impl.Klass;
import asm.impl.SuperKlass;
import org.objectweb.asm.ClassVisitor;

import java.util.Arrays;

public class ClassDeclarationVisitor extends ClassVisitor {
	private IKlassPart klass;

	public ClassDeclarationVisitor(int api){
		super(api);
	}
	
	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces){
		// TODO: delete the line below
		System.out.println("Class: "+name+" extends "+superName+" implements "+Arrays.toString(interfaces));
		// TODO: construct an internal representation of the class for later use by decorators
		this.klass = new Klass(name, version, access);
		if(superName != ""){
			this.klass = new SuperKlass(this.klass, superName);
		}
		if(interfaces.length != 0){
			this.klass = new Interphace(interfaces, this.klass);
		}


		super.visit(version, access, name, signature, superName, interfaces);
		
	}
}
