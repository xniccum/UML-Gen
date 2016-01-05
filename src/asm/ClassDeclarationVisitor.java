package asm;

import asm.api.IKlass;
import asm.api.IKlassPart;
import asm.impl.Interphace;
import asm.impl.Klass;
import asm.impl.SuperKlass;
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
        IKlassPart part = new Klass(name, version,access);
        if(superName != "")
            part = new SuperKlass(part, name);
        if(interfaces.length != 0)
            part = new Interphace(interfaces, part);

        this.klass.setCurrentPart(part);


        super.visit(version, access, name, signature, superName, interfaces);

    }

}
