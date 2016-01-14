package asm.asmVisitor.MethodVisitors;

import asm.StorageApi.IMethod;
import asm.impl2.Methodimp.MethodInternalCall;
import org.objectweb.asm.MethodVisitor;

/**
 * Created by Steven on 1/13/2016.
 */
public class MethodInstanceVisitor extends MethodVisitor {
    private IMethod method;

    public MethodInstanceVisitor(int i, MethodVisitor methodVisitor, IMethod method) {
        super(i, methodVisitor);
        this.method = method;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
       super.visitMethodInsn(opcode, owner,name,desc,itf);
        //classList.add(owner);
        System.out.println("OWNER: "+owner+"."+name+"(...)");
        this.method.addMethodPart(new MethodInternalCall(owner, name, desc, itf));
    }
}
