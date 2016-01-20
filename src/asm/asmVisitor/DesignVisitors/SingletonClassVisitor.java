package asm.asmVisitor.DesignVisitors;

import asm.StorageApi.IKlass;
import asm.impl2.DesignParts.SingletonDesign;
import jdk.internal.org.objectweb.asm.Opcodes;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * Created by Steven on 1/19/2016.
 */
public class SingletonClassVisitor extends ClassVisitor {
    private IKlass klass;
    private boolean flagPrivateStaticOwnClass = false;
    private boolean flagMethodReturnTypeofClassType = false;
    private boolean flagPrivateConstructorExists = false;
    private boolean designAdded = false;

    public SingletonClassVisitor(int i, IKlass klass) {
        super(i);
        this.klass = klass;
    }

    public SingletonClassVisitor(int i, ClassVisitor classVisitor, IKlass klass) {
        super(i, classVisitor);
        this.klass = klass;
    }

    private boolean conditionsMet(){
        return flagPrivateStaticOwnClass && flagMethodReturnTypeofClassType && flagPrivateConstructorExists;
    }

    private void UpdateKlass(){
        if(conditionsMet()  && !designAdded){
            designAdded = true;
            klass.addKlassPart(new SingletonDesign());
        }
        else if(!conditionsMet() && designAdded){
            //remove klassPart
        }
    }

    /**
     * Used for Class Declaration Visitor
     *
     * @param version    version
     * @param access     access
     * @param name       name
     * @param signature  signature
     * @param superName  superName
     * @param interfaces interfaces
     */
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);

    }

    /**
     * Used to check fields for self
     *
     * @param access    access
     * @param name      name
     * @param desc      desc
     * @param signature signature
     * @param value     value
     * @return
     */
    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        if (access == Opcodes.ACC_PRIVATE && desc.equals(klass.getName())) {
            flagPrivateStaticOwnClass = true;
            System.out.println("FLAG SET: flagPrivateStaticOwnClass = " + flagPrivateStaticOwnClass);
        }
        // TODO private static field of ClassType
        UpdateKlass();
        return super.visitField(access, name, desc, signature, value);
    }

    /**
     * Used to check for method return
     *
     * @param access     access
     * @param name       name
     * @param desc       desc
     * @param signature  signature
     * @param exceptions exceptions
     * @return
     */
    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        // TODO Check for a public method that returns the class type
        if(access == Opcodes.ACC_PUBLIC && desc.equals(klass.getName()) && !name.equals(klass.getName())) {
            flagPrivateConstructorExists = true;
            System.out.println("FLAG SET: flagPrivateConstructorExists = " + flagPrivateConstructorExists);
        }

        // TODO Check for a private constructor
        if(access == Opcodes.ACC_PRIVATE && desc.equals(klass.getName()) && name.equals(klass.getName())) {
            flagPrivateConstructorExists = true;
            System.out.println("FLAG SET: flagPrivateConstructorExists = " + flagPrivateConstructorExists);
        }
        UpdateKlass();
        return super.visitMethod(access, name, desc, signature, exceptions);
    }
}
