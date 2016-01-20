package asm.asmVisitor.StandardVisitors;

import asm.StorageApi.IKlass;
import asm.asmVisitor.MethodVisitors.MethodInstanceVisitor;
import asm.impl.Argument;
import asm.impl2.StandardDataObjects.Method;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class ClassMethodVisitor extends ClassVisitor
{
	private IKlass klass;
	public ClassMethodVisitor(int api, IKlass klass){
		super(api);
		this.klass = klass;
	}
	
	public ClassMethodVisitor(int api, ClassVisitor decorated, IKlass klass) {
		super(api, decorated);
		this.klass = klass;
    }
	
	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions){
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
//        final HashSet<String> classList = new HashSet<>();

        Method method = new Method(
                access,
                name,
                this.getReturnType(desc),
                this.getArguments(desc),
                exceptions,
				signature);

        MethodVisitor instantiationDecorator = new MethodInstanceVisitor(Opcodes.ASM5, toDecorate, method);

       // String[] classArray = classList.toArray(new String[classList.size()]);

		this.klass.addKlassPart(method);

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
