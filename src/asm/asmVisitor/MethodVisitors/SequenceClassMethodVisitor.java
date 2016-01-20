package asm.asmVisitor.MethodVisitors;

import asm.asmVisitor.MethodVisitors.MethodInstanceVisitor;
import asm.impl.Argument;
import asm.impl2.StandardDataObjects.Method;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * Created by Steven on 1/14/2016.
 */
public class SequenceClassMethodVisitor extends ClassVisitor {

    private String methodName;
    private Method method;

    public SequenceClassMethodVisitor(int api, String methodName) {
        super(api);
        this.methodName = methodName;

    }

    public SequenceClassMethodVisitor(int api, ClassVisitor cv, String methodName) {
        super(api, cv);
        this.methodName = methodName;

    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor toDecorate =  super.visitMethod(access, name, desc, signature, exceptions);
        if(name.equals(methodName)){
            method = new Method(access, name, this.getReturnType(desc), this.getArguments(desc), exceptions, signature);
            toDecorate = new MethodInstanceVisitor(Opcodes.ASM5, toDecorate, method);
        }

        return toDecorate;
    }

    public String getMethodName() {
        return methodName;
    }

    public Method getMethod() {
        return method;
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
}
