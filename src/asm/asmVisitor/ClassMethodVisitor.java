package asm.asmVisitor;

import asm.KlassStorage;
import asm.impl.Argument;
import asm.impl2.Method;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.util.ArrayList;

public class ClassMethodVisitor extends ClassVisitor
{
	private KlassStorage klass;
	public ClassMethodVisitor(int api, KlassStorage klass){
		super(api);
		this.klass = klass;
	}
	
	public ClassMethodVisitor(int api, ClassVisitor decorated, KlassStorage klass) {
		super(api, decorated);
		this.klass = klass;
	}
	
	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions){
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
        final ArrayList<String> classList = new ArrayList<>();

        MethodVisitor instantiationDecorator = new MethodVisitor(Opcodes.ASM5, toDecorate) {
            public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
                System.out.println("uses: "+owner);
                classList.add(owner);
            }
        };

        String[] classArray = classList.toArray(new String[classList.size()]);

		this.klass.setCurrentPart(new Method(this.klass.getCurrentPart(),
				access,
				name,
				this.getReturnType(desc),
				this.getArguments(desc),
				exceptions,
                classArray));

		return instantiationDecorator;
	}

	String getReturnType(String desc){
		return Type.getReturnType(desc).getClassName();
	}

	Argument[] getArguments(String desc)
	{
		Type[] args = Type.getArgumentTypes(desc);
		Argument[] arguments = new Argument[args.length];
		for(int i=0; i< args.length; i++)
		{
			arguments[i] = new Argument( ("arg"+i), args[i].getClassName());
		}
		return arguments;
	}
	
	void addArguments(String desc){
		Type[] args = Type.getArgumentTypes(desc);
	    for(int i=0; i< args.length; i++)
		{
	    	String arg=args[i].getClassName();
	    }
	}
}
