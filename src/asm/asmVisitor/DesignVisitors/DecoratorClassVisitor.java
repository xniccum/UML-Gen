package asm.asmVisitor.DesignVisitors;

import asm.StorageApi.IKlass;
import asm.impl2.DesignParts.SingletonDesign;
import asm.impl2.KlassDecorator;
import jdk.internal.org.objectweb.asm.Opcodes;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * Created by Steven on 1/19/2016.
 */
public class DecoratorClassVisitor extends ClassVisitor {
    private IKlass klass;
    private String superClassName;
    private boolean flagContainsSuperclass = false;

    private boolean designAdded = false;

    public DecoratorClassVisitor(int i, IKlass klass) {
        super(i);
        this.klass = klass;
    }

    public DecoratorClassVisitor(int i, ClassVisitor classVisitor, IKlass klass) {
        super(i, classVisitor);
        this.klass = klass;
    }

    private boolean conditionsMet(){
        return flagContainsSuperclass;
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
        superClassName = KlassDecorator.fullStripClean(superName);
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
        String varType = KlassDecorator.fullStripClean(desc);

        System.out.println("var type: "+varType + ", superclass name: "+superClassName);

        if (superClassName.equals(varType)) {
            flagContainsSuperclass = true;
            System.out.println("FLAG SET: flagContainsSuperclass = " + flagContainsSuperclass);
        }
        // TODO private static field of ClassType
        UpdateKlass();
        return super.visitField(access, name, desc, signature, value);
    }

}
