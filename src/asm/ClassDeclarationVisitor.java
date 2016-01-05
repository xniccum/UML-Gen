package asm;

import asm.api.IKlass;
import asm.impl.Klass;
import org.objectweb.asm.ClassVisitor;

import java.util.Arrays;

public class ClassDeclarationVisitor extends ClassVisitor {
    private KlassStorage klass;

	public ClassDeclarationVisitor(int api, KlassStorage klass){
		super(api);
        this.klass = klass;
	}

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces){
        // TODO: delete the line below
        System.out.println("Class: "+name+" extends "+superName+" implements "+Arrays.toString(interfaces));
        // TODO: construct an internal representation of the class for later use by decorators
        this.klass.setCurrentPart(new Klass(name, version,access));

        super.visit(version, access, name, signature, superName, interfaces);

    }

}
