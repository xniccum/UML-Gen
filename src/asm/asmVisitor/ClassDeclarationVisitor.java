package asm.asmVisitor;

import asm.KlassStorage;
import asm.StorageApi.IKlass;
import asm.StorageApi.IKlassPart;
import asm.impl2.Interphace;
import asm.impl2.Klass;
import asm.impl2.SuperKlass;
import org.objectweb.asm.ClassVisitor;

import java.util.Arrays;

public class ClassDeclarationVisitor extends ClassVisitor {
    private IKlass klass;

	public ClassDeclarationVisitor(int api, IKlass klass){
		super(api);
        this.klass = klass;
	}

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces)
    {
        System.out.println("Class: "+name+" extends "+superName+" implements "+Arrays.toString(interfaces));
        //IKlassPart part = new Klass(name, version,access);
        klass.setAccess(access);
        klass.setName(name);
        klass.setVersion(version);
        if(superName != "")
            klass.addKlassPart(new SuperKlass(superName));
        if(interfaces.length != 0)
            klass.addKlassPart( new Interphace(interfaces));

        super.visit(version, access, name, signature, superName, interfaces);

    }

}
