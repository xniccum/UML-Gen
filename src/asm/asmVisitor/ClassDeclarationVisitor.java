package asm.asmVisitor;

import asm.KlassStorage;
import asm.StorageApi.IKlassPart;
import asm.impl2.Interphace;
import asm.impl2.Klass;
import asm.impl2.SuperKlass;
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
