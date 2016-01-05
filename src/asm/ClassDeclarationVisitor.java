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
	

}
