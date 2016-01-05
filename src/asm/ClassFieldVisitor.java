package asm;

import asm.impl.Field;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Type;

public class ClassFieldVisitor extends ClassVisitor{
	private KlassStorage klass;
	public ClassFieldVisitor(int api, KlassStorage klass){
		super(api);
		this.klass = klass;
	}
	
	public ClassFieldVisitor(int api, ClassVisitor decorated, KlassStorage klass) {
		super(api, decorated);
		this.klass = klass;
		// TODO Auto-generated constructor stub
	}
	
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		String type = Type.getType(desc).getClassName();

		this.klass.setCurrentPart(new Field(this.klass.getCurrentPart(), access, name, type));

		return toDecorate;
	}

}
