package asm.asmVisitor.StandardVisitors;

import asm.StorageApi.IKlass;
import asm.impl2.StandardDataObjects.Field;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Type;

public class ClassFieldVisitor extends ClassVisitor{
	private IKlass klass;
	public ClassFieldVisitor(int api, IKlass klass){
		super(api);
		this.klass = klass;
	}
	
	public ClassFieldVisitor(int api, ClassVisitor decorated, IKlass klass) {
		super(api, decorated);
		this.klass = klass;
		// TODO Auto-generated constructor stub
	}
	
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		String type = Type.getType(desc).getClassName();

		this.klass.addKlassPart(new Field(access, name, signature, type));

		return toDecorate;
	}

}
