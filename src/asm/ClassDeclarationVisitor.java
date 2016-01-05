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
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces)
    {
        System.out.println("Class: "+name+" extends "+superName+" implements "+Arrays.toString(interfaces));
        IKlassPart part = new Klass(name, version,access);
        if(superName != "")
            part = new SuperKlass(part, superName);
        if(interfaces.length != 0)
            part = new Interphace(interfaces, part);
        this.klass.setCurrentPart(part);


        super.visit(version, access, name, signature, superName, interfaces);

    }

}
