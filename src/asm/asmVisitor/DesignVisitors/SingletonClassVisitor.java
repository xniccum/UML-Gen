package asm.asmVisitor.DesignVisitors;

import asm.StorageApi.IKlass;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * Created by Steven on 1/19/2016.
 */
public class SingletonClassVisitor extends ClassVisitor{
    private IKlass klass;
    private boolean privateStaticOwnClass = false;
    private boolean methodReturnTypeofClassType = false;
    private boolean publicConstructorExists = false;

    public SingletonClassVisitor(int i, IKlass klass) {
        super(i);
        this.klass = klass;
    }

    public SingletonClassVisitor(int i, ClassVisitor classVisitor, IKlass klass) {
        super(i, classVisitor);
        this.klass = klass;
    }

    /**
     * Used for Class Declaration Visitor
     * @param i version
     * @param i1 access
     * @param s name
     * @param s1 signature
     * @param s2 superName
     * @param strings interfaces
     */
    @Override
    public void visit(int i, int i1, String s, String s1, String s2, String[] strings) {
        super.visit(i, i1, s, s1, s2, strings);
    }

    /**
     *  Used to check fields for self
     * @param i access
     * @param s name
     * @param s1 desc
     * @param s2 signature
     * @param o value
     * @return
     */
    @Override
    public FieldVisitor visitField(int i, String s, String s1, String s2, Object o) {
        return super.visitField(i, s, s1, s2, o);
    }

    /**
     *  Used to check for method return
     * @param i access
     * @param s name
      * @param s1 desc
     * @param s2 signature
     * @param strings exceptions
     * @return
     */
    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
        return super.visitMethod(i, s, s1, s2, strings);
    }
}
