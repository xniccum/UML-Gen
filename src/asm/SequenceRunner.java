package asm;

import asm.StorageApi.MethodStorage.IMethodInternalCall;
import asm.StorageApi.MethodStorage.IMethodPart;
import asm.asmVisitor.MethodVisitors.SequenceClassMethodVisitor;
import asm.impl2.StandardDataObjects.Method;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

/**
 * Created by Steven on 1/14/2016.
 */
public class SequenceRunner {

    private SequenceRunner() {

    }


    public static Method run(String className, String methodName, int maxDepthCount) throws Exception {
        ClassReader reader = new ClassReader(className);
        SequenceClassMethodVisitor methodVisitor = new SequenceClassMethodVisitor(Opcodes.ASM5, methodName);
        reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
//        System.out.println("class: "+className+" method: "+methodName);
        Method method = methodVisitor.getMethod();
        method.setClassName(className);

        if (!className.startsWith("java/")) {
            System.out.println("METHOD IN SQRUNNER: " + reader.getClassName() + "." + method.getMethodName());
            if (maxDepthCount > 0) {
                for (IMethodPart part : method.getMethodParts()) {
                    IMethodInternalCall call = (IMethodInternalCall) part;
                    Method localm = run(call.getClassName(), call.getCallName(), (maxDepthCount - 1));
                    localm.setClassName(call.getClassName());
                    method.addSubMethod(localm);
                    localm.setTopLevel(false);
                }
            }
            System.out.println("SUB_METHODS: " + method.getSubMethods());
        }

        return method;
    }
}
